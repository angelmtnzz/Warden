package vistas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder.DeathRecipient
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import clases.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.clases.R
import java.clases.databinding.ActivityRegisterBinding
import java.clases.databinding.ActivitySendEmailBinding
import java.lang.Exception
import javax.security.auth.Subject

class SendEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendEmailBinding
    private lateinit var etRecipient: EditText
    private lateinit var etSubject: EditText
    private lateinit var etMessage: EditText
    private lateinit var sendButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initComponents()
        setParams()
        initListeners()
    }

    private fun setParams() {

        etRecipient.setText("mfernm42@estudiantes.unileon.es")
        etSubject.setText("Upgrade Profile")
        etMessage.setText("I want to become a Master")
    }

    private fun initComponents() {
        etRecipient = binding.etEmailAddress
        etSubject = binding.etSubject
        etMessage = binding.etMessage
        sendButton = binding.emailSendButton
    }

    private fun initListeners() {
       sendButton.setOnClickListener{
           val recipient = etRecipient.text.toString().trim()
           val subject = etSubject.text.toString().trim()
           val message = etMessage.text.toString().trim()

           val intent = Intent(Intent.ACTION_SEND)

           intent.data = Uri.parse("mailto:")
           intent.type = "text/plain"
           intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
           intent.putExtra(Intent.EXTRA_SUBJECT, subject)
           intent.putExtra(Intent.EXTRA_TEXT, message)

           try {
               startActivity(Intent.createChooser(intent, "Send Email"))

           }catch (e: Exception){
               Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
           }
       }

    }
}