package ihor.kalaur.topmovieapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailDto(

    val title: String,

    val tagline: String,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("release_date")
    val releaseDate: String,

    val runtime: Int,

    val budget: Long,

    val revenue: Long,

    val overview: String,

    @SerializedName("poster_path")
    val partPosterUrl: String

)