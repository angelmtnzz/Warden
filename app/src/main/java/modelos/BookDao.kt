package modelos

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM Book")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT cover FROM Book WHERE titleId = :id")
    suspend fun getBookCover(id: Int): Int            //Imagenes se tratan como int
    @Query("SELECT cover FROM Book WHERE name = :name")
    suspend fun getBookCover(name: String): Int       //Imagenes se tratan como int

    @Query("SELECT name FROM Book WHERE titleId = :id")
    suspend fun getBookName(id: Int): String
    @Query("SELECT titleId FROM Book WHERE name= :name")
    suspend fun getBookId(name: String): Int

    @Query("SELECT author FROM Book WHERE titleId = :id")
    suspend fun getBookAuthor(id: Int): String
    @Query("SELECT author FROM Book WHERE name= :name")
    suspend fun getBookAuthor(name: String): String

    @Query("SELECT pages FROM Book WHERE titleId = :id")
    suspend fun getBookPages(id: Int): Int
    @Query("SELECT pages FROM Book WHERE name= :name")
    suspend fun getBookPages(name: String): Int

    @Query("SELECT genre FROM Book WHERE titleId = :id")
    suspend fun getBookGenre(id: Int): String
    @Query("SELECT genre FROM Book WHERE name= :name")
    suspend fun getBookGenre(name: String): String
}
