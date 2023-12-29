package clases

open class Title(var name: String, var review: String) {

    var score: Int = 0 //Si no se especifica se inicializa por defecto a 0.


    constructor(name: String, review: String, score: Int) : this(name, review) {
        this.name = name
        this.review = review
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