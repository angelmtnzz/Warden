package modelos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import clases.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Query("UPDATE User SET name = :newName, apellidos = :newSurname, email = :newEmail, password = :newPassword WHERE nickname = :nickname")
    suspend fun updateUserByNickname(
        nickname: String,
        newName: String,
        newSurname: String,
        newEmail: String,
        newPassword: String
    )

    @Query("SELECT COUNT(*) FROM User WHERE nickname = :nickname")  // Comprueba si existe un libro por su nombre
    suspend fun doesUserExist(nickname: String): Int

    @Query("SELECT * FROM User WHERE nickname = :nickname")
    suspend fun getUserByNickname(nickname: String) : User
    
    @Query("SELECT * FROM User ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}