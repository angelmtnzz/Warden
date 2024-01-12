package vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.clases.R
import java.clases.databinding.FragmentLibraryBinding


class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //binding.tvTexto.text = "Fragment Library"

        return inflater.inflate(R.layout.fragment_library, container, false)
    }

}