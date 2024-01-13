package clases.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import clases.Title
import clases.User

data class TitleWithUsers(
    @Embedded var title: Title,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(UserTitleCrossRef::class)

    )
    var users: List<User>
)