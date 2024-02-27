package ihor.kalaur.topmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.TmdbApi
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.model.MovieDetail
import ihor.kalaur.topmovieapp.util.toMovie
import ihor.kalaur.topmovieapp.util.toMovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbApi,
    private val pager: Pager<Int, MovieEntity>
) : MovieRepository {

    override fun getMoviesPagingFlow(): Flow<PagingData<Movie>> {
        return pager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun getMovieDetail(movieId: String): MovieDetail =
        api.getMovieDetail(movieId).toMovieDetail()

}

