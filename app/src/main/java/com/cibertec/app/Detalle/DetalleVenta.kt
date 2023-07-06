package com.cibertec.app.Detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.databinding.ActivityDetalleFavoritoBinding
import com.cibertec.app.databinding.ActivityDetalleLocalBinding
import com.cibertec.app.databinding.ActivityDetalleVentaClienteBinding

class DetalleVenta : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleVentaClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_detalle_local)
        binding = ActivityDetalleVentaClienteBinding.inflate(layoutInflater)
        setContentView(binding.root) //declaramos esto
        val bundle_ = intent.extras

        bundle_?.let {
            val cli = it.getString("KEY_DESCRIPCION")?: ""
            val mas = it.getString("KEY_DESCRIPCION1")?: ""
            val tip = it.getString("KEY_DESCRIPCION2")?: ""
            val serv = it.getString("KEY_DESCRIPCION3")?: ""
            val pago = it.getString("KEY_DESCRIPCION5")?: ""
            val pago2 = it.getString("KEY_DESCRIPCION6")?: ""
            val pago3 = it.getString("KEY_DESCRIPCION7")?: ""


            binding.tvClienteDetalle.text = "Codigo    : " +cli
            binding.tvMascotaDetalle.text = "Cliente : " +mas
            binding.tvTipoDetalle.text = "Correo     : " +tip
            binding.tvServicioDetalle.text = "Direccion  : " +serv
            binding.tvPrecioDetalle.text = "Pelicula  : " +pago
            binding.tvPagoDetalle.text = "Cantidad     : " + pago2
            binding.tvPagoDetalle2.text = "Tipo Pago    : "+pago3
        }?: kotlin.run {
            binding.tvClienteDetalle.text = ""
            binding.tvMascotaDetalle.text = ""
            binding.tvTipoDetalle.text = ""
            binding.tvServicioDetalle.text = ""
            binding.tvPrecioDetalle.text = ""
            binding.tvPagoDetalle.text = ""
            binding.tvPagoDetalle2.text = ""
        }

        binding.btnAtras.setOnClickListener {
            onBackPressed()
        }


    }



}