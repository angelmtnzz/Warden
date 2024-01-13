package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Season
import clases.Serie

data class SerieWithSeasons (
    @Embedded var serie: Serie,
    @Relation(
        parentColumn = "titleId",
        entityColumn = "titleId"
    )
    var seasons: List<Season>
)