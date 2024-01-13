package clases.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import clases.Actor
import clases.Film

data class ActorWithFilms(
    @Embedded var actor: Actor,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId",
        associateBy = Junction(FilmActorCrossRef::class)
    )
    var films: List<Film>
    )
