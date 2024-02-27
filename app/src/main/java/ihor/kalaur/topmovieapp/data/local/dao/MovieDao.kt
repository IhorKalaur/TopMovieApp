package ihor.kalaur.topmovieapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ihor.kalaur.topmovieapp.data.local.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.response.MovieDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}