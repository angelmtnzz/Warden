package clases

class Title {
    var name:String
    var review:String
    var score:Int = 0 //Por defecto se incializa a 0.

    constructor(name:String, review:String, score:Int){
        this.name = name
        this.review = review
        if (score>=0 && score<=5) this.score = score;
    }

    /**
     * Metodo que devuelve el nombre del título
     * @return this.name
     */
    public fun getName():String{ return this.name }

    /**
     * Metodo que devuelve la puntuación del título
     * @return this.score
     */
    public fun getScore():Int{ return this.score }
}