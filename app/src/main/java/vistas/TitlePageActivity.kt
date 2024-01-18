package vistas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import clases.Book
import clases.ConsumptionStatus
import clases.Title
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.button.MaterialButtonToggleGroup
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

//Para los botones de Status
private lateinit var titleToggleButton: MaterialButtonToggleGroup
private lateinit var buttonToConsume: Button
private lateinit var buttonConsuming: Button
private lateinit var buttonConsumed: Button


class TitlePageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_page)
        initComponents()
        updateFavoriteButtonState(isFavourite)
        initListeners()
        setButtonConsumedText()
    }

    private fun initComponents(){
        bookdao = WardenDatabase.getDatabase(this).bookDao()
        filmdao = WardenDatabase.getDatabase(this).filmDao()
        seriedao = WardenDatabase.getDatabase(this).serieDao()
        titledao = WardenDatabase.getDatabase(this).titleDao()
        backwardsButton = findViewById(R.id.backwardsButton)
        ivTitle = findViewById(R.id.ivTitle)
        tvNameTitle = findViewById(R.id.tvNameTitle)
        tvAuthorTitle = findViewById(R.id.tvAuthorTitle)
        ibFavoritesTitlePage = findViewById(R.id.ibFavoritesTitlePage)
        titleToggleButton = findViewById(R.id.titleToggleButton)
        buttonToConsume = findViewById(R.id.buttonToConsume)
        buttonConsuming = findViewById(R.id.buttonConsuming)
        buttonConsumed = findViewById(R.id.buttonConsumed)

        val cover = intent.getIntExtra("cover", 0) // 0 es el valor por defecto por si no lo encuentra
        title = intent.getStringExtra("name")
        val subtitle = intent.getStringExtra("pages")
        val author = intent.getStringExtra("author")
        isFavourite = intent.getBooleanExtra("favourite", false)
        updateFavoriteButtonState(isFavourite)
        ivTitle.load(cover) {
            transformations(RoundedCornersTransformation(8f))
        }
        tvNameTitle.text = title
        //tvPagesTitle.text = subtitle
        tvAuthorTitle.text = author
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
        titleToggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            // Guardo el status en una variable
            val newStatus = when (checkedId) {
                R.id.buttonToConsume -> ConsumptionStatus.TO_CONSUME
                R.id.buttonConsuming -> ConsumptionStatus.CONSUMING
                R.id.buttonConsumed -> ConsumptionStatus.CONSUMED
                else -> null
            }

            newStatus?.let {
                lifecycleScope.launch {
                    titledao.updateStatus(title.toString(), it)

                    if (titledao.doesBookExist(title.toString()) == 1) {
                        bookdao.updateStatus(title.toString(), it)
                    }
                    if (titledao.doesFilmExist(title.toString()) == 1) {
                        filmdao.updateStatus(title.toString(), it)
                    }
                    if (titledao.doesSerieExist(title.toString()) == 1) {
                        seriedao.updateStatus(title.toString(), it)
                    }
                }
            }
        }
    }
    private fun updateTitleStatus(newStatus: ConsumptionStatus) {
        lifecycleScope.launch {
            titledao.updateStatus(title.toString(), newStatus)
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

    /**
     * Si el titulo es un libro inicializa con por leer/leido/leido, si no con por ver/viendo/visto
     */
    private fun setButtonConsumedText(){
        lifecycleScope.launch {
            if (titledao.doesBookExist(title.toString()) == 1) {
                val book = bookdao.getBook(title.toString())
                setButtonTextForBook(book)
            }
            if (titledao.doesFilmExist(title.toString()) == 1 || titledao.doesSerieExist(title.toString()) == 1) {
                val film = filmdao.getFilm(title.toString())
                setButtonTextForFilmSerie(film)
            }
        }
    }
    private fun setButtonTextForBook(book: Book) {
        buttonToConsume.text = "Por Leer"
        buttonConsuming.text = "Leyendo"
        buttonConsumed.text = "Leido"
    }
    private fun setButtonTextForFilmSerie(title: Title) {
        buttonToConsume.text = "Por Ver"
        buttonConsuming.text = "Viendo"
        buttonConsumed.text = "Vista"
    }
}