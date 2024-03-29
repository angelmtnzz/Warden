package vistas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.add
import androidx.lifecycle.lifecycleScope
import clases.Book
import clases.ConsumptionStatus
import clases.Film
import clases.Serie
import clases.Title
import clases.User
import clases.createTitleFromMedia
import com.google.android.material.snackbar.Snackbar
import database.WardenDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelos.BookDao
import modelos.FilmDao
import modelos.SerieDao
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
    private lateinit var serieDao: SerieDao
    private lateinit var etNickname: EditText
    private lateinit var etPassword: EditText


    // Listas para guardar los titles que se crean
    private var films: List<Film> =
        emptyList()         // Lista de Films que se añadiran a la database
    private var books: List<Book> =
        emptyList()         // Lista de Books que se añadiran a la database
    private var series: List<Serie> =
        emptyList()       // lista de Series que se añadiran a la database
    private var titles: MutableList<Title> =
        mutableListOf()       // lista de Titles clonados que se añadiran a la database
    var media: List<Title> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponent()
        addDataToDatabase()
        login()
        navigateToRegister()
    }

    private fun navigateToRegister() {
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
        serieDao = WardenDatabase.getDatabase(this).serieDao()

    }


    private fun login() {

        var user = User(0, "", "", "", "", "", false, "")

        btnLogin.setOnClickListener {
            var name: String = etNickname.text.toString()
            var password: String = etPassword.text.toString()
            if (name.isNotEmpty() && password.isNotEmpty()) {

                lifecycleScope.launch {
                    user = withContext(Dispatchers.IO) {
                        userDao.getUserByNickname(name)
                    }
                }
                var uNickname = user?.nickname
                var uPassword = user?.password

                if (uNickname!= null && uPassword != null && name == uNickname && password == uPassword) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)

                    intent.putExtra("nickname", name)
                    startActivity(intent)
                } else {

                    setSnackBar("Usuario o contraseña incorrectos")
                }
            } else {
                setSnackBar("Usuario o contraseña no especificados")
            }

        }

    }

    private fun setSnackBar(msg: String) {
        val mySnackbar = Snackbar.make(
            binding.root, msg, Snackbar.LENGTH_LONG
        ).setAnchorView(binding.loginLogo)

        val view: View = mySnackbar.view
        val params = view.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        params.setMargins(100, 200, 100, 0)
        view.layoutParams = params

        mySnackbar.show()
    }

    /**
     * Crea una lista de Peliculas, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addUsersToDatabase() {

        var users = listOf(
            User(0, "shussk02", "Saad", "Hussain", "shussk02@gmail.com", "saad", false, ""),
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


    private fun addDataToDatabase() {
        addUsersToDatabase()
        addTitlesToDatabase()
    }

    private fun addTitlesToDatabase() {

        addBooksToDatabase()
        addFilmsToDatabase()
        addSeriesToDatabase()

        media = books + films + series
        Log.d("WARDEN", "$media")
        for (i in 0..media.size - 1) {
            titles.add(createTitleFromMedia(media[i]))  //Clono el titulo y lo añado a titles
        }
        lifecycleScope.launch {
            titles.forEach {
                val titleExists = titleDao.doesTitleExist(it.name)  // Compruebo si existe
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

        films = listOf(
            Film(
                "Star Wars III",
                "George Lucas",
                139,
                "Sci-Fy",
                R.drawable.coverfilmstarwars,
                true,
                ConsumptionStatus.CONSUMED
            ), Film(
                "Scott Pilgrim vs the World",
                "Edgar Wright",
                112,
                "Comedy",
                R.drawable.coverfilmcott,
                false,
                ConsumptionStatus.TO_CONSUME
            ), Film(
                "The Green Mile",
                "Frank darabond",
                188,
                "horror",
                R.drawable.coverfilmgreenmile,
                true,
                ConsumptionStatus.TO_CONSUME
            ), Film(
                "Harry Potter and the Philosopher's Stone",
                "Chris Columbus",
                152,
                "Fantasy",
                R.drawable.coverfilmharrypotter,
                true,
                ConsumptionStatus.TO_CONSUME
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

    private fun addSeriesToDatabase() {

        series = listOf(
            Serie(
                "The Office",
                9,
                "Ricky Gervais",
                "Comedy",
                R.drawable.coverserietheoffice,
                true,
                ConsumptionStatus.TO_CONSUME
            ), Serie(
                "Cyberpunk: Edgerunners",
                1,
                "Hiroyuki Imaishi",
                "Cyberpunk",
                R.drawable.coverserieedgerunners,
                true,
                ConsumptionStatus.CONSUMED
            )

        )
        lifecycleScope.launch {
            series.forEach {
                val serieExists = serieDao.doesSerieExist(it.name)
                if (serieExists == 0) {
                    serieDao.insertSerie(it)
                }
            }
        }
    }

    /**
     * Inicializa el dao y crea una lista de libros, luego abre una corrutina y añade los libros a la BBDD
     */
    private fun addBooksToDatabase() {

        books = listOf(
            Book(
                "The Lord of the rings",
                "Tolkien",
                1191,
                "Fantasy",
                R.drawable.coverbooktlotr,
                true,
                ConsumptionStatus.CONSUMING
            ),
            Book(
                "Hyperion",
                "Dan Simmons",
                648,
                "Sci-Fy",
                R.drawable.coverbookhyperion,
                true,
                ConsumptionStatus.TO_CONSUME
            ),
            Book(
                "The Fall of Hyperion",
                "Dan Simmons",
                744,
                "Sci-Fy",
                R.drawable.coverbookfallhyperion,
                false,
                ConsumptionStatus.TO_CONSUME
            ),
            Book(
                "At the Mountains of Madness",
                "H. P. Lovecraft",
                176,
                "Horror",
                R.drawable.coverbookmadnessmountains,
                true,
                ConsumptionStatus.TO_CONSUME
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