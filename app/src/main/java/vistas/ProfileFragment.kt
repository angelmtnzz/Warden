package vistas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import clases.User
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.UserDao
import java.clases.R
import java.clases.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileConfiguration: Button
    private lateinit var userDao: UserDao
    private lateinit var logoutButton: Button
    private lateinit var upgradeButton: Button
    private lateinit var user: User
    //private lateinit var nameTextView: TextView     //TvNameTitle
    //private lateinit var name: String               //title
    //private lateinit var buttonMenu: ImageButton

    override fun onAttach(context: Context) {   // onAttach se ejecuta antes que onCreated, inicializamos la BBDD y el DAO aqui para que no haya problemas antes
        super.onAttach(context)
        // Inicializo el DAO
        userDao = WardenDatabase.getDatabase(requireContext()).userDao()    //Borrar


    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initComponents()
        // Accede al Intent de la Activity asociada al Fragment
        val intentFromActivity = activity?.intent

        // Ahora puedes usar intentFromActivity para obtener extras, etc.
        val nickname = intentFromActivity?.getStringExtra("nickname")

        user = User(0, "", "", "", "", "", false, "")
        if (!nickname.isNullOrEmpty()) {
            lifecycleScope.launch {
                if (userDao.doesUserExist(nickname.toString()) == 1) {
                    user = userDao.getUserByNickname(nickname.toString())
                    binding.profileUsername.text = nickname
                    binding.profileUserUsername.text =
                        getString(R.string.username_usersurname, user.name, user.apellidos)
                    if (user.range != false) {
                        binding.profileStatus.text = "BASICO"
                    } else {
                        binding.profileStatus.text = "MAESTRO"
                    }
                }
            }
        }

        return binding.root
    }

    private fun initComponents() {
        logoutButton = binding.profileLogout
        profileConfiguration = binding.profileConfiguration
        upgradeButton = binding.profileUpgrade
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val intentFromActivity = activity?.intent

        // Ahora puedes usar intentFromActivity para obtener extras, etc.
        val nickname = intentFromActivity?.getStringExtra("nickname")
        initListeners(nickname.toString())
    }

    private fun initListeners(nickname: String) {
        logoutButton.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        profileConfiguration.setOnClickListener{
            val intent = Intent(activity, ConfigurationActivity::class.java)
            intent.putExtra("nickname", nickname)
            startActivity(intent)
        }
        upgradeButton.setOnClickListener{
            val intent = Intent(activity, SendEmailActivity::class.java)
            startActivity(intent)
        }
    }


}