package clases.relations

import androidx.room.Entity

@Entity(primaryKeys = ["serieId", "actorId"])
data class SerieActorCrossRef(
    val serieId: Int,
    val actorId: Int
)