package modelos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import clases.Actor

interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActor(review: Actor)

    //@Query("SELECT * FROM Actor WHERE titleId = :titleId")
    //suspend fun getAllActorsFromTitleId(titleId: Int): LiveData<List<Actor>>

    //@Query("SELECT id FROM Actor WHERE titleId=:titleId")
    //suspend fun getNameFromTitleId(titleId: Int): LiveData<Int>

}