package modelos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Book
import clases.Serie


@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(serie: Serie)

    @Query("SELECT COUNT(*) FROM Serie WHERE name = :name")  // Comprueba si existe una serie por su nombre
    suspend fun doesSerieExist(name: String): Int

    @Query("SELECT * FROM Serie WHERE name = :name")
    suspend fun getSerie(name: String): Serie


    @Query("SELECT Favourite FROM Serie WHERE name = :name")
    suspend fun isFavourite(name: String): Boolean

    @Query("UPDATE Serie SET favourite = :isFavourite WHERE name = :name")   // Para cambiar el status de favorito
    suspend fun updateFavouriteStatus(name: String, isFavourite: Boolean)

    @Query("SELECT * FROM Serie WHERE favourite = 1")
    suspend fun getAllFavoriteBooks(): List<Serie>

    @Query("SELECT COUNT(*) FROM Serie")
    suspend fun getNumBooks(): Int

    @Query("SELECT * FROM Serie")
    suspend fun getAllSeries(): List<Serie>

    @Query("SELECT cover FROM Serie WHERE id = :id")
    suspend fun getSerieCover(id: Int): Int            //Imagenes se tratan como int
    @Query("SELECT cover FROM Serie WHERE name = :name")
    suspend fun getSerieCover(name: String): Int

    @Query("SELECT name FROM Serie WHERE id = :id")
    suspend fun getSerieName(id: Int): String
    @Query("SELECT id FROM Serie WHERE name= :name")
    suspend fun getSerieId(name: String): Int

    @Query("SELECT director FROM Serie WHERE id = :id")
    suspend fun getSerieDirector(id: Int): String
    @Query("SELECT director FROM Serie WHERE name= :name")
    suspend fun getSerieDirector(name: String): String

    @Query("SELECT genre FROM Serie WHERE id = :id")
    suspend fun getSerieGenre(id: Int): String
    @Query("SELECT genre FROM Serie WHERE name= :name")
    suspend fun getSerieGenre(name: String): String
}