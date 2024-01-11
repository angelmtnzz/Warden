package vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import java.clases.R
import java.clases.databinding.FragmentMainPageBinding
import android.util.Log


class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding   //Para el bottom menu
    private lateinit var carouselRecyclerView: RecyclerView //Para el carousel
    private val images = listOf(R.drawable.cyberpunkedgerunners, R.drawable.onepiececover, R.drawable.scottpilgrimcover, R.drawable.theofficecover) //Imagenes del carousel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        setupCarouselRecyclerview()
        carouselRecyclerView.setOnClickListener {
            Log.d("Warden", "PULSADO")
        }
        Log.d("Warden", "funciona")
        return binding.root
    }

    /**
     *  Debe de crear el RecyclerView y configurar el adaptador
     */
    private fun setupCarouselRecyclerview() {

        Log.d("Warden", "Se crea el setup de carousel")
        carouselRecyclerView = binding.carouselRecyclerView

        // Configura el adaptador antes de configurar el LayoutManager y SnapHelper
        carouselRecyclerView.adapter = CarouselAdapter(images)

        // Luego, configura el LayoutManager y el SnapHelper
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        carouselRecyclerView.layoutManager = layoutManager
        CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        Log.d("Warden", "fin de setup")
    }

}