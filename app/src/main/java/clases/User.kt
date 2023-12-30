package clases

open class User(var nickname: String, var password: String, var preferences: String) {//TODO Hay que mirar como añadimos la foto de perfil al usuario.
    fun editpreferences(newPreferences: String){
        this.preferences = newPreferences
        //TODO Esto hay que mirar como lo acabamos poniendo, por que una String me parece raro, veo mejor un enum de generos.
    }

    /**
     * Metodo que cambia el nombre y/o contraseña de un perfil.
     */
    fun editProfile(newNickname: String, newPassword: String){
        if(newNickname!=null){setNickname(newNickname)}
        if(newPassword!=null){setPassword(newPassword)}
    }

    /**
     * Metodo que cambia la contraseña de un perfil.
     */
    private fun setPassword(newPassword: String){
        this.password = newPassword
    }

    /**
     * Metodo que cambia el nickname de un perfil.
     */
    private fun setNickname(newNickname: String){
        this.nickname = newNickname
    }
}