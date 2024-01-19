package vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        configure()

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
                name.isNotEmpty() &&
                surname.isNotEmpty() &&
                email.isNotEmpty() &&
                newPassword.isNotEmpty() &&
                confirmedNewPassword.isNotEmpty()
            ) {

                if(confirmedNewPassword == newPassword){

                    var user = User(0, nickname, name, surname, email, confirmedNewPassword, false, "")

                    lifecycleScope.launch {
                        userDao.updateUser(user)
                    }

                    navigateToLogin()
                }
                else {
                    setSnackBar("Las constraseñas no coinciden")
                }


            } else{
                setSnackBar("Debes rellenar todos los campos")
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
        params.setMargins(100, 200, 100, 0)
        view.layoutParams = params

        mySnackbar.show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun initComponents() {

        userDao = WardenDatabase.getDatabase(this).userDao()
        registerButton = binding.configRegisterButton
        etNickname = binding.configNickname
        etName = binding.configUserName
        etSurname = binding.configUserSurname
        etEmail = binding.configUserEmail
        etNewPassword = binding.configUserPassword
        etConfirmNewPassword = binding.configUserConfirmedPassword
    }



}