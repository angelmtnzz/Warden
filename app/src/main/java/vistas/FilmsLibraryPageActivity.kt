package vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clases.Book
import clases.ConsumptionStatus
import clases.Film
import clases.Title
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import modelos.FilmDao
import java.clases.R

private lateinit var filmDao: FilmDao
class FilmsLibraryPageActivity : AppCompatActivity() {

    private lateinit var backwardsButton: CardView
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Warden_library)
        super.onCreate(savedInstanceState)
        filmDao = WardenDatabase.getDatabase(this).filmDao()
        setContentView(R.layout.activity_films_library_page)
        initComponents()
        initListeners()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lifecycleScope.launch {
            val filmList =
                filmDao.getFilmsByStatus(ConsumptionStatus.CONSUMED)   // cargo la lista de libros Consumidos
            val adapter = FilmAdapter(filmList) { selectedFilm ->
                // Aqui dentro va el clickListener del item
                navigateToTitlePage(selectedFilm)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this@FilmsLibraryPageActivity)     //con el @indicamos la clase a la que pertenece el this, si no se refiere a la corrutina
        }
    }
    private fun navigateToTitlePage(selectedFilm: clases.Film) {    // PASAMOS LOS INTENT A LA TITLEPAGE
        // You can use the data from selectedFilm to pass to the title page
        lifecycleScope.launch {

            val intent = Intent(this@FilmsLibraryPageActivity, TitlePageActivity::class.java)
            intent.putExtra("name", selectedFilm.name)
            intent.putExtra("cover", selectedFilm.cover)
            intent.putExtra("favourite", selectedFilm.favourite)
            intent.putExtra("author", selectedFilm.director)
            intent.putExtra("pages", selectedFilm.lenght)

            startActivity(intent)
        }
    }
    class FilmAdapter(
        private val filmList: List<Film>,
        private val onItemClick: (clases.Film) -> Unit
    ) :
        RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

        class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val filmTextView: TextView = itemView.findViewById(R.id.tvItemTitleView)
            val imageView: ImageView = itemView.findViewById(R.id.ivItemTitleView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_titleview, parent, false)
            return FilmViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
            val currentFilm = filmList[position]

            holder.filmTextView.text = currentFilm.name
            holder.imageView.setImageResource(currentFilm.cover)


            // ClickListener for each item in the RecyclerView
            holder.itemView.setOnClickListener {
                onItemClick.invoke(currentFilm)
            }
        }

        override fun getItemCount(): Int {
            return filmList.size
        }
    }

    private fun initListeners() {
        backwardsButton.setOnClickListener {
            finish()
        }
    }

    private fun initComponents() {
        backwardsButton = findViewById(R.id.backwardsButton)
        recyclerView = findViewById(R.id.rvConsumedFilmsPage)
    }
}