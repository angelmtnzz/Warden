package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Chapter
import clases.Season

data class SeasonWithChapters (
    @Embedded var season: Season,
    @Relation(
        parentColumn = "seasonNumber",
        entityColumn = "seasonNumber"
    )
    var chapter: List<Chapter>
)