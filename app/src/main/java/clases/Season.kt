package clases

class Season(var seasonNumber: Int, var chapters: ArrayList<Chapter>) {
    /**
     * Metodo que devuelve el numero de capitulos de la temporada.
     */
    fun getNumChapters(): Int{
        return this.chapters.size
    }

    /**
     * Metodo que devuelve un capitulo a partir de su numero.
     */
    fun getChapter(n: Int): Chapter{
        return this.chapters.get(n)
    }
}