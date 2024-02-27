package ihor.kalaur.topmovieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(

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

    @SerializedName("backdrop_path")
    val partBackdropUrl: String,

    @SerializedName("poster_path")
    val partPosterUrl: String

)