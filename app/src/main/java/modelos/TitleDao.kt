package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Book
import clases.ConsumptionStatus
import clases.Film
import clases.Serie
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
    @Query("UPDATE Title SET favourite = :isFavourite WHERE name = :name")   // Para cambiar el status de favorito
    suspend fun updateFavouriteStatus(name: String, isFavourite: Boolean)

    @Query("UPDATE Title SET status = :newStatus WHERE name = :name")        //Para cambiar el Status del titulo
    suspend fun updateStatus(name: String, newStatus: ConsumptionStatus)
    @Query("SELECT status FROM Title WHERE name = :name")            // Devuelve el status del titulo
    suspend fun getStatus(name: String): ConsumptionStatus

    @Query("SELECT * FROM Title WHERE status = :status")        // Le pasas un status y te devuelve los titulos con dicho status
    suspend fun getTitlesByStatus(status: ConsumptionStatus): List<Title>

    @Query("SELECT * FROM Title WHERE favourite = 1")
    suspend fun getAllFavoriteTitles(): List<Title>
    @Query("SELECT * FROM Title")
    suspend fun getAllTitles(): List<Title>
    @Query("SELECT COUNT(*) FROM Title")
    suspend fun getNumTitles(): Int

    // Para Books
    @Query("SELECT COUNT(*) FROM Book WHERE name = :name")  // Comprueba si existe un libro por su nombre
    suspend fun doesBookExist(name: String): Int

    @Query("SELECT * FROM Book WHERE name = :name")
    suspend fun getBook(name: String): Book

    // Para Films
    @Query("SELECT COUNT(*) FROM Film WHERE name = :name")  // Comprueba si existe una pelicula por su nombre
    suspend fun doesFilmExist(name: String): Int
    @Query("SELECT * FROM Film WHERE name = :name")
    suspend fun getFilm(name: String): Film

    // Para Series
    @Query("SELECT COUNT(*) FROM Serie WHERE name = :name")  // Comprueba si existe una serie por su nombre
    suspend fun doesSerieExist(name: String): Int

    @Query("SELECT * FROM Serie WHERE name = :name")
    suspend fun getSerie(name: String): Serie
}



