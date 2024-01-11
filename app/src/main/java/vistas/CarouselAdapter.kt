package vistas

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import java.clases.R

class CarouselAdapter(private val images: List<Int>):
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        Log.d("Warden", "funciona " + images.size)
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
        holder.bind(images[position])
    }

    /**
     * Devuelve el numero de imagenes en el adaptador
     */
    override fun getItemCount(): Int {
        return images.size
    }

    /**
     * Esto lo hacen en todos lso tutos pero no entiendo muy bien que es
     */
    inner class CarouselViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val carouselImageView: AppCompatImageView  =
            view.findViewById(R.id.carouselImageView)

        fun bind(image: Int) {
            carouselImageView.load(image) {
                transformations(RoundedCornersTransformation(8f))
            }
        }
    }
}