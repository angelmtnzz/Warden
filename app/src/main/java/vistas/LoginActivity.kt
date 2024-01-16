package vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import clases.Book
import clases.Film
import clases.Title
import clases.User
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.BookDao
import modelos.FilmDao
import modelos.TitleDao
import modelos.UserDao
import java.clases.R
import java.clases.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private lateinit var btnLogin: Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)




        addUsersToDatabase()  //Añade todos los usuarios
        addTitlesToDatabase()   //Añade todas las titles
        addFilmsToDatabase()    //Añade todas las peliculas
        addBooksToDatabase()


        initComponent()
        login()
    }


    private fun initComponent() {
        btnLogin = findViewById(R.id.loginButton)

    }

    /*private fun login() {
        val etNickname = findViewById<EditText>(R.id.loginUserName)
        val etPassword = findViewById<EditText>(R.id.loginUserPassword)
        val name = etNickname.text.toString()
        val password = etPassword.text.toString()
        var userDao = WardenDatabase.getDatabase(this).userDao()
        lifecycleScope.launch { user = userDao.getUserByNickname(name) }
        btnLogin.setOnClickListener {
            if(user.nickname == name && user.password == password ){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Log.i("TAG", "login fallido ")
            }

        }
    }*/

    private fun login() {
        btnLogin.setOnClickListener {
            val etNickname = binding.loginUserName
            val etPassword = binding.loginUserPassword
            val name = etNickname.text.toString()
            val password = etPassword.text.toString()
            val userDao = WardenDatabase.getDatabase(this).userDao()
            lifecycleScope.launch {
                var user = userDao.getUserByNickname(name)

                if (true/**user.nickname == name && user.password == password*/) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.i("TAG", "login fallido ")
                }
            }
        }}

    /**
     * Crea una lista de Peliculas, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addUsersToDatabase() {
        var userDao = WardenDatabase.getDatabase(this).userDao()
        var users = listOf(
            User(0, "shussk02", "Saad", "Hussain", "shussk02@gmail.com", "saad", false, ""),
            User(0, "vamig00", "Victor", "Amigo", "vamig00@gmail.com", "victor", false, ""),
            User(
                0, "marcosfnmr", "Marcos", "Fernandez", "marcosfnmr@gmail.com", "marcos", false, ""
            ),
            User(0, "angelmtnzz", "Angel", "Martínez", "angelmtnzz@gmail.com", "angel", false, ""),

            )
        lifecycleScope.launch {
            users.forEach {
                val userExists = userDao.doesUserExist(it.nickname)
                if (userExists == 0) {
                    userDao.insertUser(it)
                }
            }

        }
    }


    /**
     * Inicializa el dao y crea una lista de Peliculas, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addTitlesToDatabase() {

        var titleDao = WardenDatabase.getDatabase(this).titleDao()

        var titles = listOf(
            Title(0, "The Lord of the rings"),
            Title(0, "Hyperion"),
            Title(0, "The Fall of Hyperion"),
            Title(0, "At the Mountains of Madness"),

            )
        lifecycleScope.launch {
            titles.forEach {
                val titleExists = titleDao.doesTitleExist(it.name)
                if (titleExists == 0) {
                    titleDao.insertTitle(it)
                }
            }
        }
    }

    /**
     * Inicializa el dao y crea una lista de Peliculas, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addFilmsToDatabase() {
        var filmDao = WardenDatabase.getDatabase(this).filmDao()

        var films = listOf(
            Film("Star Wars III", "George Lucas", 139, "Sci-Fy", R.drawable.coverfilmstarwars),
            Film(
                "Scott Pilgrim vs the World",
                "Edgar Wright",
                112,
                "Comedy",
                R.drawable.coverfilmcott
            ),
            Film("The Green Mile", "Frank darabond", 188, "horror", R.drawable.coverfilmgreenmile),
            Film(
                "Harry Potter and the Philosopher's Stone",
                "Chris Columbus",
                152,
                "Fantasy",
                R.drawable.coverfilmharrypotter
            )
        )
        lifecycleScope.launch {
            films.forEach {
                val filmExists = filmDao.doesFilmExist(it.name)
                if (filmExists == 0) {
                    filmDao.insertFilm(it)
                }
            }
        }
    }

    /**
     * Inicializa el dao y crea una lista de libros, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addBooksToDatabase() {
        var bookDao = WardenDatabase.getDatabase(this).bookDao()

        var books = listOf(
            Book("The Lord of the rings", "Tolkien", 1191, "Fantasy", R.drawable.coverbooktlotr),
            Book("Hyperion", "Dan Simmons", 648, "Sci-Fy", R.drawable.coverbookhyperion),
            Book(
                "The Fall of Hyperion",
                "Dan Simmons",
                744,
                "Sci-Fy",
                R.drawable.coverbookfallhyperion
            ),
            Book(
                "At the Mountains of Madness",
                "H. P. Lovecraft",
                176,
                "Horror",
                R.drawable.coverbookmadnessmountains,
            ),

            )

        lifecycleScope.launch {
            books.forEach {
                val bookExists = bookDao.doesBookExist(it.name)
                if (bookExists == 0) {
                    bookDao.insertBook(it)
                }
            }
        }
    }
}