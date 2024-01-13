package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score (
    @PrimaryKey
    val userNickname: String,
    var score: Double,
    val titleId: Int
)