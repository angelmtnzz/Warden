package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Book
import clases.Title

data class TitleAndBook (
    @Embedded var title: Title,
    @Relation(
        parentColumn = "name",
        entityColumn = "name"
    )
    var book: Book
)