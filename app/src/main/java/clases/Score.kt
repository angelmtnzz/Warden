package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score (
    @PrimaryKey
    val user: String,
    var score: Double
)