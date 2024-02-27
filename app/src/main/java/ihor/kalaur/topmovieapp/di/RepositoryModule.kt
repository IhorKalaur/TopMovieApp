package ihor.kalaur.topmovieapp.di

import androidx.paging.Pager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.TmdbApi
import ihor.kalaur.topmovieapp.data.repository.MovieRepository
import ihor.kalaur.topmovieapp.data.repository.MovieRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: TmdbApi,
        pager: Pager<Int, MovieEntity>
    ): MovieRepository = MovieRepositoryImpl(api, pager)

}