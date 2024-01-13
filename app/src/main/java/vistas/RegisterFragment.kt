import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.clases.databinding.FragmentRegisterBinding

private lateinit var binding: FragmentRegisterBinding
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            // Lógica de registro
            val userName = binding.userName.text.toString()
            val userSurname = binding
            val userUsername = binding.userUsername.text.toString()
            val email = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()
            val confirmedPassword = binding.userConfirmedPassword.text.toString()

        }
    }
}