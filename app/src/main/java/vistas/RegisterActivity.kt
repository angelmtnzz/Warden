package vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import clases.User
import database.WardenDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelos.UserDao
import java.clases.R
import java.clases.databinding.ActivityLoginBinding
import java.clases.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerButton: Button
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()

        register()



    }

    private fun register() {

        registerButton.setOnClickListener{
            var nickname = binding.registerUserUsername.text.toString()
            var name = binding.registerUserName.text.toString()
            var surname = binding.registerUserSurname.text.toString()
            var email = binding.registerUserEmail.text.toString()
            var password = binding.registerUserPassword.text.toString()
            var confirmedPassword = binding.registerUserConfirmedPassword.text.toString()

            var user = User(0,nickname, name, surname, email,confirmedPassword, false, "" )

            lifecycleScope.launch {
                userDao.insertUser(user)
            }

            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun initComponents() {
        registerButton = binding.registerRegisterButton
        userDao = WardenDatabase.getDatabase(this).userDao()
    }
}