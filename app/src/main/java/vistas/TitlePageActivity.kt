package vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import coil.load
import coil.transform.RoundedCornersTransformation
import java.clases.R

private lateinit var backwardsButton: CardView  //Boton para volver atras
private lateinit var ivTitle: ImageView
private lateinit var tvNameTitle: TextView
private lateinit var tvAuthorTitle: TextView


class TitlePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_page)

        initComponents()
        initListeners()

        val cover = intent.getIntExtra("cover", 0) // 0 is the default value if not found
        val title = intent.getStringExtra("name")
        val subtitle = intent.getStringExtra("pages")
        val author = intent.getStringExtra("author")
        val isFavourite = intent.getBooleanExtra("favourite", false)
        Log.d("TitlePageActivity", "Received data: cover=$cover,author=$author, title=$title, subtitle=$subtitle")

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
    }

    private fun initListeners(){
        backwardsButton.setOnClickListener{
            finish()    //Cierra la actividad actual y vuelve a la anterior
        }
    }
}