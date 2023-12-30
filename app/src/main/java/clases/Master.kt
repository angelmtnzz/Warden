package clases

class Master(nickname: String, password: String, preferences: String, var stats: String): User(nickname, password, preferences) {
    /**
     * Metodo que añade una review a un titulo
     */
    fun  writeReview(review: String, title: Title){//TODO nose si este metodo seria mejor hacerlo dentro de la clase Titulo.
        title.reviews.add(review)
    }
}