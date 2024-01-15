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

        binding.registerRegisterButton.setOnClickListener {
            // Lógica de registro
            val userName = binding.registerUserName.text.toString()
            val userSurname = binding.registerUserSurname.text.toString()
            val userUsername = binding.registerUserUsername.text.toString()
            val email = binding.registerUserEmail.text.toString()
            val password = binding.registerUserPassword.text.toString()
            val confirmedPassword = binding.registerUserConfirmedPassword.text.toString()

        }
    }
}