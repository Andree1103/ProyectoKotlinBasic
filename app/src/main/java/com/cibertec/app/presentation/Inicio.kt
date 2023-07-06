package com.cibertec.app.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.app.R
import com.cibertec.app.databinding.ActivityInicioBinding
import com.cibertec.app.databinding.ActivityLogueoBinding


class Inicio : AppCompatActivity() {

    private lateinit var binding : ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_inicio)

        binding.button.setOnClickListener {
            val intent = Intent(this, PresenIntegrantes::class.java)
            startActivity(intent)


        }

        binding.btnAdmin.setOnClickListener {
            val intent = Intent(this, MainActivity_Logeo::class.java)
            startActivity(intent)
        }
        binding.btnInvitado.setOnClickListener {
            val intent = Intent(this, LogueoActivity::class.java)
            startActivity(intent)
        }

    }



}