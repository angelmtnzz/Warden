package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Serie


@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(serie: Serie)

    @Query("SELECT COUNT(*) FROM Serie WHERE name = :name")  // Comprueba si existe una serie por su nombre
    suspend fun doesSerieExist(name: String): Int

    @Query("SELECT * FROM Serie")
    suspend fun getAllSeries(): List<Serie>

    @Query("SELECT cover FROM Serie WHERE titleId = :id")
    suspend fun getSerieCover(id: Int): Int            //Imagenes se tratan como int
    @Query("SELECT cover FROM Serie WHERE name = :name")
    suspend fun getSerieCover(name: String): Int

    @Query("SELECT name FROM Serie WHERE titleId = :id")
    suspend fun getSerieName(id: Int): String
    @Query("SELECT titleId FROM Serie WHERE name= :name")
    suspend fun getSerieId(name: String): Int

    @Query("SELECT director FROM Serie WHERE titleId = :id")
    suspend fun getSerieDirector(id: Int): String
    @Query("SELECT director FROM Serie WHERE name= :name")
    suspend fun getSerieDirector(name: String): String

    @Query("SELECT lenght FROM Serie WHERE titleId = :id")
    suspend fun getSerieLenght(id: Int): Int
    @Query("SELECT lenght FROM Serie WHERE name= :name")
    suspend fun getSerieLenght(name: String): Int

    @Query("SELECT genre FROM Serie WHERE titleId = :id")
    suspend fun getSerieGenre(id: Int): String
    @Query("SELECT genre FROM Serie WHERE name= :name")
    suspend fun getSerieGenre(name: String): String
}