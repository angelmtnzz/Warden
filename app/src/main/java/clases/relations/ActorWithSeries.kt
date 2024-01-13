package clases.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import clases.Actor
import clases.Serie

data class ActorWithSeries(
    @Embedded var actor: Actor,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId",
        associateBy = Junction(SerieActorCrossRef::class)
    )
    var series: List<Serie>
)
