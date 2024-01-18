package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Title
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitle(title: Title)

    @Query("SELECT COUNT(*) FROM Title WHERE name = :name")  // Comprueba si existe un title por su nombre
    suspend fun doesTitleExist(name: String): Int

    @Query("SELECT * FROM Title ORDER BY id ASC")
    fun readAllData(): LiveData<List<Title>>

    @Query("SELECT * FROM Title WHERE name LIKE :searchQuery")
    fun search(searchQuery: String): Flow<List<Title>>

}