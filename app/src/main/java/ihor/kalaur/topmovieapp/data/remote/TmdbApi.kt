package ihor.kalaur.topmovieapp.data.remote

import ihor.kalaur.topmovieapp.data.remote.response.MovieDetailDto
import ihor.kalaur.topmovieapp.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: String): MovieDetailDto

}