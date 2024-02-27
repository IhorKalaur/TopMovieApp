package ihor.kalaur.topmovieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ihor.kalaur.topmovieapp.data.local.dao.entities.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<RemoteKeyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKeyEntity)

    @Query("SELECT * FROM movie_remote_keys WHERE id=:key")
    suspend fun getKeyByMovie(key: String): RemoteKeyEntity?

    @Query("DELETE FROM movie_remote_keys")
    suspend fun clearKeys()
}