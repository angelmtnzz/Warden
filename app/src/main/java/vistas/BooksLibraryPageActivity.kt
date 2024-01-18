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
import clases.Title
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import java.clases.R

private lateinit var bookDao: BookDao
class BooksLibraryPageActivity : AppCompatActivity() {

    private lateinit var backwardsButton: CardView
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Warden_library)
        super.onCreate(savedInstanceState)
        bookDao = WardenDatabase.getDatabase(this).bookDao()
        setContentView(R.layout.activity_books_library_page)
        initComponents()
        initListeners()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lifecycleScope.launch {
            val bookList =
                bookDao.getBooksByStatus(ConsumptionStatus.CONSUMED)   // cargo la lista de libros Consumidos
            val adapter = BookAdapter(bookList) { selectedBook ->
                // Aqui dentro va el clickListener del item
                navigateToTitlePage(selectedBook)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this@BooksLibraryPageActivity)     //con el @indicamos la clase a la que pertenece el this, si no se refiere a la corrutina
        }
    }
    private fun navigateToTitlePage(selectedBook: clases.Book) {    // PASAMOS LOS INTENT A LA TITLEPAGE
        // You can use the data from selectedBook to pass to the title page
        lifecycleScope.launch {

            val intent = Intent(this@BooksLibraryPageActivity, TitlePageActivity::class.java)
            intent.putExtra("name", selectedBook.name)
            intent.putExtra("cover", selectedBook.cover)
            intent.putExtra("favourite", selectedBook.favourite)
            intent.putExtra("author", selectedBook.author)
            intent.putExtra("pages", selectedBook.pages)

            startActivity(intent)
        }
    }
    class BookAdapter(
        private val bookList: List<Book>,
        private val onItemClick: (clases.Book) -> Unit
    ) :
        RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

        class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val bookTextView: TextView = itemView.findViewById(R.id.tvItemTitleView)
            val imageView: ImageView = itemView.findViewById(R.id.ivItemTitleView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_titleview, parent, false)
            return BookViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val currentBook = bookList[position]

            holder.bookTextView.text = currentBook.name
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

    private fun initListeners() {
        backwardsButton.setOnClickListener {
            finish()
        }
    }

    private fun initComponents() {
        backwardsButton = findViewById(R.id.backwardsButton)
        recyclerView = findViewById(R.id.rvConsumedBooksPage)
    }
}