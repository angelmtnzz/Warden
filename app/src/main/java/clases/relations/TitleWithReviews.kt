package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Review
import clases.Title

data class TitleWithReviews (
    @Embedded var title: Title,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId"
    )
    var reviews: List<Review>
)