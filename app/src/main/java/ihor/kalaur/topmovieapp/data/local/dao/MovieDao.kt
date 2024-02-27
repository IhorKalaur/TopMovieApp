package ihor.kalaur.topmovieapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun getPagingSourceMovie(): PagingSource<Int, MovieEntity>

    @Query("select * from movies order by movieId asc limit :limit")
    fun getFlowMovies(limit: Int): Flow<List<MovieEntity>>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}