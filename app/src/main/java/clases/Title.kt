package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Title(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var reviews: ArrayList<Review>,
    var score: Double


) {
}

