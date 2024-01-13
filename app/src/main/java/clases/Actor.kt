package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Actor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var surname: String,
    var age: Int
)