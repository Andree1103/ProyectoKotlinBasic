package com.cibertec.app.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.Listados.*
import com.cibertec.app.databinding.ActivityMenuInvitadoBinding

class MenuInvitado : AppCompatActivity() {

    private lateinit var binding : ActivityMenuInvitadoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuInvitadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFavoritos.setOnClickListener {
            val intent = Intent(this, favorito_listado::class.java)
            startActivity(intent)
        }

        binding.btnLocales.setOnClickListener {//CONFITERIA
            val intent = Intent(this, dulces_listado_cliente::class.java)
            startActivity(intent)
        }

        binding.btnVentas.setOnClickListener {
            val intent = Intent(this, venta_listado::class.java)
            startActivity(intent)
        }





    }
}