package clases

class Book(
    name: String,
    reviews: ArrayList<String>,
    score: Int,
    var author: String,
    var pages: Int,
    var genre: String
) : Title(name, reviews, score) {
    /**
     * Metodo que devuelve el numero de paginas que tiene el libro
     */
    fun getPages(): Int {
        return this.pages
    }

    /**
     * Metodo que devuelve el autor del libro
     */
    fun getAuthor(): String {
        return this.author
    }

    /**
     * Metodo que devuelve el genero de llibro
     */
    fun getGenre(): String {
        return this.genre
    }


}