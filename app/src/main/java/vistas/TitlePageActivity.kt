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
import java.clases.R

private lateinit var backwardsButton: CardView  //Boton para volver atras
private lateinit var ivTitle: ImageView
private lateinit var tvNameTitle: TextView
private lateinit var tvAuthorTitle: TextView
private lateinit var ibFavoritesTitlePage: ImageButton
private var isFavourite: Boolean = false
private lateinit var bookdao: BookDao
private lateinit var title: String


class TitlePageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_page)

        bookdao = WardenDatabase.getDatabase(this).bookDao()
        initComponents()
        initListeners()

        val cover = intent.getIntExtra("cover", 0) // 0 is the default value if not found
        title = intent.getStringExtra("name")
        val subtitle = intent.getStringExtra("pages")
        val author = intent.getStringExtra("author")
        isFavourite = intent.getBooleanExtra("favourite", false)
        initListeners()
        Log.d("WARDEN", "ISFAVOURITE=$isFavourite")
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
            // Toggle the isFavourite status
            isFavourite = !isFavourite

            // Update the ImageButton state
            updateFavoriteButtonState(isFavourite)

            // Update the database
            lifecycleScope.launch {
                // Use the actual book name here
                bookdao.updateFavouriteStatus(title.toString(), isFavourite)
            }
        }
    }

    private fun updateFavoriteButtonState(isFavourite: Boolean) {
        Log.d("WARDEN", "ISFAVOURITE=$isFavourite")
        val drawableId = if (isFavourite) {
            Log.d("WARDEN", "ISFAVOURITE=$isFavourite")
            R.drawable.icon_star_selected
        } else {
            R.drawable.icon_star_noselected
        }
        ibFavoritesTitlePage.load(drawableId)
        /**ibFavoritesTitlePage.imageTintList = null
        val drawable = resources.getDrawable(drawableId, theme)
        ibFavoritesTitlePage.setImageDrawable(drawable)*/

    }
}