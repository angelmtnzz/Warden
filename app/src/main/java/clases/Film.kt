package clases;

class Film(
    name: String,
    review: String,
    score: Int,
    var director: String,
    var lenght: Int,
    var genre: String,
    var characters: ArrayList<Character>
) : Title(name, review, score) {
    /**
     * Metodo que devuelve el director del libro.
     */
    fun getDirector(): String {
        return this.director
    }

    /**
     * Metodo que devuelve la duración de la pelicula de la pelicula en minutos.
     */
    fun getLenght(): Int {
        return this.lenght
    }

    /**
     * Metodo que devueve el genero de la pelicula
     */
    fun getGenre(): String {
        return this.genre
    }
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
