package vistas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import database.WardenDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import modelos.BookDao
import modelos.FilmDao
import modelos.SerieDao
import modelos.TitleDao
import java.clases.R

private lateinit var backwardsButton: CardView  //Boton para volver atras
private lateinit var ivTitle: ImageView
private lateinit var tvNameTitle: TextView
private lateinit var tvAuthorTitle: TextView
private lateinit var ibFavoritesTitlePage: ImageButton
private var isFavourite: Boolean = false
private lateinit var bookdao: BookDao
private lateinit var filmdao: FilmDao
private lateinit var seriedao: SerieDao
private lateinit var titledao: TitleDao
private lateinit var title: String


class TitlePageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_page)

        bookdao = WardenDatabase.getDatabase(this).bookDao()
        titledao = WardenDatabase.getDatabase(this).titleDao()
        initComponents()
        updateFavoriteButtonState(isFavourite)
        initListeners()

        val cover = intent.getIntExtra("cover", 0) // 0 is the default value if not found
        title = intent.getStringExtra("name")
        val subtitle = intent.getStringExtra("pages")
        val author = intent.getStringExtra("author")
        isFavourite = intent.getBooleanExtra("favourite", false)
        updateFavoriteButtonState(isFavourite)
        initListeners()
        updateFavoriteButtonState(isFavourite)  // pone el boton de color si esta en favs

        ivTitle.load(cover) {
            transformations(RoundedCornersTransformation(8f))
        }
        tvNameTitle.text = title
        //tvPagesTitle.text = subtitle
        tvAuthorTitle.text = author


    }

    private fun initComponents(){
        backwardsButton = findViewById(R.id.backwardsButton)
        ivTitle = findViewById(R.id.ivTitle)
        tvNameTitle = findViewById(R.id.tvNameTitle)
        tvAuthorTitle = findViewById(R.id.tvAuthorTitle)
        ibFavoritesTitlePage = findViewById(R.id.ibFavoritesTitlePage)
    }

    private fun initListeners(){
        backwardsButton.setOnClickListener{
            finish()    //Cierra la actividad actual y vuelve a la anterior
        }
        ibFavoritesTitlePage.setOnClickListener {
            // Cambio el status de favorito al pulsar el boton
            isFavourite = !isFavourite

            // Actualizo el botón
            updateFavoriteButtonState(isFavourite)

            // Actualizo la BDD
            lifecycleScope.launch {
                titledao.updateFavouriteStatus(title.toString(), isFavourite)

                if(titledao.doesBookExist(title.toString())==1){
                    bookdao.updateFavouriteStatus(title.toString(), isFavourite)
                }
                if(titledao.doesFilmExist(title.toString())==1){
                    filmdao.updateFavouriteStatus(title.toString(), isFavourite)
                }
                if(titledao.doesSerieExist(title.toString())==1){
                    seriedao.updateFavouriteStatus(title.toString(), isFavourite)
                }
            }
        }
    }

    private fun updateFavoriteButtonState(isFavourite: Boolean) {
        val drawableId = if (isFavourite) {
            R.drawable.icon_star_selected
        } else {
            R.drawable.icon_star_noselected
        }
        ibFavoritesTitlePage.load(drawableId)
    }
}