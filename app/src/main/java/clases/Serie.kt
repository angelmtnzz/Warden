package clases

class Serie(name:String, review:String, score:Int, var numberSeasons:Int, var director:String, var genre:String): Title(name, review, score) {
    fun getNumberSeasons(): Int {
        return this.numberSeasons
    }

    fun getDirector(): String {
        return this.director
    }

    fun getGenre(): String {
        return this.genre
    }
}