package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey
    var titleId: Int,
    var author: String,
    var pages: Int,
    var genre: String,
    var cover: Int

)/* : Title(name, reviews, score) {



}*/