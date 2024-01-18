package clases;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    override var name: String,
    var director: String,
    var lenght: Int,
    var genre: String,
    override var cover: Int,
    override var favourite: Boolean,
    override var status: ConsumptionStatus


) : Title(0, name, cover, favourite, status) {

}
