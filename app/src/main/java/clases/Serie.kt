package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Serie(
    var numberSeasons: Int,
    var director: String,
    var lenght: Int,
    var genre: String,
    var cover: Int,
    override var name: String,

) : Title( 0, name=name) {}