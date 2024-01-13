package vistas


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import clases.Book
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
        val titleDao = WardenDatabase.getDatabase(this).titleDao()
        val titles = listOf(
            Title(0, "Harry Potter"),
            Title(0, "La llave Magica"),
            Title(0, "Los tres mosqueteros")
        )
        lifecycleScope.launch { titles.forEach { titleDao.insertTitle(it) } }

        lifecycleScope.launch {
            val title = Title(id = 0, name = "The Lord of The Rings")
            val book = Book(titleId = 0, author = "Tolkien", pages = 1191, genre = "Fantasy", cover = R.drawable.tlotrcover, name = "The Lord of the rings")
        }

        setupNavigation()
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