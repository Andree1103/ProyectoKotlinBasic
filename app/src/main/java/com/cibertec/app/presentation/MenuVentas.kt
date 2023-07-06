package com.cibertec.app.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.Listados.dulces_listado
import com.cibertec.app.Listados.venta_listado
import com.cibertec.app.databinding.ActivityMenuVentasBinding

class MenuVentas : AppCompatActivity() {

    private lateinit var binding : ActivityMenuVentasBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuVentasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLocales.setOnClickListener {//CONFITERIA
            val intent = Intent(this, dulces_listado::class.java)
            startActivity(intent)
        }

        binding.btnVentas.setOnClickListener {
            val intent = Intent(this, venta_listado::class.java)
            startActivity(intent)
        }




    }
}