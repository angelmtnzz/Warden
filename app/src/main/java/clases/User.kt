package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var nickname: String,
    var password: String,
    var range: Int,
    var preferences: String) {
    // Hay que mirar como añadimos la foto de perfil al usuario.
    fun editpreferences(newPreferences: String) {
        this.preferences = newPreferences
        //Esto hay que mirar como lo acabamos poniendo, por que una String me parece raro, veo mejor un enum de generos.
    }

    /**
     * Metodo que cambia el nombre y/o contraseña de un perfil.
     */
    fun editProfile(newNickname: String, newPassword: String) {
        if (newNickname != null) {
            setNewNickname(newNickname)
        }
        if (newPassword != null) {
            setNewPassword(newPassword)
        }
    }

    /**
     * Metodo que cambia la contraseña de un perfil.
     */
    private fun setNewPassword(newPassword: String) {
        this.password = newPassword
    }

    /**
     * Metodo que cambia el nickname de un perfil.
     */
    private fun setNewNickname(newNickname: String) {
        this.nickname = newNickname
    }
}