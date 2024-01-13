package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Review
import clases.User

data class UserWithReviews (
    @Embedded var user: User,
    @Relation(
        parentColumn = "nickname",
        entityColumn = "userNickname"
    )
    var reviews: List<Review>
)