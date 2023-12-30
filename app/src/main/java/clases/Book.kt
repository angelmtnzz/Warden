package clases

class Book(
    name: String,
    reviews: ArrayList<String>,
    score: Int,
    var author: String,
    var pages: Int,
    var genre: String
) : Title(name, reviews, score) {



}