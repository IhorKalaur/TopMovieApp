package ihor.kalaur.topmovieapp.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ihor.kalaur.topmovieapp.data.local.MovieDatabase
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.util.toMovie
import java.io.IOException
import javax.inject.Inject

/*@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val movieDb: MovieDatabase,
    private val tmdbApi: TmdbApi
) : RemoteMediator<Int, Movie>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.APPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.PREPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.movieId.toInt() / state.config.pageSize) + 1
                    }
                }
            }

            val movies = tmdbApi.getTopRatedMovies(
                page = loadKey
            )

            movieDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    movieDb.movieDao().clearAll()
                }
                movieDb.movieDao().addMovies(movies.body()!!.results)
            }

            MediatorResult.Success(
                endOfPaginationReached = movies.body()!!.results.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

        //TODO("Not yet implemented")
    }

}*/