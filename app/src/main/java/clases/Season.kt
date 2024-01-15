package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Season(
    @PrimaryKey
    var seasonNumber: Int,
    val titleId: Int
) {
}
