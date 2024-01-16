package vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.clases.R
import java.clases.databinding.ActivityLoginBinding
import java.clases.databinding.ActivityRegisterBinding
import java.clases.databinding.FragmentRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = binding.registerUserName.text.toString()
        val userSurname = binding.registerUserSurname.text.toString()
        val userUsername = binding.registerUserUsername.text.toString()
        val email = binding.registerUserEmail.text.toString()
        val password = binding.registerUserPassword.text.toString()
        val confirmedPassword = binding.registerUserConfirmedPassword.text.toString()


    }
}