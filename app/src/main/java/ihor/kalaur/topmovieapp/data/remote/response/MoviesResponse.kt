package ihor.kalaur.topmovieapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: List<MovieDto>
)
