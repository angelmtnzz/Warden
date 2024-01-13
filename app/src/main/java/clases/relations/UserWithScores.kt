package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Score
import clases.User

data class UserWithScores (
    @Embedded var user: User,
    @Relation(
        parentColumn = "nickname",
        entityColumn = "userNickname"
    )
    var scores: List<Score>
)