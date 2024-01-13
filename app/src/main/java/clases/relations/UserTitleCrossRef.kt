package clases.relations

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "titleId"])
data class UserTitleCrossRef(
    val userId: Int,
    val titleId: Int
)