package com.cibertec.app.Listados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.app.Adapter.LocalAdapter
import com.cibertec.app.Detalle.DetalleLocal
import com.cibertec.app.database.AppDatabaseLocales
import com.cibertec.app.databinding.ActivityLocalListadoBinding
import com.cibertec.app.presentation.MainActivity
import com.cibertec.app.Registros.RegistroLocales
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LocalListado : AppCompatActivity() {

    private lateinit var binding : ActivityLocalListadoBinding
    private lateinit var notaAdapter: LocalAdapter
    private lateinit var appDatabaseVictor: AppDatabaseLocales

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_local_listado)
        binding = ActivityLocalListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()



        binding.fabAdd.setOnClickListener{
            val intent = Intent(this, RegistroLocales::class.java)
            startActivity(intent)
        }

    }

    override fun onStart(){
        super.onStart()

        appDatabaseVictor = AppDatabaseLocales.getInstance(this)
        GlobalScope.launch(Dispatchers.Main) {

            binding.progressBar2.visibility = View.VISIBLE

            val nota_= withContext(Dispatchers.IO) {
                appDatabaseVictor.localDao().getAllLocales()
            }

            binding.progressBar2.visibility = View.GONE
            notaAdapter.updateList(nota_)
        }
    }

    private fun getListDB(){
        GlobalScope.launch(Dispatchers.Main) {

            binding.progressBar2.visibility = View.VISIBLE

            val local_= withContext(Dispatchers.IO) {
                appDatabaseVictor.localDao().getAllLocales()
            }

            binding.progressBar2.visibility = View.GONE
            notaAdapter.updateList(local_)
        }
    }

    private fun setupAdapter(){


        notaAdapter = LocalAdapter(onItemDelete = {local->
            appDatabaseVictor = AppDatabaseLocales.getInstance(this)

            GlobalScope.launch(Dispatchers.Main) {

                binding.progressBar2.visibility = View.VISIBLE

                withContext(Dispatchers.IO){
                    appDatabaseVictor.localDao().delete(local)
                }
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(this@LocalListado,"Local Eliminado Correctamente", Toast.LENGTH_LONG).show()

            }
            getListDB()
        }, onItemViewLocales = {local->

            val bundle = Bundle().apply {
                putString("KEY_NOMBRES",local.nomLocal)
                putString("KEY_DESCRIPCION",local.descripLocal)
                putString("KEY_CORREO",local.correoLocal)
                putString("KEY_NUMERO",local.telefonoLocal)
                putString("KEY_DISTRIRO",local.distritLocal)
                putString("KEY_TIPO",local.TipoLocal)
                putString("KEY_TERMINOS",local.Requisitos)

            }
            val intent = Intent(this, DetalleLocal::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        })

        binding.rvnotas.adapter = notaAdapter
    }
}