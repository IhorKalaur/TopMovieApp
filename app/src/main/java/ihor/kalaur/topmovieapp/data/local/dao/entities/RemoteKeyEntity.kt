package ihor.kalaur.topmovieapp.data.local.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_remote_keys")
data class RemoteKeyEntity(

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextPage: Int?,
    val lastUpdated: Long

)
