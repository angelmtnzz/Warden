package clases.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import clases.Title
import clases.User

data class UserWithTitles(
    @Embedded var user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(UserTitleCrossRef::class)

    )
    var titles: List<Title>
)