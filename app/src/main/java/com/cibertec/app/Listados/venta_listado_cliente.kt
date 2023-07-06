package com.cibertec.app.Listados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cibertec.app.Adapter.VentaAdapter
import com.cibertec.app.databinding.ActivityVentaListadoBinding
import com.cibertec.app.Detalle.DetalleVenta
import com.cibertec.app.Registros.RegisterVentaClienteActivity
import com.cibertec.app.networking.ApiVenta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class venta_listado_cliente : AppCompatActivity() {

    private lateinit var binding: ActivityVentaListadoBinding

    private var ventaAdapter = VentaAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_venta_listado)
        binding = ActivityVentaListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton3.setOnClickListener{
            val intent = Intent(this, RegisterVentaClienteActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart(){
        super.onStart()
        binding.rvUsers.adapter = ventaAdapter
        //para las curtinas
        GlobalScope.launch(Dispatchers.Main) {

            try {
                //binding.progressBar.visibility= View.VISIBLE
                val response = withContext(Dispatchers.IO){
                    ApiVenta.build().getAllVentas()
                }
                if (response.isSuccessful){
                    val vents = response.body()
                    vents?.let{
                        ventaAdapter.updateList(it)
                    }
                }
                //PARA LOS ERRORES
            }catch (ex: IOException){ //PARA VERIFICAR SI TIENE ACCESO A DATOS O INTERNET
                Toast.makeText(this@venta_listado_cliente,"No tienes acceso a Internet", Toast.LENGTH_LONG).show()
            }
            catch (ex:Exception){
                Toast.makeText(this@venta_listado_cliente,ex.message, Toast.LENGTH_LONG).show()
            }
            //
            finally {
                // binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun VentaAdapter(): VentaAdapter {
        ventaAdapter = VentaAdapter(Detalle = { peli ->
            val bundle_ = Bundle().apply {
                putString("KEY_DESCRIPCION", peli.idventa)
                putString("KEY_DESCRIPCION1", peli.nombrecliente)
                putString("KEY_DESCRIPCION2", peli.correonumero)
                putString("KEY_DESCRIPCION3", peli.direccion)
                putString("KEY_DESCRIPCION4", peli.dni)
                putString("KEY_DESCRIPCION5", peli.nombrepeli)
                putString("KEY_DESCRIPCION6", peli.cantidad)
                putString("KEY_DESCRIPCION7", peli.tipopago)
                putString("KEY_DESCRIPCION8", peli.terminos)
            }
            val intentar_ = Intent(this, DetalleVenta::class.java).apply {
                putExtras(bundle_)
            }
            startActivity(intentar_)
        })
        return ventaAdapter
    }







}