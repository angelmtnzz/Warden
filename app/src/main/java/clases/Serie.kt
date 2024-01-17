package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Serie(
    override var name: String,
    var numberSeasons: Int,
    var director: String,
    var genre: String,
    override var cover: Int,
    override var favourite: Boolean

) : Title( 0, name, cover, favourite) {}