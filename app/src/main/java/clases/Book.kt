package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey
    val titleId: Int,
    var author: String,
    var pages: Int,
    var genre: String,

)/* : Title(name, reviews, score) {



}*/