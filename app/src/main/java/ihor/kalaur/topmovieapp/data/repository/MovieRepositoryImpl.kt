package ihor.kalaur.topmovieapp.data.repository

import ihor.kalaur.topmovieapp.data.local.MovieDatabase
import ihor.kalaur.topmovieapp.data.local.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.TmdbApi
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.model.MovieDetail
import ihor.kalaur.topmovieapp.util.toEntity
import ihor.kalaur.topmovieapp.util.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val tmdbApi: TmdbApi,
    private val tmdbDatabase: MovieDatabase
) {
    private var currentPage = 1;

    fun getTopRatedMovies(page: Int): Flow<List<Movie>> = flow {
        val topRatedMoviesResponse = tmdbApi.getTopRatedMovies(page)
        if (topRatedMoviesResponse.isSuccessful) {
            topRatedMoviesResponse.body()?.results.let { movieDtos ->
                withContext(Dispatchers.IO) {
                    if (movieDtos != null) {
                        tmdbDatabase.movieDao().addMovies(movieDtos.map { movieDto -> movieDto.toEntity() })
                    } else {
                        //TODO Обробка помилки, можливо, кидання винятку або логування
                    }
                }
                emitAll(tmdbDatabase.movieDao().getAllMovies().map { list ->
                    list.map { movieEntity -> movieEntity.toMovie() }
                })
            }
        }



        /*if (topRatedMoviesResponse.isSuccessful) {
            // Асинхронне додавання фільмів у базу даних
            topRatedMoviesResponse.body()?.results?.let { movies ->
                withContext(Dispatchers.IO) {
                    tmdbDatabase.movieDao().addMovies(movies)
                    currentPage++
                }
            }
        } else {
            //TODO Обробка помилки, можливо, кидання винятку або логування
        }
        // Витягування фільмів із бази даних та їх емітуючий як потік
        emitAll(tmdbDatabase.movieDao().getAllMovies())*/
    }.flowOn(Dispatchers.IO)

    suspend fun getMovieDetail(movieId: Int): MovieDetail {

       /* val movieDetail = try {

        } catch (e: Exception) {
            throw Exception("Can't load Data")
        }
        return try {
            tmdbApi.getMovieDetail(movieId)
        } catch ()*/
        return tmdbApi.getMovieDetail(movieId)

    }
}

