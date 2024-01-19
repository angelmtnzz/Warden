package vistas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.clases.R
import java.clases.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileConfiguration: Button
    //private lateinit var nameTextView: TextView     //TvNameTitle
    //private lateinit var name: String               //title
    //private lateinit var buttonMenu: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Accede al Intent de la Activity asociada al Fragment
        val intentFromActivity = activity?.intent

        // Ahora puedes usar intentFromActivity para obtener extras, etc.
        val nickname = intentFromActivity?.getStringExtra("nickname")
        val userName = intentFromActivity?.getStringExtra("name")
        val userSurname = intentFromActivity?.getStringExtra("apellidos")
        val completeName = "userName" + " " + "userSurname"

        binding.profileUsername.text = nickname
        binding.profileName.text = completeName
        //binding.profileStatus

        return binding.root
    }

}