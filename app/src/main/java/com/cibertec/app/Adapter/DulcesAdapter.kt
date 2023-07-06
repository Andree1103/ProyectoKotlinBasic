package com.cibertec.app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.app.R
import com.cibertec.app.databinding.ItemFavoritosBinding
import com.cibertec.app.model.Dulces


//import com.cibertec.app.databinding.ItemNotaBinding
import com.cibertec.app.model.Favorito
import com.cibertec.app.model.Pelicula


class DulcesAdapter  constructor(private var dulces: List<Dulces> = emptyList(), var Detalle: (Dulces) -> Unit) :
    RecyclerView.Adapter<DulcesAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemFavoritosBinding = ItemFavoritosBinding.bind(itemView)

        fun bind(dulces: Dulces) {
            binding.tvOne.text = dulces.iddulces
            binding.tvTwo.text = dulces.nombrecliente
            binding.tvThree.text = dulces.producto
            binding.tvFour.text = dulces.cantidad
            binding.tvFive.text = dulces.tipopago
            binding.tvSix.text = dulces.eliminado

            binding.imageVista.setOnClickListener{
                Detalle(dulces)
            }
        }


    }
    fun updateList(dulces: List<Dulces>) {
        this.dulces = dulces
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_favoritos, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dulces.size
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val pet = dulces[position]
        holder.bind(pet)
    }

}