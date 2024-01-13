package clases.relations

import androidx.room.Entity

@Entity(primaryKeys = ["filmId", "actorId"])
data class FilmActorCrossRef(
    val filmId: Int,
    val actorId: Int
)