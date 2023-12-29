package clases

class Book(
    name: String,
    review: String,
    score: Int,
    var author: String,
    var pages: Int,
    var genre: String
) : Title(name, review, score) {

    fun getPages(): Int {
        return this.pages
    }

    fun getAuthor(): String {
        return this.author
    }

    fun getGenre(): String {
        return this.genre
    }


}