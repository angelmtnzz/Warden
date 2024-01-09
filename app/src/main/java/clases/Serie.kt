package clases

class Serie(
    name: String,
    reviews: ArrayList<String>,
    score: Int,
    var numberSeasons: Int,
    var director: String,
    var genre: String,
    var characters: ArrayList<Character>
) /*: Title(name, reviews, score) {
    /**
     * Metodo que devuelve el numero de actores de las serie.
     */
    fun getnumCharacters(): Int{
        return this.characters.size
    }

    /**
     * Metodo que nos devuelve el actor que se encuentra en la posicion n.
     * @param n posicion en la que seencuentra el actor
     * @return character en la posicion n
     */
    /*fun getCharacter(n:Int): Character{
        return this.characters.get(n)
    }
}*/