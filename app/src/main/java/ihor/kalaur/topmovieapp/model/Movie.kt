package ihor.kalaur.topmovieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(

   // @PrimaryKey(autoGenerate = true)
    //val id: String,

    //@SerializedName("id")
    val movieId: String,

  //  @SerializedName("title")
    val movieName: String,

  //  @SerializedName("release_date")
    val dateOfProduction: String,

   // @SerializedName("poster_path")
    val partOfPosterUrl: String
)
