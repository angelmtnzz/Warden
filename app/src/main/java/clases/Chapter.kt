package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chapter(
    @PrimaryKey
    var chapterNumber: Int,
    var duration: Int
) {
    /**
     * Metodo que devuelve la duración de un capitulo, en minutos.
     */
}
