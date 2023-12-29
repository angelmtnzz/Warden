package clases;

class Film(
    name: String,
    review: String,
    score: Int,
    var author: String,
    var pages: Int,
    var genre: String
) : Title(name, review, score) {
    fun getAuthor(): String {
        return this.author
    }

    fun getPages(): Int {
        return this.pages
    }

    fun getGenre(): String {
        return this.genre
    }

}
