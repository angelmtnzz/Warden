package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Film
import clases.Title


data class TitleAndFilm(
    @Embedded var title: Title,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId"
    )
    var film: Film
)
