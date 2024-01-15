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
    suspend fun getAllFilms(): List<Film>

    @Query("SELECT cover FROM Film WHERE titleId = :id")
    suspend fun getFilmCover(id: Int): Int            //Imagenes se tratan como int
    @Query("SELECT cover FROM Film WHERE name = :name")
    suspend fun getFilmCover(name: String): Int

    @Query("SELECT name FROM Film WHERE titleId = :id")
    suspend fun getFilmName(id: Int): String
    @Query("SELECT titleId FROM Film WHERE name= :name")
    suspend fun getFilmId(name: String): Int

    @Query("SELECT director FROM Film WHERE titleId = :id")
    suspend fun getFilmDirector(id: Int): String
    @Query("SELECT director FROM Film WHERE name= :name")
    suspend fun getFilmDirector(name: String): String

    @Query("SELECT lenght FROM Film WHERE titleId = :id")
    suspend fun getFilmLenght(id: Int): Int
    @Query("SELECT lenght FROM Film WHERE name= :name")
    suspend fun getFilmLenght(name: String): Int

    @Query("SELECT genre FROM Film WHERE titleId = :id")
    suspend fun getFilmGenre(id: Int): String
    @Query("SELECT genre FROM Film WHERE name= :name")
    suspend fun getFilmGenre(name: String): String

}