package ihor.kalaur.topmovieapp.data.remote.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val movieId: String,

    @SerializedName("title")
    val movieName: String,

    @SerializedName("release_date")
    val dateOfProduction: String,

    @SerializedName("poster_path")
    val partOfPosterUrl: String
)
