package clases

class Character(var name:String, var surname:String) {
    /**
     * Metodo que devuelve el nombre del actor
     */
    fun getName():String{
        return this.name
    }

    /**
     * Metodo que devuelve el apellido del actor
     */
    fun getSurname():String{
        return this.surname
    }
}