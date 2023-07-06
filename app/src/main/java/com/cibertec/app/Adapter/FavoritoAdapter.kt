package com.cibertec.app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.app.R
import com.cibertec.app.databinding.ItemFavoritosBinding


//import com.cibertec.app.databinding.ItemNotaBinding
import com.cibertec.app.model.Favorito
import com.cibertec.app.model.Pelicula


class FavoritoAdapter  constructor(private var pets: List<Favorito> = emptyList(),var Detalle: (Favorito) -> Unit) :
    RecyclerView.Adapter<FavoritoAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemFavoritosBinding = ItemFavoritosBinding.bind(itemView)

        fun bind(favorito: Favorito) {
            binding.tvOne.text = favorito.idfav
            binding.tvTwo.text = favorito.nombre_clie
            binding.tvThree.text = favorito.correo
            binding.tvFour.text = favorito.nombre_peli
            binding.tvFive.text = favorito.resenia
            binding.tvSix.text = favorito.terminos

            binding.imageVista.setOnClickListener{
                Detalle(favorito)
            }
        }


    }
    fun updateList(pets: List<Favorito>) {
        this.pets = pets
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_favoritos, parent, false)
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