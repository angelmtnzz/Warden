package clases

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Book(
    var author: String,
    var pages: Int,
    var genre: String,
    var cover: Int,
    override var name: String,

    ) : Title( 0, name=name) {

}