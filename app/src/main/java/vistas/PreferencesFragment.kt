
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.clases.databinding.FragmentPreferencesBinding

private lateinit var binding: FragmentPreferencesBinding
class PreferencesFragment : Fragment() {

    val preferencias = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Agrega un listener a cada botón para manejar la lógica de selección
        val buttons = listOf(
            binding.preferenciaFiccion,
            binding.preferenciaAventura,
            binding.preferenciaAccion,
            binding.preferenciaFantasia,
            binding.preferenciaDrama,
            binding.preferenciaComedia,
            binding.preferenciaTerror,
            binding.preferenciaMisterio,
            binding.preferenciaRomance,
            binding.preferenciaThriller,
            binding.preferenciaCrimen,
            binding.preferenciaHistoriaReal,
            binding.preferenciaAnimacion,
            binding.preferenciaDocumental,
            binding.preferenciaSuperheroes,
            binding.preferenciaViajes,
            binding.preferenciaDeportes,
            binding.preferenciaGuerra,
            binding.preferenciaWestern,
            binding.preferenciaPsicologia,
            binding.preferenciaCrecimientoPersonal,
            binding.preferenciaCocina,
            binding.preferenciaAnimales,
            binding.button24,
            binding.button25,
            binding.button26,
            binding.button27,
            binding.button28,
            binding.button29,
            binding.button30,
            binding.button31,
            binding.button32
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                // Lógica de selección y cambio de color
                toggleButton(button)
            }
        }

        // Listener al botón de registro
        binding.preferenciasRegisterButton.setOnClickListener {
            // Lógica para manejar el registro con las preferencias seleccionadas
            // Guardamos la lista preferencias en el usuario
        }
    }

    private fun toggleButton(button: Button) {
        // Implementa la lógica para cambiar el color del botón al ser seleccionado
        if (button.isSelected) {
            button.setBackgroundColor(10485723)
            preferencias.remove(button.id.toString())
            button.isSelected = false
        } else {
            button.setBackgroundColor(36213)
            preferencias.add(button.id.toString())
            button.isSelected = true
        }
    }
}
