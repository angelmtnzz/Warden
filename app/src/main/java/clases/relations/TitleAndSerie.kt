package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Serie
import clases.Title


data class TitleAndSerie(
    @Embedded var title: Title,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId"
    )
    var serie: Serie
)