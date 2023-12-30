package clases

open class Title(var name: String, var reviews: ArrayList<String>) {

    var score: Int = 0 //Si no se especifica se inicializa por defecto a 0.


    constructor(name: String, reviews: ArrayList<String>, score: Int) : this(name, reviews) {
        this.name = name
        this.reviews = reviews
        if (score >= 0 && score <= 5) this.score = score;
    }


    /**
     * Metodo que devuelve el nombre del título
     * @return this.name
     */
    fun getName(): String {
        return this.name
    }

    /**
     * Metodo que devuelve la puntuación del título
     * @return this.score
     */
    fun getScore(): Int {
        return this.score
    }
}