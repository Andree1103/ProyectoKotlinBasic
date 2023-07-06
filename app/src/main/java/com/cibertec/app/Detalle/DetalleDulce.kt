package com.cibertec.app.Detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.databinding.ActivityDetalleDulceBinding
import com.cibertec.app.databinding.ActivityDetalleFavoritoBinding
import com.cibertec.app.databinding.ActivityDetalleLocalBinding

class DetalleDulce : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleDulceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_detalle_local)
        binding = ActivityDetalleDulceBinding.inflate(layoutInflater)
        setContentView(binding.root) //declaramos esto
        val bundle_ = intent.extras

        bundle_?.let {
            val cli = it.getString("KEY_DESCRIPCION")?: ""
            val mas = it.getString("KEY_DESCRIPCION1")?: ""
            val tip = it.getString("KEY_DESCRIPCION2")?: ""
            val serv = it.getString("KEY_DESCRIPCION3")?: ""
            val pre = it.getString("KEY_DESCRIPCION4")?: ""
            val pago = it.getString("KEY_DESCRIPCION5")?: ""

            binding.tvClienteDetalle.text = "Codigo    : " +cli
            binding.tvMascotaDetalle.text = "Cliente : " +mas
            binding.tvTipoDetalle.text = "Producto     : " +tip
            binding.tvServicioDetalle.text = "Cantidad : " +serv
            binding.tvPrecioDetalle.text = "Tipo Pago    : " +pre
            binding.tvPagoDetalle.text = "Terminos     : " + pago
        }?: kotlin.run {
            binding.tvClienteDetalle.text = ""
            binding.tvMascotaDetalle.text = ""
            binding.tvTipoDetalle.text = ""
            binding.tvServicioDetalle.text = ""
            binding.tvPrecioDetalle.text = ""
            binding.tvPagoDetalle.text = ""

        }

        binding.btnAtras.setOnClickListener {
            onBackPressed()
        }


    }



}