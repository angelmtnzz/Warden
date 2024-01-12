package clases;

import androidx.room.Entity

@Entity
data class Film(
    var director: String,
    var lenght: Int,
    var genre: String,
    val titleId: Int,
    var actors: ArrayList<Actor>
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