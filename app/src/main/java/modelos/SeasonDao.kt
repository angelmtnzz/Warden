package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Season

@Dao
interface SeasonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeason(season: Season)

    @Query("SELECT * FROM Season WHERE titleId = :id")
    suspend fun getAllSeasonsFromTitleId(id: Int): LiveData<List<Season>>

    @Query("SELECT seasonNumber FROM Season WHERE titleId = :id")
    suspend fun getSeasonNumberFromTitleId(id:Int): LiveData<Int>

    @Query("SELECT * FROM Season WHERE seasonNumber = :number")
    suspend fun getAllSeasonsFromSeasonNumber(number: Int): LiveData<List<Season>>

    @Query("SELECT seasonNumber FROM Season WHERE seasonNumber = :number")
    suspend fun getSeasonNumberFromSeasonNumber(number: Int): LiveData<Int>

}