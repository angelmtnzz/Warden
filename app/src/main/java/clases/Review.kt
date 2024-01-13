package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey
    val userNickname: String,
    var comment: String,
    val titleId: Int
)