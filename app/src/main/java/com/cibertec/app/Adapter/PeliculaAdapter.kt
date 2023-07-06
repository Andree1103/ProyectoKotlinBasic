package com.cibertec.app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.app.R
import com.cibertec.app.databinding.ItemPeliculasBinding
import com.cibertec.app.model.Pelicula

class PeliculaAdapter constructor(private var pets: List<Pelicula> = emptyList(),var Detalle: (Pelicula) -> Unit) :
    RecyclerView.Adapter<PeliculaAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemPeliculasBinding = ItemPeliculasBinding.bind(itemView)

        fun bind(pelicula: Pelicula) {
            binding.tvOne.text = pelicula.idpelicula
            binding.tvTwo.text = pelicula.titulo
            binding.tvThree.text = pelicula.descripcion
            binding.tvFour.text = pelicula.duracion
            binding.tvFive.text = pelicula.genero
            binding.tvSix.text = pelicula.precio
            binding.tvSeven.text = pelicula.stock
            binding.tvEights.text = pelicula.publico

            binding.imageVista.setOnClickListener{
                Detalle(pelicula)
            }
        }
    }
    fun updateList(pets: List<Pelicula>) {
        this.pets = pets
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_peliculas, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pets.size
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val pet = pets[position]
        holder.bind(pet)
    }









}