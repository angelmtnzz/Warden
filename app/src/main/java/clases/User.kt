package clases

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["nickname", "email"],
    unique = true)])
open class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nickname: String,
    var name: String,
    var apellidos: String,
    var email: String,
    var password: String,
    var range: Boolean,
    var preferences: String?,

    ) {

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

class Common(
    nickname: String,
    name: String,
    apellidos: String,
    email: String,
    password: String,
    range: Boolean,
    preferences: String?,
) : User(0, nickname, name, apellidos, email, password, false, preferences) {

}

class Master(
    nickname: String,
    name: String,
    apellidos: String,
    email: String,
    password: String,
    range: Boolean,
    preferences: String?,
) : User(0, nickname, name, apellidos, email, password, false, preferences) {

    /* Metodo que añade una review a un titulo
    */

}
