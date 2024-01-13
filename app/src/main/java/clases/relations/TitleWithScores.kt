package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Score
import clases.Title

data class TitleWithScores (
    @Embedded var title: Title,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId"
    )
    var scores: List<Score>
)