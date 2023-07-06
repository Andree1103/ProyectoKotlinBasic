package com.cibertec.app.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.Listados.*
import com.cibertec.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)


        binding.btnLocales.setOnClickListener {
            val intent = Intent(this, LocalListado::class.java)
            startActivity(intent)
        }


        binding.btnFavoritos.setOnClickListener {
            val intent = Intent(this, favorito_listado_admin::class.java)
            startActivity(intent)
        }

        binding.btnPeliculas.setOnClickListener {
            val intent = Intent(this, pelicula_listado::class.java)
            startActivity(intent)
        }

        binding.btnVentas.setOnClickListener {
            val intent = Intent(this, MenuVentas::class.java)
            startActivity(intent)
        }


    }
}