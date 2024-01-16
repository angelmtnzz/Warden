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
import com.google.android.material.textfield.TextInputEditText
import database.WardenDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelos.BookDao
import modelos.FilmDao
import modelos.TitleDao
import modelos.UserDao
import java.clases.R
import java.clases.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var userDao: UserDao
    private lateinit var titleDao: TitleDao
    private lateinit var bookDao: BookDao
    private lateinit var filmDao: FilmDao
    private lateinit var etNickname: EditText
    private lateinit var etPassword:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initComponent()

        addUsersToDatabase()  //Añade todos los usuarios
        addTitlesToDatabase()   //Añade todas las titles
        addFilmsToDatabase()    //Añade todas las peliculas
        addBooksToDatabase()

        login()

        register()
    }

    private fun register() {
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }


    private fun initComponent() {
        btnLogin = findViewById(R.id.loginButton)
        btnRegister = findViewById(R.id.loginRegisterButton)
        etNickname = binding.loginUserName
        etPassword = binding.loginUserPassword
        userDao = WardenDatabase.getDatabase(this).userDao()
        titleDao = WardenDatabase.getDatabase(this).titleDao()
        filmDao = WardenDatabase.getDatabase(this).filmDao()
        bookDao = WardenDatabase.getDatabase(this).bookDao()

    }

    private fun login() {

        var user:User = User(0,"","","","","",false,"")

        btnLogin.setOnClickListener {
            var name:String = etNickname.text.toString()
            var password:String = etPassword.text.toString()
            if (name.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    user = withContext(Dispatchers.IO) {
                        userDao.getUserByNickname(name)
                    }
                }
                if (name == user.nickname && password == user.password) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.i("mine", "login fallido ")
                }
            }

        }

    }

    /**
     * Crea una lista de Peliculas, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addUsersToDatabase() {

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