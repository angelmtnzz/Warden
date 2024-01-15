package clases.relations

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["userId", "titleId"])
data class UserTitleCrossRef(
    val userId: Int,
    val titleId: Int,
    var status: Double,
    var favourite: Boolean,
    var startDate: Date,
    var finishDate: Date?

)