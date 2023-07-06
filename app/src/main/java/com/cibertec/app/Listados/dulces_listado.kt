package com.cibertec.app.Listados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.cibertec.app.Adapter.DulcesAdapter
import com.cibertec.app.Adapter.FavoritoAdapter
import com.cibertec.app.Detalle.DetalleFavorito
import com.cibertec.app.Registros.RegisterEditDulcesActivity
import com.cibertec.app.Registros.RegisterEditFavoritoActivity
import com.cibertec.app.Registros.RegisterEditFavoritoAdminActivity
import com.cibertec.app.Registros.RegisterEditPeliculaActivity
import com.cibertec.app.databinding.ActivityDulcesListadoBinding
import com.cibertec.app.databinding.ActivityFavoritoListadoBinding
import com.cibertec.app.networking.ApiDulces
import com.cibertec.app.networking.ApiFavorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class dulces_listado : AppCompatActivity()
{
    private lateinit var binding : ActivityDulcesListadoBinding

    private  var Adapter = DulcesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_favorito_listado)
        binding = ActivityDulcesListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingActionButton2.setOnClickListener{
            val intent = Intent(this, RegisterEditDulcesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
        binding.rvUsers.adapter = Adapter
        //para las curtinas
        GlobalScope.launch(Dispatchers.Main) {

            try {
                //binding.progressBar.visibility= View.VISIBLE
                val response = withContext(Dispatchers.IO){
                    ApiDulces.build().getAllDulces()
                }
                if (response.isSuccessful){
                    val favs = response.body()
                    favs?.let{
                        Adapter.updateList(it)
                    }
                }
                //PARA LOS ERRORES
            }catch (ex:IOException){ //PARA VERIFICAR SI TIENE ACCESO A DATOS O INTERNET
                Toast.makeText(this@dulces_listado,"No tienes acceso a Internet",Toast.LENGTH_LONG).show()
            }
            catch (ex:Exception){
                Toast.makeText(this@dulces_listado,ex.message,Toast.LENGTH_LONG).show()
            }
            //
            finally {
                // binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun DulcesAdapter(): DulcesAdapter {
        Adapter = DulcesAdapter(Detalle = { peli ->
            val bundle_ = Bundle().apply {
                putString("KEY_DESCRIPCION", peli.iddulces)
                putString("KEY_DESCRIPCION1", peli.nombrecliente)
                putString("KEY_DESCRIPCION2", peli.producto)
                putString("KEY_DESCRIPCION3", peli.cantidad)
                putString("KEY_DESCRIPCION4", peli.tipopago)
                putString("KEY_DESCRIPCION5", peli.eliminado)
            }
            val intentar_ = Intent(this, RegisterEditDulcesActivity::class.java).apply {
                putExtras(bundle_)
            }
            startActivity(intentar_)
        })
        return Adapter
    }





}