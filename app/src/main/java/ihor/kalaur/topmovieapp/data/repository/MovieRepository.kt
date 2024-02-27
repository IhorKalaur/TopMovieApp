package ihor.kalaur.topmovieapp.data.repository

import androidx.paging.PagingData
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesPagingFlow(): Flow<PagingData<Movie>>
    suspend fun getMovieDetail(movieId: String): MovieDetail

}