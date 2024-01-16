package vistas

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import java.clases.R

class CarouselAdapter(private val items: MutableList<MainPageFragment.ItemConsuming>):
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_item, parent, false
            )
        )
    }

    /**
     * Esto creo que es paras escoger cada imagen de la lista
     */
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /**
     * Devuelve el numero de imagenes en el adaptador
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * devuelve el ID del item en cuestion
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }


    /**
     * Esto lo hacen en todos lso tutos pero no entiendo muy bien que es
     */
    inner class CarouselViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val carouselImageView: AppCompatImageView  =
            view.findViewById(R.id.ivCarousel)
        private val carouselTextView: AppCompatTextView =
            view.findViewById(R.id.tvCarousel)

        fun bind(item: MainPageFragment.ItemConsuming) {
            carouselImageView.load(item.image) {
                transformations(RoundedCornersTransformation(8f))
            }
            carouselTextView.text = item.title

        }
    }
}
