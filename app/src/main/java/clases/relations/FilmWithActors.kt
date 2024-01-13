package clases.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import clases.Actor
import clases.Film

data class FilmWithActors(
    @Embedded var film: Film,
    @Relation(
        parentColumn = "titleId",
        entityColumn = "id",
        associateBy = Junction(FilmActorCrossRef::class)
    )
    var actors: List<Actor>
)