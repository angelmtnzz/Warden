package modelos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import clases.Chapter

interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapter: Chapter)

    @Query("SELECT * FROM Chapter WHERE seasonNumber = :seasonNumber")
    suspend fun getAllChaptersFromSeasonNumber(seasonNumber: Int): LiveData<List<Chapter>>

    @Query("SELECT chapterNumber FROM Chapter WHERE seasonNumber = :seasonNumber")
    suspend fun getChapterNumberFromSeasonNumber(seasonNumber: Int): LiveData<Int>

    @Query("SELECT duration FROM Chapter WHERE seasonNumber = :seasonNumber")
    suspend fun getDurationFromSeasonNumber(seasonNumber: Int): LiveData<Int>

    @Query("SELECT seasonNumber FROM Chapter WHERE seasonNumber = :seasonNumber")
    suspend fun seasonNumberFromSeasonNumber(seasonNumber: Int): LiveData<Int>

    @Query("SELECT * FROM Chapter WHERE chapterNumber = :chapterNumber")
    suspend fun getAllChaptersFromChapterNumber(chapterNumber: Int): LiveData<List<Chapter>>

    @Query("SELECT chapterNumber FROM Chapter WHERE chapterNumber = :chapterNumber")
    suspend fun getChapterNumberFromChapterNumber(chapterNumber: Int): LiveData<Int>

    @Query("SELECT duration FROM Chapter WHERE chapterNumber = :chapterNumber")
    suspend fun getDurationFromChapterNumber(chapterNumber: Int): LiveData<Int>

    @Query("SELECT seasonNumber FROM Chapter WHERE chapterNumber = :chapterNumber")
    suspend fun seasonNumberFromChapterNumber(chapterNumber: Int): LiveData<Int>
}