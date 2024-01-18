package vistas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clases.Title
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import modelos.TitleDao
import java.clases.R

class FavoriteLibraryPageActivity : AppCompatActivity() {

    private lateinit var titledao: TitleDao
    private lateinit var backwardsButton: CardView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Warden_library)
        super.onCreate(savedInstanceState)
        titledao = WardenDatabase.getDatabase(this).titleDao()
        setContentView(R.layout.activity_favorite_library_page)
        initComponents()
        initListeners()
        setupRecyclerView()
    }


    private fun initComponents() {
        backwardsButton = findViewById(R.id.backwardsButton)
        recyclerView = findViewById(R.id.rvFavoritePage)
    }

    private fun initListeners() {
        backwardsButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        lifecycleScope.launch {
            val titleList =
                titledao.getAllFavoriteTitles()   // cargo la lista de Titulos en favoritos
            val adapter = TitleAdapter(titleList) { selectedTitle ->
                // Aqui dentro va el clickListener del item
                navigateToTitlePage(selectedTitle)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this@FavoriteLibraryPageActivity)     //con el @indicamos la clase a la que pertenece el this, si no se refiere a la corrutina
        }
    }

    private fun navigateToTitlePage(selectedTitle: clases.Title) {    // PASAMOS LOS INTENT A LA TITLEPAGE

        lifecycleScope.launch {

            val intent = Intent(this@FavoriteLibraryPageActivity, TitlePageActivity::class.java)
            intent.putExtra("name", selectedTitle.name)
            intent.putExtra("cover", selectedTitle.cover)
            intent.putExtra("favourite", selectedTitle.favourite)
            if(titledao.doesBookExist(selectedTitle.name)==1){
                val book = titledao.getBook(selectedTitle.name)
                intent.putExtra("author", book.author)
                intent.putExtra("pages", book.pages)
            }
            if(titledao.doesFilmExist(selectedTitle.name)==1){
                val film = titledao.getFilm(selectedTitle.name)
                intent.putExtra("author", film.director)
                intent.putExtra("pages", film.lenght)
            }
            if(titledao.doesSerieExist(selectedTitle.name)==1){
                val serie = titledao.getSerie(selectedTitle.name)
                intent.putExtra("author", serie.director)
                intent.putExtra("pages", serie.numberSeasons)
            }
            startActivity(intent)
        }
    }

    class TitleAdapter(
        private val titleList: List<Title>,
        private val onItemClick: (clases.Title) -> Unit
    ) :
        RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

        class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.tvItemTitleView)
            val imageView: ImageView = itemView.findViewById(R.id.ivItemTitleView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_titleview, parent, false)
            return TitleViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
            val currentTitle = titleList[position]

            holder.titleTextView.text = currentTitle.name
            holder.imageView.setImageResource(currentTitle.cover)

            holder.itemView.setOnClickListener {
                onItemClick.invoke(currentTitle)
            }
        }

        override fun getItemCount(): Int {
            return titleList.size
        }
    }
}
