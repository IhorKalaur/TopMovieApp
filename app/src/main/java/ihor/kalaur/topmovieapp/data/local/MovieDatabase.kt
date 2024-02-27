package ihor.kalaur.topmovieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ihor.kalaur.topmovieapp.data.local.dao.MovieDao
import ihor.kalaur.topmovieapp.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class, /*MovieDetail::class*/], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    /*abstract fun movieDetail(): MovieDetailDao*/

}