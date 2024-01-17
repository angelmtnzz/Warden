package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Title(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    open var name: String,
    open var cover: Int,
    open var favourite: Boolean
) {
}

