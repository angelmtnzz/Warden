package vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clases.Serie
import clases.ConsumptionStatus
import database.WardenDatabase
import kotlinx.coroutines.launch
import modelos.SerieDao
import java.clases.R


class SeriesLibraryPageActivity : AppCompatActivity() {

    private lateinit var serieDao: SerieDao
    private lateinit var backwardsButton: CardView
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Warden_library)
        super.onCreate(savedInstanceState)
        serieDao = WardenDatabase.getDatabase(this).serieDao()
        setContentView(R.layout.activity_series_library_page)
        initComponents()
        initListeners()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lifecycleScope.launch {
            val serieList =
                serieDao.getSeriesByStatus(ConsumptionStatus.CONSUMED)   // cargo la lista de libros Consumidos
            val adapter = SerieAdapter(serieList) { selectedSerie ->
                // Aqui dentro va el clickListener del item
                navigateToTitlePage(selectedSerie)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this@SeriesLibraryPageActivity)     //con el @indicamos la clase a la que pertenece el this, si no se refiere a la corrutina
        }
    }
    private fun navigateToTitlePage(selectedSerie: clases.Serie) {    // PASAMOS LOS INTENT A LA TITLEPAGE

        lifecycleScope.launch {

            val intent = Intent(this@SeriesLibraryPageActivity, TitlePageActivity::class.java)
            intent.putExtra("name", selectedSerie.name)
            intent.putExtra("cover", selectedSerie.cover)
            intent.putExtra("favourite", selectedSerie.favourite)
            intent.putExtra("author", selectedSerie.director)
            intent.putExtra("pages", selectedSerie.numberSeasons)

            startActivity(intent)
        }
    }
    class SerieAdapter(
        private val serieList: List<Serie>,
        private val onItemClick: (clases.Serie) -> Unit
    ) :
        RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {

        class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val serieTextView: TextView = itemView.findViewById(R.id.tvItemTitleView)
            val imageView: ImageView = itemView.findViewById(R.id.ivItemTitleView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_titleview, parent, false)
            return SerieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
            val currentSerie = serieList[position]

            holder.serieTextView.text = currentSerie.name
            holder.imageView.setImageResource(currentSerie.cover)


            // ClickListener para cada elemento
            holder.itemView.setOnClickListener {
                onItemClick.invoke(currentSerie)
            }
        }

        override fun getItemCount(): Int {
            return serieList.size
        }
    }

    private fun initListeners() {
        backwardsButton.setOnClickListener {
            finish()
        }
    }

    private fun initComponents() {
        backwardsButton = findViewById(R.id.backwardsButton)
        recyclerView = findViewById(R.id.rvConsumedSeriesPage)
    }
}