package clases;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    @PrimaryKey
    val titleId: Int,
    var director: String,
    var lenght: Int,
    var genre: String
) /*: Title(name, reviews, score) {
    /**
     * Metodo que devuelve el numero de actores de la pelicula.
     */
    fun getnumCharacters(): Int{
        return this.characters.size
    }

    /**
     * Metodo que nos devuelve el actor que se encuentra en la posicion n.
     * @param n posicion en la que seencuentra el actor
     * @return character en la posicion n
     */
    fun getCharacter(n:Int): Character{
        return this.characters.get(n)
    }

}
*/