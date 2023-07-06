package com.cibertec.app.Listados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.cibertec.app.Adapter.FavoritoAdapter
import com.cibertec.app.Detalle.DetalleFavorito
import com.cibertec.app.Registros.RegisterEditFavoritoActivity
import com.cibertec.app.Registros.RegisterEditFavoritoAdminActivity
import com.cibertec.app.Registros.RegisterEditPeliculaActivity
import com.cibertec.app.databinding.ActivityFavoritoListadoBinding
import com.cibertec.app.networking.ApiFavorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class favorito_listado : AppCompatActivity()
{
    private lateinit var binding : ActivityFavoritoListadoBinding

    private  var favoritoAdapter = FavoritoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_favorito_listado)
        binding = ActivityFavoritoListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingActionButton2.setOnClickListener{
            val intent = Intent(this, RegisterEditFavoritoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
        binding.rvUsers.adapter = favoritoAdapter
        //para las curtinas
        GlobalScope.launch(Dispatchers.Main) {

            try {
                //binding.progressBar.visibility= View.VISIBLE
                val response = withContext(Dispatchers.IO){
                    ApiFavorito.build().getAllFavoritos()
                }
                if (response.isSuccessful){
                    val favs = response.body()
                    favs?.let{
                        favoritoAdapter.updateList(it)
                    }
                }
                //PARA LOS ERRORES
            }catch (ex:IOException){ //PARA VERIFICAR SI TIENE ACCESO A DATOS O INTERNET
                Toast.makeText(this@favorito_listado,"No tienes acceso a Internet",Toast.LENGTH_LONG).show()
            }
            catch (ex:Exception){
                Toast.makeText(this@favorito_listado,ex.message,Toast.LENGTH_LONG).show()
            }
            //
            finally {
                // binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun FavoritoAdapter(): FavoritoAdapter {
        favoritoAdapter = FavoritoAdapter(Detalle = { peli ->
            val bundle_ = Bundle().apply {
                putString("KEY_DESCRIPCION", peli.idfav)
                putString("KEY_DESCRIPCION1", peli.nombre_clie)
                putString("KEY_DESCRIPCION2", peli.correo)
                putString("KEY_DESCRIPCION3", peli.nombre_peli)
                putString("KEY_DESCRIPCION4", peli.resenia)
                putString("KEY_DESCRIPCION5", peli.terminos)
                putString("KEY_DESCRIPCION6", peli.eliminado)
            }
            val intentar_ = Intent(this, DetalleFavorito::class.java).apply {
                putExtras(bundle_)
            }
            startActivity(intentar_)
        })
        return favoritoAdapter
    }





}