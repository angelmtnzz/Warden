package modelos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Title

interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTitle(title: Title)

    @Query("SELECT * FROM Title ORDER BY id ASC")
    fun readAllData(): LiveData<List<Title>>
}