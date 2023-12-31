package vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.clases.R
import java.clases.databinding.FragmentExtraBinding

private lateinit var binding: FragmentExtraBinding
class ExtraFragment : Fragment(R.layout.fragment_extra) {

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentExtraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_extra, container, false)
    }


}