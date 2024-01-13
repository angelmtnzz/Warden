package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Title

@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitle(title: Title)

    @Query("SELECT * FROM Title ORDER BY id ASC")
    fun readAllData(): LiveData<List<Title>>
}