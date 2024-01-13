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
    fun getAllSeries(): LiveData<List<Serie>>

    @Query("SELECT cover FROM Serie WHERE titleId = :id")
    fun getSerieCover(id: Int): LiveData<Int>            //Imagenes se tratan como int
    @Query("SELECT cover FROM Serie WHERE name = :name")
    fun getSerieCover(name: String): LiveData<Int>

    @Query("SELECT name FROM Serie WHERE titleId = :id")
    fun getSerieName(id: Int): LiveData<String>
    @Query("SELECT titleId FROM Serie WHERE name= :name")
    fun getSerieId(name: String): LiveData<Int>

    @Query("SELECT director FROM Serie WHERE titleId = :id")
    fun getSerieDirector(id: Int): LiveData<String>
    @Query("SELECT director FROM Serie WHERE name= :name")
    fun getSerieDirector(name: String): LiveData<String>

    @Query("SELECT lenght FROM Serie WHERE titleId = :id")
    fun getSerieLenght(id: Int): LiveData<Int>
    @Query("SELECT lenght FROM Serie WHERE name= :name")
    fun getSerieLenght(name: String): LiveData<Int>

    @Query("SELECT genre FROM Serie WHERE titleId = :id")
    fun getSerieGenre(id: Int): LiveData<String>
    @Query("SELECT genre FROM Serie WHERE name= :name")
    fun getSerieGenre(name: String): LiveData<String>
}