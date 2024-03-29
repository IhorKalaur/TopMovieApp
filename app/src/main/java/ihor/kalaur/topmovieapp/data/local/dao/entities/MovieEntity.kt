package ihor.kalaur.topmovieapp.data.local.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    val movieId: String,

    val movieName: String,

    val yearOfProduction: String,

    val partOfPosterUrl: String

)
