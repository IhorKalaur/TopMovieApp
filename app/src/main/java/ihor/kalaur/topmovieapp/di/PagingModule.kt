package ihor.kalaur.topmovieapp.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ihor.kalaur.topmovieapp.data.local.MovieDatabase
import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.mediator.MovieRemoteMediator
import ihor.kalaur.topmovieapp.data.remote.TmdbApi
import ihor.kalaur.topmovieapp.util.DataParser
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    @Singleton
    fun provideUserFeedPager(
        movieDatabase: MovieDatabase,
        tmdbApi: TmdbApi,
        dataParser: DataParser
    ): Pager<Int, MovieEntity> {

        return Pager(
            config = PagingConfig(pageSize = 20),

            remoteMediator = MovieRemoteMediator(
                database = movieDatabase,
                tmdbApi = tmdbApi,
                dataParser = dataParser
            ),
            pagingSourceFactory = {
                movieDatabase.movieDao().getPagingSourceMovie()
            }
        )
    }

}