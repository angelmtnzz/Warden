package vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.clases.R
import java.clases.databinding.FragmentConfigurationBinding

class ConfigurationFragment : Fragment(R.layout.fragment_configuration) {
   private lateinit var binding: FragmentConfigurationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentConfigurationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuration, container, false)
    }
}