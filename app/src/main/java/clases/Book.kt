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
    var cover: Int,


    ) : Title( 0, name=name) {

}