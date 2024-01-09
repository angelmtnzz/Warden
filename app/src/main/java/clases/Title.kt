package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Title(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var reviews: MutableList<Review>,
    var score: Double


) {
    constructor(name: String, reviews: MutableList<Review>, score: Double) : this(0, name, reviews, score)


}

data class Review(
    val usuario: String,
    var comentario: String
)