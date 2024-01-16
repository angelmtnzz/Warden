package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Serie(
    override var name: String,
    var numberSeasons: Int,
    var director: String,
    var lenght: Int,
    var genre: String,
    var cover: Int,

) : Title( 0, name=name) {}