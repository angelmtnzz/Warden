package clases

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Book(
    override var name: String,
    var author: String,
    var pages: Int,
    var genre: String,
    override var cover: Int,
    override var favourite: Boolean,
    override var status: ConsumptionStatus

    ) : Title( 0, name, cover, favourite, status) {

}