package modelos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import clases.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)
}
