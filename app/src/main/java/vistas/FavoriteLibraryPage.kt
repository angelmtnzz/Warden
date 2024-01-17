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
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import java.clases.R


//Para sacar dotos de los DAO
private lateinit var bookdao: BookDao

class FavoriteLibraryPage : AppCompatActivity() {

    private lateinit var backwardsButton: CardView
    private lateinit var recyclerView: RecyclerView

    data class Book(val title: String, val imageUrl: Int, val isFavorite: Boolean)


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Warden_library)
        super.onCreate(savedInstanceState)
        bookdao = WardenDatabase.getDatabase(this).bookDao()
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
            val bookList =
                bookdao.getAllFavoriteBooks()   // cargo la lista de libros
            val adapter = BookAdapter(bookList) { selectedBook ->
                // Aqui dentro va el clickListener del item
                navigateToTitlePage(selectedBook)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this@FavoriteLibraryPage)     //con el @indicamos la clase a la que pertenece el this, si no se refiere a la corrutina
            //recyclerView.setHasFixedSize(true)
            Log.d("WARDEN", "Booklist=$bookList")   //Funciona
        }

    }

    private fun navigateToTitlePage(selectedBook: clases.Book) {    // PASAMOS LOS INTENT A LA TITLEPAGE
        // You can use the data from selectedBook to pass to the title page
        val intent = Intent(this, TitlePageActivity::class.java)
        intent.putExtra("name", selectedBook.name)
        intent.putExtra("cover", selectedBook.cover)
        intent.putExtra("author", selectedBook.author)
        intent.putExtra("pages", selectedBook.pages)
        intent.putExtra("favourite", selectedBook.favourite)
        // Add other attributes as needed
        startActivity(intent)
    }

    class BookAdapter(
        private val bookList: List<clases.Book>,
        private val onItemClick: (clases.Book) -> Unit
    ) :
        RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

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
