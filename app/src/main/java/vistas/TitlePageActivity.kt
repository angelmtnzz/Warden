package vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import java.clases.R

private lateinit var backwardsButton: CardView  //Boton para volver atras

class TitlePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_page)

        initComponents()
        initListeners()
    }

    private fun initComponents(){
        backwardsButton = findViewById(R.id.backwardsButton)
    }

    private fun initListeners(){
        backwardsButton.setOnClickListener{
            finish()    //Cierra la actividad actual y vuelve a la anterior
        }
    }
}