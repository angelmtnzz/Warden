import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.clases.databinding.FragmentLoginBinding

private lateinit var binding: FragmentLoginBinding
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            // Lógica de inicio de sesión
            val username = binding.userName.text.toString()
            val password = binding.userPassword.text.toString()

        }

        binding.registerButton.setOnClickListener {
            // Lógica de registro
        }
    }
}