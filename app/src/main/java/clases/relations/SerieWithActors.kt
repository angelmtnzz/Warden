package clases.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import clases.Actor
import clases.Serie

data class SerieWithActors(
    @Embedded var serie: Serie,
    @Relation(
        parentColumn = "titleId",
        entityColumn = "id",
        associateBy = Junction(SerieActorCrossRef::class)
    )
    var actors: List<Actor>
)
