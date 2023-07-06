package com.cibertec.app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.app.R
import com.cibertec.app.databinding.ItemVentasBinding
import com.cibertec.app.model.Dulces


//import com.cibertec.app.databinding.ItemNotaBinding
import com.cibertec.app.model.Venta


class VentaAdapter constructor(private var pets: List<Venta> = emptyList(),
                               var Detalle: (Venta) -> Unit) :
    RecyclerView.Adapter<VentaAdapter.NotaViewHolder>()  {

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemVentasBinding = ItemVentasBinding.bind(itemView)

        fun bind(venta: Venta) {
            binding.tvOne.text = venta.idventa
            binding.tvTwo.text = venta.nombrecliente
            binding.tvThree.text = venta.correonumero
            binding.tvFour.text = venta.dni
            binding.tvFive.text = venta.nombrepeli
            binding.tvSix.text = venta.tipopago

            binding.imageVista.setOnClickListener{
                Detalle(venta)
            }
        }
    }
    fun updateList(pets: List<Venta>) {
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