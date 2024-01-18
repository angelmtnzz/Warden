package modelos


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Book
import clases.ConsumptionStatus
import clases.Title

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("SELECT COUNT(*) FROM Book WHERE name = :name")  // Comprueba si existe un libro por su nombre
    suspend fun doesBookExist(name: String): Int

    @Query("SELECT * FROM Book WHERE name = :name")
    suspend fun getBook(name: String): Book


    @Query("SELECT Favourite FROM Book WHERE name = :name")
    suspend fun isFavourite(name: String): Boolean

    @Query("UPDATE Book SET favourite = :isFavourite WHERE name = :name")   // Para cambiar el status de favorito
    suspend fun updateFavouriteStatus(name: String, isFavourite: Boolean)

    @Query("UPDATE Book SET status = :newStatus WHERE name = :name")        //Para cambiar el Status
    suspend fun updateStatus(name: String, newStatus: ConsumptionStatus)

    @Query("SELECT status FROM Book WHERE name = :name")            // Devuelve el status del libro
    suspend fun getStatus(name: String): ConsumptionStatus
    @Query("SELECT * FROM Book WHERE status = :status")
    suspend fun getBooksByStatus(status: ConsumptionStatus): List<Book>

    @Query("SELECT * FROM Book WHERE favourite = 1")
    suspend fun getAllFavoriteBooks(): List<Book>

    @Query("SELECT COUNT(*) FROM Book")
    suspend fun getNumBooks(): Int

    @Query("SELECT * FROM Book")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT cover FROM Book WHERE id = :id")
    suspend fun getBookCover(id: Int): Int            //Imagenes se tratan como int
    @Query("SELECT cover FROM Book WHERE name = :name")
    suspend fun getBookCover(name: String): Int       //Imagenes se tratan como int

    @Query("SELECT name FROM Book WHERE id = :id")
    suspend fun getBookName(id: Int): String
    @Query("SELECT id FROM Book WHERE name= :name")
    suspend fun getBookId(name: String): Int

    @Query("SELECT author FROM Book WHERE id = :id")
    suspend fun getBookAuthor(id: Int): String
    @Query("SELECT author FROM Book WHERE name= :name")
    suspend fun getBookAuthor(name: String): String

    @Query("SELECT pages FROM Book WHERE id = :id")
    suspend fun getBookPages(id: Int): Int
    @Query("SELECT pages FROM Book WHERE name= :name")
    suspend fun getBookPages(name: String): Int

    @Query("SELECT genre FROM Book WHERE id = :id")
    suspend fun getBookGenre(id: Int): String
    @Query("SELECT genre FROM Book WHERE name= :name")
    suspend fun getBookGenre(name: String): String
}
