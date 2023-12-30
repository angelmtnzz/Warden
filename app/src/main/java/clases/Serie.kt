package clases

class Serie(
    name: String,
    review: String,
    score: Int,
    var numberSeasons: Int,
    var director: String,
    var genre: String,
    var characters: ArrayList<Character>
) : Title(name, review, score) {
    /**
     * Metodo que devuelve el numero de temporadas que tiene la serie
     */
    fun getNumberSeasons(): Int {
        return this.numberSeasons
    }

    /**
     * Metodo que devuelve el nombre del director de laserie
     */
    fun getDirector(): String {
        return this.director
    }

    /**
     * Metodo que devuelve el genero de la serie
     */
    fun getGenre(): String {
        return this.genre
    }

    /**
     * Metodo que devuelve el numero de actores de las serie
     */
    fun getnumCharacters(): Int{
        return this.characters.size
    }

    /**
     * Metodo que nos devuelve el actor que se encuentra en la posicion n
     * @param n posicion en la que seencuentra el actor
     * @return character en la posicion n
     */
    fun getCharacter(n:Int): Character{
        return this.characters.get(n)
    }
}