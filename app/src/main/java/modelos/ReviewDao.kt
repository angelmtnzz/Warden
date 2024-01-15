package modelos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Review

interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(review: Review)

    @Query("SELECT * FROM Review WHERE userNickname = :userNickname")
    suspend fun getAllReviewsFromNickname(userNickname: String): LiveData<List<Review>>

    @Query("SELECT userNickname FROM Review WHERE userNickname=:userNickname")
    suspend fun getUserNicknameFromNickname(userNickname: String): LiveData<String>

    @Query("SELECT comment FROM Review WHERE userNickname=:userNickname")
    suspend fun getCommentFromNickname(userNickname: String): LiveData<String>

    @Query("SELECT titleId FROM Review WHERE userNickname=:userNickname")
    suspend fun getTitleIdFromNickname(userNickname: String): LiveData<Int>

    @Query("SELECT * FROM Review WHERE titleId = :id")
    suspend fun getAllReviewsFromTitleId(id: Int): LiveData<List<Review>>

    @Query("SELECT userNickname FROM Review WHERE titleId=:id")
    suspend fun getUserNicknameFromTitleId(id: Int): LiveData<String>

    @Query("SELECT comment FROM Review WHERE titleId=:id")
    suspend fun getCommentFromTitleId(id: Int): LiveData<String>

    @Query("SELECT titleId FROM Review WHERE titleId=:id")
    suspend fun getTitleIdFromTitleId(id: Int): LiveData<Int>

}