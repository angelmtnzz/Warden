package vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.clases.R
import java.clases.databinding.FragmentMainPageBinding

class SearchFragment : Fragment(R.layout.fragment_search){

    private lateinit var binding : FragmentMainPageBinding

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        /*binding.etFilter.addTextChangedListener{

        }*/
    }

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstancesState: Bundle?): View? {
        binding.tvTexto.text= "Search Fragment"
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}