package clases.relations

import androidx.room.Embedded
import androidx.room.Relation
import clases.Book
import clases.Title

data class TitleAndBook (
    @Embedded var title: Title,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId"
    )
    var book: Book
)