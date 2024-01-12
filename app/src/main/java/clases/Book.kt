package clases

import androidx.room.Entity

@Entity
data class Book(
    var author: String,
    var pages: Int,
    var genre: String,
    val titleId: Int
)/* : Title(name, reviews, score) {



}*/