package com.cibertec.app.Detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.databinding.ActivityDetalleLocalBinding

class DetalleLocal : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleLocalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_detalle_local)
        binding = ActivityDetalleLocalBinding.inflate(layoutInflater)
        setContentView(binding.root) //declaramos esto
        val bundle_ = intent.extras

        bundle_?.let {
            val cli = it.getString("KEY_NOMBRES")?: ""
            val mas = it.getString("KEY_DESCRIPCION")?: ""
            val tip = it.getString("KEY_CORREO")?: ""
            val serv = it.getString("KEY_NUMERO")?: ""
            val pre = it.getString("KEY_DISTRIRO")?: ""
            val pago = it.getString("KEY_TIPO")?: ""
            val pago2 = it.getString("KEY_TERMINOS")?: ""

            binding.tvClienteDetalle.text = "Nombre      : " +cli
            binding.tvMascotaDetalle.text = "Descripcion : " +mas
            binding.tvTipoDetalle.text = "Correo      : " +tip
            binding.tvServicioDetalle.text = "Telefono  : " +serv
            binding.tvPrecioDetalle.text = "Distrito    : " +pre
            binding.tvPagoDetalle.text = "Tipo        : " + pago
            binding.tvPagoDetalle2.text = "Acepto      : "+pago2
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