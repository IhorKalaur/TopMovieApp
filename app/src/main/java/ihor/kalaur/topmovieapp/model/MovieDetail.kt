package ihor.kalaur.topmovieapp.model

data class MovieDetail(

    val title: String,

    val tagline: String,

    val rating: Double,

    val releaseDate: String,

    val runtime: Int,

    val budget: Long,

    val revenue: Long,

    val overview: String,

    val partPosterUrl: String

)