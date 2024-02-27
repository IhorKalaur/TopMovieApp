package ihor.kalaur.topmovieapp.data.remote

import ihor.kalaur.topmovieapp.data.remote.response.MoviesResponse
import ihor.kalaur.topmovieapp.model.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): MovieDetail

}