package clases

class Master(nickname: String, password: String, preferences: String) :
    User(nickname, password, true, preferences) {

    /* Metodo que añade una review a un titulo
    */
    fun  writeReview(review: Review, title: Title){// nose si este metodo seria mejor hacerlo dentro de la clase Titulo.
        title.reviews.add(review)
    }
}
