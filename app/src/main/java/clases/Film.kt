package clases;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    override var name: String,
    var director: String,
    var lenght: Int,
    var genre: String,
    var cover: Int,

) : Title(0, name=name) {

}
