package com.cibertec.app.Listados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.app.Adapter.PeliculaAdapter

import com.cibertec.app.databinding.ActivityPeliculaListadoBinding
import com.cibertec.app.networking.ApiPelicula
import com.cibertec.app.Registros.RegisterEditPeliculaActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class pelicula_listado : AppCompatActivity() {

    private lateinit var binding : ActivityPeliculaListadoBinding

    private  var peliculaAdapter = PeliculaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_pelicula_listado)
        binding = ActivityPeliculaListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd1.setOnClickListener{
            val intent = Intent(this, RegisterEditPeliculaActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart(){
        super.onStart()
        binding.rvUsers.adapter = peliculaAdapter
        //para las curtinas
        GlobalScope.launch(Dispatchers.Main) {

            try {
                //binding.progressBar.visibility= View.VISIBLE
                val response = withContext(Dispatchers.IO){
                    ApiPelicula.build().getAllPeliculas()
                }
                if (response.isSuccessful){
                    val pelis = response.body()
                    pelis?.let{
                        peliculaAdapter.updateList(it)
                    }
                }
                //PARA LOS ERRORES
            }catch (ex: IOException){ //PARA VERIFICAR SI TIENE ACCESO A DATOS O INTERNET
                Toast.makeText(this@pelicula_listado,"No tienes acceso a Internet", Toast.LENGTH_LONG).show()
            }
            catch (ex:Exception){
                Toast.makeText(this@pelicula_listado,ex.message, Toast.LENGTH_LONG).show()
            }
            //
            finally {
                binding.progressBar3.visibility = View.GONE
            }
        }
    }


     private fun PeliculaAdapter(): PeliculaAdapter{
         peliculaAdapter = PeliculaAdapter(Detalle = { peli ->
        val bundle_ = Bundle().apply {
            putString("KEY_DESCRIPCION", peli.idpelicula)
            putString("KEY_DESCRIPCION1", peli.titulo)
            putString("KEY_DESCRIPCION2", peli.descripcion)
            putString("KEY_DESCRIPCION3", peli.duracion)
            putString("KEY_DESCRIPCION4", peli.genero)
            putString("KEY_DESCRIPCION5", peli.precio)
            putString("KEY_DESCRIPCION6", peli.stock)
            putString("KEY_DESCRIPCION7", peli.publico)
            putString("KEY_DESCRIPCION8", peli.eliminado)
        }
        val intentar_ = Intent(this, RegisterEditPeliculaActivity::class.java).apply {
            putExtras(bundle_)
        }
        startActivity(intentar_)
    })
    return peliculaAdapter
}






}