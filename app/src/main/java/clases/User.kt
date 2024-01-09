package clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nickname: String,
    var password: String,
    var range: Boolean,
    var preferences: String,


    ) {
    constructor(nickname: String, password: String, range: Boolean, preferences: String) : this(
        0,
        nickname,
        password,
        range,
        preferences
    )

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
            this.nickname = newNickname
        }
        if (newPassword != null) {
            this.password = newPassword
        }
    }


}