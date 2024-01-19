package vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
import clases.User
import com.google.android.material.snackbar.Snackbar
import database.WardenDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelos.UserDao
import java.clases.R
import java.clases.databinding.ActivityLoginBinding
import java.clases.databinding.ActivityConfigurationBinding
import java.clases.databinding.ActivityRegisterBinding

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding

    private lateinit var registerButton: Button
    private lateinit var userDao: UserDao
    private lateinit var etNickname: EditText
    private lateinit var etCurrentPassword: EditText
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmNewPassword: EditText
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initComponents()

        val nick = intent.extras?.getString("nickname")

        setUserParams(nick.toString())

        configure()

    }

    private fun setUserParams(nick: String) {
        user = User(0, "", "", "", "", "", false, "")

        lifecycleScope.launch {
            user = withContext(Dispatchers.IO) {
                userDao.getUserByNickname(nick)

            }
            etNickname.setText(user.nickname)
            etCurrentPassword.setText(user.password)
            etName.setText(user.name)
            etSurname.setText(user.apellidos)
            etEmail.setText(user.email)
        }

    }

    private fun configure() {


        registerButton.setOnClickListener {
            var nickname = etNickname.text.toString()
            var currentPassword = etCurrentPassword.text.toString()
            var name = etName.text.toString()
            var surname = etSurname.text.toString()
            var email = etEmail.text.toString()
            var newPassword = etNewPassword.text.toString()
            var confirmedNewPassword = etConfirmNewPassword.text.toString()

            if (nickname.isNotEmpty() &&
                currentPassword.isNotEmpty() &&
                name.isNotEmpty() &&
                surname.isNotEmpty() &&
                email.isNotEmpty() &&
                newPassword.isEmpty() &&
                confirmedNewPassword.isEmpty()
            ) {
                finish()
            } else if (
                nickname.isNotEmpty() &&
                currentPassword.isNotEmpty() &&
                name.isNotEmpty() &&
                surname.isNotEmpty() &&
                email.isNotEmpty() &&
                newPassword.isNotEmpty() &&
                confirmedNewPassword.isNotEmpty()
            ) {

                if (confirmedNewPassword == newPassword) {

                    /*var user =
                        User(0, nickname, name, surname, email, confirmedNewPassword, false, "")*/

                    lifecycleScope.launch {
                        try {
                            if(userDao.doesUserExist(nickname) > 0){
                                userDao.updateUserByNickname(nickname, name, surname, email, confirmedNewPassword)
                                finish()
                            }
                            else{
                                Log.d("conf", "configure: usuario no existe")
                            }


                            // Puedes cerrar la actividad después de una configuración exitosa
                            //finish()

                            // O puedes navegar a otra actividad si es necesario
                            // val intent = Intent(this@ConfigurationActivity, OtraActivity::class.java)
                            // startActivity(intent)
                        } catch (e: Exception) {
                            Log.d("conf", "configure:error al actuliazar ")
                        }
                    }

                } else {
                    setSnackBar("No se cumple la verificacion de nueva contraseña")
                }

            } else{
                setSnackBar("Campos obligatorios no especificados")
            }


        }
    }

    private fun setSnackBar(msg: String) {
        val mySnackbar = Snackbar.make(
            binding.root,
            msg,
            Snackbar.LENGTH_LONG
        ).setAnchorView(binding.configImage)

        val view: View = mySnackbar.view
        val params = view.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        params.setMargins(100, 50, 100, 0)
        view.layoutParams = params

        mySnackbar.show()
    }

    private fun initComponents() {

        userDao = WardenDatabase.getDatabase(this).userDao()
        registerButton = binding.configRegisterButton
        etNickname = binding.configNickname
        etCurrentPassword = binding.configCurrentPassword
        etName = binding.configUserName
        etSurname = binding.configUserSurname
        etEmail = binding.configUserEmail
        etNewPassword = binding.configUserPassword
        etConfirmNewPassword = binding.configUserConfirmedPassword
    }


}