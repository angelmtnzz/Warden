package clases

open class Title(var name: String) {

    var reviews: String = " "
    var score: Float = 0f //Si no se especifica se inicializa por defecto a 0.


    constructor(name: String, reviews: String, score: Float) : this(name) {
        this.name = name
        this.reviews = reviews
        if (score in 0.0..5.0) this.score = score;
    }

    constructor(name: String, score: Float) : this(name) {
        this.name = name
        if (score in 0.0..5.0) this.score = score;
    }


}