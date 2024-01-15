package modelos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Score

interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: Score)

    @Query("SELECT * FROM Score WHERE userNickname = :userNickname")
    suspend fun getAllScores(userNickname: String): LiveData<List<Score>>

    @Query("SELECT userNickname FROM Score WHERE userNickname=:userNickname")
    suspend fun getUserNicknameFromNickname(userNickname: String): LiveData<String>

    @Query("SELECT score FROM Score WHERE userNickname=:userNickname")
    suspend fun getScoreFromNickname(userNickname: String): LiveData<Double>

    @Query("SELECT titleId FROM Score WHERE userNickname=:userNickname")
    suspend fun getTitleIdFromNickname(userNickname: String): LiveData<Int>

    @Query("SELECT * FROM Score WHERE titleId = :id")
    suspend fun getAllScoresFromTitleId(id: Int): LiveData<List<Score>>

    @Query("SELECT userNickname FROM Score WHERE titleId = :id")
    suspend fun getUserNicknameFromTitleId(id: Int): LiveData<String>

    @Query("SELECT score FROM Score WHERE titleId = :id")
    suspend fun getScoreFromTitleId(id: Int): LiveData<Double>

    @Query("SELECT titleId FROM Score WHERE titleId = :id")
    suspend fun getTitleIdFromTitleId(id: Int): LiveData<Int>
}