package clases

class Chapter(var lenght: Int, var chapterNumber: Int) {
    /**
     * Metodo que devuelve la duración de un capitulo, en minutos.
     */
    fun getLenght(): Int{
        return this.lenght
    }
    fun getChapterNumber(): Int{
        return this.chapterNumber
    }
}