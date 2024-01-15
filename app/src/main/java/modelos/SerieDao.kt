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

    @Query("SELECT * FROM Serie")
    fun getAllSeries(): List<Serie>

    @Query("SELECT cover FROM Serie WHERE titleId = :id")
    fun getSerieCover(id: Int): Int            //Imagenes se tratan como int
    @Query("SELECT cover FROM Serie WHERE name = :name")
    fun getSerieCover(name: String): Int

    @Query("SELECT name FROM Serie WHERE titleId = :id")
    fun getSerieName(id: Int): String
    @Query("SELECT titleId FROM Serie WHERE name= :name")
    fun getSerieId(name: String): Int

    @Query("SELECT director FROM Serie WHERE titleId = :id")
    fun getSerieDirector(id: Int): String
    @Query("SELECT director FROM Serie WHERE name= :name")
    fun getSerieDirector(name: String): String

    @Query("SELECT lenght FROM Serie WHERE titleId = :id")
    fun getSerieLenght(id: Int): Int
    @Query("SELECT lenght FROM Serie WHERE name= :name")
    fun getSerieLenght(name: String): Int

    @Query("SELECT genre FROM Serie WHERE titleId = :id")
    fun getSerieGenre(id: Int): String
    @Query("SELECT genre FROM Serie WHERE name= :name")
    fun getSerieGenre(name: String): String
}