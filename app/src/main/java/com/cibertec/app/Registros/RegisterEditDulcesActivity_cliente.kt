package com.cibertec.app.Registros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.databinding.ActivityRegisterEditDulcesBinding
import com.cibertec.app.databinding.ActivityRegisterEditDulcesClienteBinding
import com.cibertec.app.databinding.ActivityRegisterEditPeliculaBinding
import com.cibertec.app.model.PeliculaEliminar
import com.cibertec.app.model.RegisterDulcesRequets
import com.cibertec.app.model.RegisterPeliculaRequets
import com.cibertec.app.networking.ApiDulces
import com.cibertec.app.networking.ApiPelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegisterEditDulcesActivity_cliente : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEditDulcesClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register_edit_pelicula)
        binding = ActivityRegisterEditDulcesClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //PARTE DE DETALLE


        val spGeneros: AutoCompleteTextView = findViewById(R.id.spGeneros)
        val GenerosList: List<String> = listOf("Canchita Salada", "Canchita Dulce y Salada",
            "Gaseosas de 500ml","Chocolates","Paquetes de galletas","Caramelos surtidos")

        spGeneros.setAdapter(
            ArrayAdapter(this, R.layout.item_spinner_genero, GenerosList)
        )

        spGeneros.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                val selected = GenerosList[position]
                println(selected)
            }


                binding.btnSave.setOnClickListener {

                    val idpeli = binding.edtIdPeli.text.toString()
                    val titulo = binding.edtPeli.text.toString()
                    val genero = binding.spGeneros.text.toString()
                    val precio = binding.edtDuracion.text.toString()

                    val stock = if (binding.radioButton2.isChecked) {
                        "Efectivo"
                    }else {
                        "Tarjeta"
                    }


                    if (idpeli.isEmpty() || titulo.isEmpty() || genero.isEmpty() || precio.isEmpty() || stock.isEmpty()) {
                        Toast.makeText(this@RegisterEditDulcesActivity_cliente,"Ingrese todos los campos ", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            binding.progressBar2.visibility = View.VISIBLE
                            val response = withContext(Dispatchers.IO) {
                                ApiDulces.build().saveDulces(request = RegisterDulcesRequets(idpeli,titulo,genero,precio,stock))
                                // Api.build().savePet(request = RegisterPetRequest(customer, petName, age))
                            }

                            if (response.isSuccessful) {
                                val resultBody = response.body()
                                resultBody?.let {
                                    Toast.makeText(this@RegisterEditDulcesActivity_cliente, it.resultMessage, Toast.LENGTH_LONG).show()
                                   // Toast.makeText(this@RegisterEditPeliculaActivity,"Se ha registrado una nueva Pelicula",
                                       // Toast.LENGTH_LONG).show()
                                }
                            }

                        } catch (ex: IOException) {
                            Toast.makeText(
                                this@RegisterEditDulcesActivity_cliente,
                                "Registrado con Exito",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (ex: Exception) {
                            Toast.makeText(this@RegisterEditDulcesActivity_cliente, ex.message, Toast.LENGTH_LONG).show()
                        } finally {
                            binding.progressBar2.visibility = View.GONE

                            binding.edtIdPeli.setText("")
                            binding.edtPeli.setText("")
                            binding.spGeneros.setText("")
                            binding.edtDuracion.setText("")

                        }
                    }
                }




    }
}