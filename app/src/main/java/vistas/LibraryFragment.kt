package vistas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.clases.R
import java.clases.databinding.FragmentLibraryBinding


class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use the binding object to inflate the layout
        binding = FragmentLibraryBinding.inflate(inflater, container, false)

        // Set up RecyclerView and adapter
        val adapter = categoryAdapter(categories)
        binding.rvLibrary.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.rvLibrary.layoutManager = layoutManager

        // Return the root view from the binding object
        return binding.root
    }

    /**
     * cada una de las tarjetas que aparecen en la library
     */
    data class Category(val image: Int, val title: String, val subtitle: String)
    val categories = listOf(
        Category(R.drawable.banner_favourites, "Favoritos", "Subtítulo 1"),
        Category(R.drawable.banner_books, "Libros", "Subtítulo 2"),
        Category(R.drawable.banner_films, "Pelculas", "Subtítulo 3"),
        Category(R.drawable.banner_series, "Series", "Subtítulo 4"),
    )


    inner class categoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<categoryAdapter.CategoryViewHolder>() {
        /**
         * ViewHolder que contiene las vistas para cada ítem
          */
        inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.categoryImage)        // TODO Redireccionar bien
            val titleView: TextView = view.findViewById(R.id.categoryTitle)
            val subtitleView: TextView = view.findViewById(R.id.categoryEpisodes)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
            return CategoryViewHolder(view)
        }

        /**
         * Este método se llama para mostrar los datos en la posición especificada
         * Aquí es donde se configuran los datos de la category en las vistas
         */
        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category = categories[position]
            holder.imageView.setImageResource(category.image)
            holder.titleView.text = category.title
            holder.subtitleView.text = category.subtitle

            // Set OnClickListener for the entire CardView
            holder.itemView.setOnClickListener {
                // Customize the Intent based on the clicked item
                val intent = when (position) {
                    0 -> Intent(holder.itemView.context, BooksLibraryPage::class.java) //TODO Sustituir con las actividades a redireccionar
                    1 -> Intent(holder.itemView.context, BooksLibraryPage::class.java)
                    2 -> Intent(holder.itemView.context, BooksLibraryPage::class.java)
                    // Add more cases for each category
                    else -> Intent(holder.itemView.context, BooksLibraryPage::class.java)
                }

                // You can also pass data to the new activity using intent.putExtra if needed
                holder.itemView.context.startActivity(intent)
            }
        }

        /***
         * Este método devuelve el número total de ítems en el conjunto de datos
         * En este caso, es el tamaño de la lista de podcasts
         */
        override fun getItemCount() = categories.size
    }

}