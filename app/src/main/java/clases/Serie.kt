package clases

import androidx.room.Entity

@Entity
data class Serie(
    var numberSeasons: Int,
    var director: String,
    var genre: String,
    val titleId: Int
) /* {
    /**
     * Metodo que devuelve el numero de actores de las serie.
     */
    fun getnumActors(): Int{
        return this.characters.size
    }

    /**
     * Metodo que nos devuelve el actor que se encuentra en la posicion n.
     * @param n posicion en la que seencuentra el actor
     * @return character en la posicion n
     */
    /*fun getActor(n:Int): Character{
        return this.characters.get(n)
    }
}*/