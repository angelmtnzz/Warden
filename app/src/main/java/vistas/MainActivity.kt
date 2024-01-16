package vistas


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import clases.Book
import clases.Film
import clases.Title
import database.WardenDatabase
import kotlinx.coroutines.launch
import java.clases.R
import java.clases.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



       // addFilmsToDatabase()    //Añade todas las peliculas

        addBooksToDatabase()    //Añade todos los libros
        setupNavigation()
    }



    /**
     * Inicializa el dao y crea una lista de Peliculas, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addFilmsToDatabase(){
        val filmDao = WardenDatabase.getDatabase(this).filmDao()
        var films = listOf(
            Film( "George Lucas", 139, "Sci-Fy", R.drawable.coverfilmstarwars, "Star Wars III"),
            Film( "Edgar Wright", 112, "Comedy", R.drawable.coverfilmcott, "Scott Pilgrim vs the World"),
            Film( "Frank darabond", 188, "horror", R.drawable.coverfilmgreenmile, "The Green Mile"),
            Film( "Chris Columbus", 152, "Fantasy", R.drawable.coverfilmharrypotter, "Harry Potter and the Philosopher's Stone")
        )
        lifecycleScope.launch { films.forEach {
            val bookExists = filmDao.doesFilmExist(it.name)
            if(bookExists==0) {
                filmDao.insertFilm(it)
            }
        }
        }
    }
    /**
     * Inicializa el dao y crea una lista de libros, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addBooksToDatabase(){
        val bookDao = WardenDatabase.getDatabase(this).bookDao()
        val titleDao = WardenDatabase.getDatabase(this).titleDao()

        var books = listOf(
            Book("The Lord of the rings","Tolkien", 1191, "Fantasy", R.drawable.coverbooktlotr ),
            Book( "Hyperion","Dan Simmons", 648, "Sci-Fy", R.drawable.coverbookhyperion ),
            Book("The Fall of Hyperion","Dan Simmons", 744, "Sci-Fy", R.drawable.coverbookfallhyperion),
            Book("At the Mountains of Madness","H. P. Lovecraft", 176, "Horror", R.drawable.coverbookmadnessmountains,),

            )
        var titles = listOf(
            Title(0,"The Lord of the rings"),
            Title( 0,"Hyperion"),
            Title(0,"The Fall of Hyperion"),
            Title(0,"At the Mountains of Madness"),

            )
        lifecycleScope.launch { books.forEach {
            val bookExists = bookDao.doesBookExist(it.name)
            if(bookExists==0) {
                bookDao.insertBook(it)
            }

        }
            titles.forEach{
                val titleExists = titleDao.doesTitleExist(it.name)
                if(titleExists==0) {
                    titleDao.insertTitle(it)
                }
            }
        }
    }
    private fun setupNavigation() {
        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )
    }
}