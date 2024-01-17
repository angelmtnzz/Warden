package vistas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import java.clases.R
import java.clases.databinding.FragmentMainPageBinding
import java.clases.databinding.ItemConsumiendoBinding
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import java.io.Serializable

class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var carouselRecyclerView: RecyclerView
    private lateinit var adapter: ItemConsumingAdapter

    //Para sacar dotos de los DAO
    private lateinit var bookdao: BookDao
    var itemsConsuming: MutableList<ItemConsuming> = mutableListOf()


    override fun onAttach(context: Context) {   // onAttach se ejecuta antes que onCreated, inicializamos la BBDD y el DAO aqui para que no haya problemas antes
        super.onAttach(context)
        // Inicializo el DAO
        bookdao = WardenDatabase.getDatabase(requireContext()).bookDao()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)

        setupCarouselRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rvConsumiendo.layoutManager = layoutManager

        lifecycleScope.launch {     // Hay que ejecutarlo tod0 dentro de la corrutina, si no lo salta y ejecuta al final
            val books = bookdao.getAllBooks()   // guardo el contenido de los libros en la variable books usando el metodo del DAO

            itemsConsuming = mutableListOf(
                ItemConsuming(books[0].cover, books[0].name, books[0].pages.toString(), books[0].author, books[0].favourite) // Asigno a itemconsuming los valores de book (en caso de ser mas de uno podemos hacerlo con un for)
            )

            if(itemsConsuming!=null){   //Importante poner el if para que no de nullpointer si hay algun error
                adapter = context?.let { ItemConsumingAdapter(itemsConsuming, it, R.layout.item_consumiendo) }!!
                binding.rvConsumiendo.adapter = adapter
            }
        }
    }

    private fun setupCarouselRecyclerView() {
        carouselRecyclerView = binding.carouselRecyclerView
        lifecycleScope.launch {
            val books = bookdao.getAllBooks()
            itemsConsuming.clear()

            for (i in 1 until bookdao.getNumBooks()) {
                itemsConsuming.add(ItemConsuming(books[i].cover, books[i].name, books[i].pages.toString(), books[i].author, books[i].favourite))
            }

            val adapter = context?.let {
                CarouselAdapter(itemsConsuming)
            }
            carouselRecyclerView.adapter = adapter
            val layoutManager = CarouselLayoutManager()
            layoutManager.scrollToPosition(0)
            carouselRecyclerView.layoutManager = layoutManager
            CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        }
    }


    data class ItemConsuming(val image: Int, val title: String, val subtitle: String, val author: String, val favourite: Boolean) : Serializable    // Serializable permite enviarlo a traves de un intent a otra activity



    // Use a separate binding class for your item layout
    class ItemConsumingAdapter(
        private val items: List<ItemConsuming>,
        private val context: Context,
        private val layoutResourceId: Int
    ) : RecyclerView.Adapter<ItemConsumingAdapter.ItemConsumingViewHolder>() {

        inner class ItemConsumingViewHolder(private val binding: ItemConsumiendoBinding) : RecyclerView.ViewHolder(binding.root) {
            val imageView: ImageView = binding.itemConsumingCover
            val titleView: TextView = binding.itemConsumingTitle
            val subtitleView: TextView = binding.itemConsumingSubtitle

            fun bind(itemConsuming: ItemConsuming) {
                imageView.setImageResource(itemConsuming.image)
                titleView.text = itemConsuming.title
                subtitleView.text = itemConsuming.subtitle
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemConsumingViewHolder {
            val binding = ItemConsumiendoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemConsumingViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ItemConsumingViewHolder, position: Int) {
            val itemConsuming = items[position]
            holder.bind(itemConsuming)

            holder.itemView.setOnClickListener {
                val intent = Intent(context, TitlePageActivity::class.java)
                intent.putExtra("itemConsuming", itemConsuming)
                intent.putExtra("cover", itemConsuming.image)
                intent.putExtra("name", itemConsuming.title)
                intent.putExtra("pages", itemConsuming.subtitle)
                intent.putExtra("author", itemConsuming.author)
                intent.putExtra("favourite", itemConsuming.favourite)
                intent.putExtra("itemConsuming", itemConsuming)
                context.startActivity(intent)
            }
        }


        override fun getItemCount(): Int {
            return items.size
        }
    }
}
