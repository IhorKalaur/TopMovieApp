package ihor.kalaur.topmovieapp.data.remote.mediator

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ihor.kalaur.topmovieapp.data.local.MovieDatabase
import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.local.dao.entities.RemoteKeyEntity
import ihor.kalaur.topmovieapp.data.remote.TmdbApi
import ihor.kalaur.topmovieapp.util.DataParser
import ihor.kalaur.topmovieapp.util.toEntity
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val database: MovieDatabase,
    private val tmdbApi: TmdbApi,
    private val dataParser: DataParser
) : RemoteMediator<Int, MovieEntity>() {

    private val remoteKeyDao = database.remoteKeyDao()
    private val movieDao = database.movieDao()

    override suspend fun initialize(): InitializeAction {
        val remoteKey = database.withTransaction {
            remoteKeyDao.getKeyByMovie("popular_movie")
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.getKeyByMovie("popular_movie")
                    } ?: return MediatorResult.Success(true)

                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(true)
                    }

                    remoteKey.nextPage
                }
            }

            val movies = tmdbApi.getTopRatedMovies(
                page = loadKey
            ).results.map { movieDto ->
                movieDto.also {
                    it.yearOfProduction = dataParser.parseYearFromResponse(it.yearOfProduction)
                }
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.movieDao().clearAll()
                }

                val nextPage = if (movies.isEmpty()) {
                    null
                } else {
                    loadKey + 1
                }


                remoteKeyDao.insertKey(
                    RemoteKeyEntity(
                        id = "popular_movie",
                        nextPage = nextPage,
                        lastUpdated = System.currentTimeMillis()
                    )
                )

                movieDao.addMovies(
                    movies.map {
                            dto -> dto.toEntity()
                    })
            }

            MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}