package ihor.kalaur.topmovieapp.data.remote.response

import com.google.gson.annotations.SerializedName
import ihor.kalaur.topmovieapp.model.Movie

data class MoviesResponse(
    @SerializedName("page")
    val currentPage: Int,

    @SerializedName("results")
    val results: List<MovieDto>,

    @SerializedName("total_pages")
    val totalPage: Int
)
