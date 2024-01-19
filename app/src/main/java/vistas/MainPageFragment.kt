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
import clases.Book
import clases.ConsumptionStatus
import clases.Film
import clases.Title
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import modelos.TitleDao
import java.io.Serializable

class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var carouselRecyclerView: RecyclerView
    private lateinit var adapter: ItemConsumingAdapter

    //Para sacar dotos de los DAO
    private lateinit var bookdao: BookDao   //Borrar
    private lateinit var titledao: TitleDao
    var itemsConsuming: MutableList<ItemConsuming> = mutableListOf()
    var itemsConsumingList: MutableList<ItemConsuming> = mutableListOf()

    override fun onAttach(context: Context) {   // onAttach se ejecuta antes que onCreated, inicializamos la BBDD y el DAO aqui para que no haya problemas antes
        super.onAttach(context)
        // Inicializo el DAO
        bookdao = WardenDatabase.getDatabase(requireContext()).bookDao()    //Borrar

        titledao = WardenDatabase.getDatabase(requireContext()).titleDao()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)

        // Accede al Intent de la Activity asociada al Fragment
        val intentFromActivity = activity?.intent

        // Ahora puedes usar intentFromActivity para obtener extras, etc.
        val nickname = intentFromActivity?.getStringExtra("nickname")

        binding.tvBienvenida.text = getString(R.string.Bienvenida, nickname)

        setupCarouselRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rvConsumiendo.layoutManager = layoutManager

        lifecycleScope.launch {     // Hay que ejecutarlo tod0 dentro de la corrutina, si no lo salta y ejecuta al final
            val titles = titledao.getTitlesByStatus(ConsumptionStatus.CONSUMING)    // Devuelve los Titles que se estan consumiendo
            itemsConsumingList.clear()
            Log.d("WARDEN", "LISTA=$titles")
            for (i in 0..titles.size-1) {
                if (titledao.doesBookExist(titles[i].name) == 1) {  // Si existe un libro con ese nombre
                    val book = titledao.getBook(titles[i].name) // Cojo el libro
                    itemsConsumingList.add(
                        ItemConsuming(
                            book.cover,
                            book.name,
                            book.pages.toString(),
                            book.author,
                            book.favourite
                        )
                    )    // Lo inserto
                }
                if (titledao.doesFilmExist(titles[i].name) == 1) {  // Si existe una pelicula con ese nombre
                    val film = titledao.getFilm(titles[i].name) // Cojo la pelicula
                    itemsConsumingList.add(
                        ItemConsuming(
                            film.cover,
                            film.name,
                            film.lenght.toString(),
                            film.director,
                            film.favourite
                        )
                    )    // La inserto
                }
                if (titledao.doesSerieExist(titles[i].name) == 1) {  // Si existe una serie con ese nombre
                    val serie = titledao.getSerie(titles[i].name) // Cojo la serie
                    itemsConsumingList.add(
                        ItemConsuming(
                            serie.cover,
                            serie.name,
                            serie.numberSeasons.toString(),
                            serie.director,
                            serie.favourite
                        )
                    )    // La inserto
                }
            }
            Log.d("WARDEN", "LISTA=$itemsConsuming")
            if(itemsConsumingList != null) {
                adapter = context?.let { ItemConsumingAdapter(itemsConsumingList, it, R.layout.item_consumiendo) }!!
                binding.rvConsumiendo.adapter = adapter
                adapter.notifyDataSetChanged()
            } else {
                // If 'itemsConsuming' is null, clear the adapter and notify it of the change
                itemsConsumingList.clear()
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupCarouselRecyclerView() {
        carouselRecyclerView = binding.carouselRecyclerView
        lifecycleScope.launch {
            val books = bookdao.getAllBooks()
            val titles: List<Title> = titledao.getAllTitles()



            itemsConsuming.clear()

            for (i in 0 until titledao.getNumTitles()-1) {   // Recorro la lista de titulos EMPEZANDO EN 0
                if (titledao.doesBookExist(titles[i].name) == 1) {  // Si existe un libro con ese nombre
                    val book = titledao.getBook(titles[i].name) // Cojo el libro
                    itemsConsuming.add(
                        ItemConsuming(
                            book.cover,
                            book.name,
                            book.pages.toString(),
                            book.author,
                            book.favourite
                        )
                    )    // Lo inserto
                }
                if (titledao.doesFilmExist(titles[i].name) == 1) {  // Si existe una pelicula con ese nombre
                    val film = titledao.getFilm(titles[i].name) // Cojo la pelicula
                    itemsConsuming.add(
                        ItemConsuming(
                            film.cover,
                            film.name,
                            film.lenght.toString(),
                            film.director,
                            film.favourite
                        )
                    )    // La inserto
                }
                if (titledao.doesSerieExist(titles[i].name) == 1) {  // Si existe una serie con ese nombre
                    val serie = titledao.getSerie(titles[i].name) // Cojo la serie
                    itemsConsuming.add(
                        ItemConsuming(
                            serie.cover,
                            serie.name,
                            serie.numberSeasons.toString(),
                            serie.director,
                            serie.favourite
                        )
                    )    // La inserto
                }

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


    data class ItemConsuming(
        val image: Int,
        val title: String,
        val subtitle: String,
        val author: String,
        val favourite: Boolean
    ) : Serializable    // Serializable permite enviarlo a traves de un intent a otra activity


    // Use a separate binding class for your item layout
    class ItemConsumingAdapter(
        private val items: List<ItemConsuming>,
        private val context: Context,
        private val layoutResourceId: Int
    ) : RecyclerView.Adapter<ItemConsumingAdapter.ItemConsumingViewHolder>() {

        inner class ItemConsumingViewHolder(private val binding: ItemConsumiendoBinding) :
            RecyclerView.ViewHolder(binding.root) {
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
            val binding =
                ItemConsumiendoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
