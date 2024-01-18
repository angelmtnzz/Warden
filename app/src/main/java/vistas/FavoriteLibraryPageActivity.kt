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


//Para sacar dotos de los DAO

private lateinit var titledao: TitleDao

class FavoriteLibraryPageActivity : AppCompatActivity() {

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
        // You can use the data from selectedBook to pass to the title page
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
            // Add other attributes as needed
            startActivity(intent)
        }
    }

    class TitleAdapter(
        private val bookList: List<Title>,
        private val onItemClick: (clases.Title) -> Unit
    ) :
        RecyclerView.Adapter<TitleAdapter.BookViewHolder>() {

        class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.tvItemTitleView)
            val imageView: ImageView = itemView.findViewById(R.id.ivItemTitleView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_titleview, parent, false)
            return BookViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val currentBook = bookList[position]

            holder.titleTextView.text = currentBook.name
            holder.imageView.setImageResource(currentBook.cover)


            // ClickListener for each item in the RecyclerView
            holder.itemView.setOnClickListener {
                onItemClick.invoke(currentBook)
            }
        }

        override fun getItemCount(): Int {
            return bookList.size
        }
    }
}
