package ihor.kalaur.topmovieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ihor.kalaur.topmovieapp.data.local.dao.MovieDao
import ihor.kalaur.topmovieapp.data.local.dao.RemoteKeyDao
import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.local.dao.entities.RemoteKeyEntity

@Database(entities = [MovieEntity::class, RemoteKeyEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao() : RemoteKeyDao

}