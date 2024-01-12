package vistas

import android.content.Intent
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
import java.clases.databinding.ItemConsumiendoBinding
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.carousel.UncontainedCarouselStrategy
import java.clases.databinding.FragmentLibraryBinding


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
        val adapter = ItemConsumingAdapter(itemsConsuming)
        binding.rvConsumiendo.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.rvConsumiendo.layoutManager = layoutManager
        return binding.root
    }

    /**
     *  Debe de crear el RecyclerView y configurar el adaptador
     */
    private fun setupCarouselRecyclerview() {

        carouselRecyclerView = binding.carouselRecyclerView

        // Configura el adaptador antes de configurar el LayoutManager y SnapHelper
        carouselRecyclerView.adapter = CarouselAdapter(images)

        // Luego, configura el LayoutManager y el SnapHelper

        val layoutManager = CarouselLayoutManager() //Añadir estrategia dentro de este parentesis
        layoutManager.itemCount
        carouselRecyclerView.layoutManager = layoutManager

        CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
    }
    data class ItemConsuming(val image: Int, val title: String, val subtitle: String)
    val itemsConsuming = listOf(
        ItemConsuming(R.drawable.tlotrcover, "TLOTR: Fellowship of the Ring", "54/432 Pages"),
    )
    // Use a separate binding class for your item layout
    class ItemConsumingAdapter(private val items: List<ItemConsuming>) : RecyclerView.Adapter<ItemConsumingAdapter.ItemconsumingViewHolder>() {

        inner class ItemconsumingViewHolder(private val binding: ItemConsumiendoBinding) : RecyclerView.ViewHolder(binding.root) {
            // Use the generated binding instead of finding views by ID
            val imageView: ImageView = binding.itemConsumingCover
            val titleView: TextView = binding.itemConsumingTitle
            val subtitleView: TextView = binding.itemConsumingSubtitle
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemconsumingViewHolder {
            val binding = ItemConsumiendoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemconsumingViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ItemconsumingViewHolder, position: Int) {
            val itemConsuming = items[position] // Use the passed itemConsuming
            holder.imageView.setImageResource(itemConsuming.image)
            holder.titleView.text = itemConsuming.title
            holder.subtitleView.text = itemConsuming.subtitle

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, BooksLibraryPage::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}