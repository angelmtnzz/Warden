package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Title(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    open var name: String,
    open var cover: Int,
    open var favourite: Boolean,
    open var status: ConsumptionStatus
) {
}

/**
 * Crea un nuevo título a partir de un Book, Serie o film que se le pase como argumento
 * el titulo creado mantendra los atributos nombre, cover y favourite pero perdera los demas
 * atributos propios de su clase.
 */
fun createTitleFromMedia(media: Title): Title {
    return Title(0, media.name, media.cover, media.favourite, media.status)
}

/**
 * Clase enum que contiene los tres valores posibles de Status
 */
enum class ConsumptionStatus {
    CONSUMED,
    CONSUMING,
    TO_CONSUME
}

