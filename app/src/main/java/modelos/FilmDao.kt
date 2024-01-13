package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import clases.Film

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: Film)

    @Query("SELECT * FROM Film")
    fun getAllFilms(): LiveData<List<Film>>

    @Query("SELECT cover FROM Film WHERE titleId = :id")
    fun getFilmCover(id: Int): LiveData<Int>            //Imagenes se tratan como int
    @Query("SELECT cover FROM Film WHERE name = :name")
    fun getFilmCover(name: String): LiveData<Int>

    @Query("SELECT name FROM Film WHERE titleId = :id")
    fun getFilmName(id: Int): LiveData<String>
    @Query("SELECT titleId FROM Film WHERE name= :name")
    fun getFilmId(name: String): LiveData<Int>

    @Query("SELECT director FROM Film WHERE titleId = :id")
    fun getFilmDirector(id: Int): LiveData<String>
    @Query("SELECT director FROM Film WHERE name= :name")
    fun getFilmDirector(name: String): LiveData<String>

    @Query("SELECT lenght FROM Film WHERE titleId = :id")
    fun getFilmLenght(id: Int): LiveData<Int>
    @Query("SELECT lenght FROM Film WHERE name= :name")
    fun getFilmLenght(name: String): LiveData<Int>

    @Query("SELECT genre FROM Film WHERE titleId = :id")
    fun getFilmGenre(id: Int): LiveData<String>
    @Query("SELECT genre FROM Film WHERE name= :name")
    fun getFilmGenre(name: String): LiveData<String>

}