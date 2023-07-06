package com.cibertec.app.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.cibertec.app.R
import com.cibertec.app.databinding.ItemLocalesBinding
import com.cibertec.app.model.Locales

class LocalAdapter constructor(private var Locales_Adap:List<Locales> = emptyList(),  val onItemDelete:(Locales)->Unit,  val onItemViewLocales:(Locales)->Unit) : Adapter<LocalAdapter.Nota_View_Holder>() {

    inner class Nota_View_Holder constructor(item_View:View) : ViewHolder(item_View){

        private val binding : ItemLocalesBinding = ItemLocalesBinding.bind(item_View)


        fun bind(local : Locales){
            binding.tvNomLocal.text = local.nomLocal
            binding.tvDescripcionLocal.text = local.descripLocal
            binding.tvCorreo.text = local.correoLocal
            binding.tvNumero.text = local.telefonoLocal
            binding.tvDistrito.text = local.distritLocal
            binding.tvTipoLocal.text = local.TipoLocal
            binding.tvTerminos.text = local.Requisitos

            binding.imageVista.setOnClickListener{
                onItemViewLocales(local)
            }

            binding.imageDelete.setOnClickListener {
                onItemDelete(local)
            }

        }
    }
    fun updateList(Locales_Adap:List<Locales>){
        this.Locales_Adap = Locales_Adap
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return Locales_Adap.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Nota_View_Holder {

        val item_View:View = LayoutInflater.from(parent.context).inflate(R.layout.item_locales,parent,false)
        return Nota_View_Holder(item_View)
    }

    override fun onBindViewHolder(holder: Nota_View_Holder, position: Int) {
        val locales = Locales_Adap[position]
        holder.bind(locales)
    }


}