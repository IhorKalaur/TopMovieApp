package ihor.kalaur.topmovieapp.data.repository

import ihor.kalaur.topmovieapp.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTopRatedMovies(): Flow<List<Movie>>

}