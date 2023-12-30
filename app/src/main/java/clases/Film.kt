package clases;

class Film(
    name: String,
    review: String,
    score: Int,
    var director: String,
    var lenght: Int,
    var genre: String
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

}
