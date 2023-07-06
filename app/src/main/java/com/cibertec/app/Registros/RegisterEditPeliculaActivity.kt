package com.cibertec.app.Registros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.databinding.ActivityRegisterEditPeliculaBinding
import com.cibertec.app.model.PeliculaEliminar
import com.cibertec.app.model.RegisterPeliculaRequets
import com.cibertec.app.networking.ApiPelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegisterEditPeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEditPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register_edit_pelicula)
        binding = ActivityRegisterEditPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //PARTE DE DETALLE
        val bundle_ = intent.extras

        bundle_?.let {
            //visualizacion = NO ES NULO
            val codigo = it.getString("KEY_DESCRIPCION")?: ""
            val titulo = it.getString("KEY_DESCRIPCION1")?: ""
            val descripcion = it.getString("KEY_DESCRIPCION2")?: ""
            val duracion = it.getString("KEY_DESCRIPCION3")?: ""
            val genero = it.getString("KEY_DESCRIPCION4")?: ""
            val precio = it.getString("KEY_DESCRIPCION5")?: ""
            val stock = it.getString("KEY_DESCRIPCION6")?: ""
            val publico = it.getString("KEY_DESCRIPCION7")?: ""


            binding.edtIdPeli.setText(codigo)
            binding.edtPeli.setText(titulo)
            binding.edtDescrip.setText(descripcion)
            binding.edtDuracion.setText(duracion)
            binding.spGeneros.setText(genero)
            binding.edtPrecio.setText(precio)
            binding.edtStock.setText(stock)
            binding.spPublico.setText(publico)
            binding.btnSave.visibility = View.GONE
        }?: kotlin.run {
            //Grbar Bundle_ nulo
            binding.edtIdPeli.setText("")
            binding.edtPeli.setText("")
            binding.edtDescrip.setText("")
            binding.edtDuracion.setText("")
            binding.spGeneros.setText("")
            binding.edtPrecio.setText("")
            binding.edtStock.setText("")
            binding.spPublico.setText("")
            binding.btnSave.visibility = View.VISIBLE
        }


        val spGeneros: AutoCompleteTextView = findViewById(R.id.spGeneros)
        val GenerosList: List<String> = listOf(
            "Accion",
            "Aventura",
            "Comedia",
            "Drama",
            "Terror",
            "Ciencia Ficción",
            "Fantasía",
            "Romance",
            "Animación"
        )

        spGeneros.setAdapter(
            ArrayAdapter(this, R.layout.item_spinner_genero, GenerosList)
        )

        spGeneros.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                val selected = GenerosList[position]
                println(selected)
            }
                val spPublico: AutoCompleteTextView = findViewById(R.id.spPublico)
                val PublicoList: List<String> = listOf(
                    "Apta para todo publico",
                    "Mayores de 18 años",
                    "Mayores de 13 años",
                    "Mayores de 16 años",
                    "Restringida (Solo para adultos)"
                )

                spPublico.setAdapter(
                    ArrayAdapter(this, R.layout.item_spinner, PublicoList)
                )

                spPublico.onItemClickListener =
                    AdapterView.OnItemClickListener { adapterView, view, position, l ->
                        val selected = PublicoList[position]
                        println(selected)
                    }


                binding.btnSave.setOnClickListener {

                    val idpeli = binding.edtIdPeli.text.toString()
                    val titulo = binding.edtPeli.text.toString()
                    val descrip = binding.edtDescrip.text.toString()
                    val duracion = binding.edtDuracion.text.toString()
                    val genero = binding.spGeneros.text.toString()
                    val precio = binding.edtPrecio.text.toString()
                    val stock = binding.edtStock.text.toString()
                    val publico = binding.spPublico.text.toString()

                    if (idpeli.isEmpty() || titulo.isEmpty() || descrip.isEmpty() || duracion.isEmpty()
                        || genero.isEmpty()
                        || precio.isEmpty()|| stock.isEmpty()|| publico.isEmpty()) {
                        Toast.makeText(this@RegisterEditPeliculaActivity,"Ingrese todos los campos ", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            binding.progressBar2.visibility = View.VISIBLE
                            val response = withContext(Dispatchers.IO) {
                                ApiPelicula.build().savePeli(request = RegisterPeliculaRequets(idpeli,titulo,descrip,duracion,genero,precio,stock,publico))
                                // Api.build().savePet(request = RegisterPetRequest(customer, petName, age))
                            }

                            if (response.isSuccessful) {
                                val resultBody = response.body()
                                resultBody?.let {
                                    Toast.makeText(this@RegisterEditPeliculaActivity, it.resultMessage, Toast.LENGTH_LONG).show()
                                   // Toast.makeText(this@RegisterEditPeliculaActivity,"Se ha registrado una nueva Pelicula",
                                       // Toast.LENGTH_LONG).show()
                                }
                            }

                        } catch (ex: IOException) {
                            Toast.makeText(
                                this@RegisterEditPeliculaActivity,
                                "Registrado con Exito",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (ex: Exception) {
                            Toast.makeText(this@RegisterEditPeliculaActivity, ex.message, Toast.LENGTH_LONG).show()
                        } finally {
                            binding.progressBar2.visibility = View.GONE

                            binding.edtIdPeli.setText("")
                            binding.edtPeli.setText("")
                            binding.edtDescrip.setText("")
                            binding.edtDuracion.setText("")
                            //
                            binding.spGeneros.setText("")
                            binding.edtPrecio.setText("")
                            binding.edtStock.setText("")
                            //
                            binding.spPublico.setText("")
                        }
                    }
                }

        binding.btnSave4.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        val idPelicula = binding.edtIdPeli.text.toString()
                        val apiService = ApiPelicula.build()
                        apiService.deletePeli(idPelicula)
                    }
                    if (response.isSuccessful) {
                        val resultApi = response.body()
                        resultApi?.let {
                            Toast.makeText(this@RegisterEditPeliculaActivity, "Eliminado correctamente", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        // Manejar el caso de error en la respuesta
                        Toast.makeText(this@RegisterEditPeliculaActivity, "Error al eliminar la película", Toast.LENGTH_LONG).show()
                    }
                } catch (ex: IOException) {
                    Toast.makeText(this@RegisterEditPeliculaActivity, "Eliminado correctamente", Toast.LENGTH_LONG).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterEditPeliculaActivity, ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    // Limpieza o acciones finales, si es necesario
                }
            }
        }



        binding.btnSave3.setOnClickListener {

            val idpeli = binding.edtIdPeli.text.toString()
            val titulo = binding.edtPeli.text.toString()
            val descrip = binding.edtDescrip.text.toString()
            val duracion = binding.edtDuracion.text.toString()
            val genero = binding.spGeneros.text.toString()
            val precio = binding.edtPrecio.text.toString()
            val stock = binding.edtStock.text.toString()
            val publico = binding.spPublico.text.toString()

            if (idpeli.isEmpty() || titulo.isEmpty() || descrip.isEmpty() || duracion.isEmpty()
                || genero.isEmpty()
                || precio.isEmpty() || stock.isEmpty() || publico.isEmpty()
            ) {
                Toast.makeText(
                    this@RegisterEditPeliculaActivity,
                    "Ingrese todos los campos ",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }



            GlobalScope.launch(Dispatchers.Main) {
                try {
                    binding.progressBar2.visibility = View.VISIBLE
                    val response = withContext(Dispatchers.IO) {
                        ApiPelicula.build().updatePeli(request = RegisterPeliculaRequets(idpeli,titulo,descrip,duracion,genero,precio,stock,publico))
                        // Api.build().savePet(request = RegisterPetRequest(customer, petName, age))
                    }

                    if (response.isSuccessful) {
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@RegisterEditPeliculaActivity, it.resultMessage, Toast.LENGTH_LONG).show()
                            // Toast.makeText(this@RegisterEditPeliculaActivity,"Se ha registrado una nueva Pelicula",
                            // Toast.LENGTH_LONG).show()
                        }
                    }

                } catch (ex: IOException) {
                    Toast.makeText(
                        this@RegisterEditPeliculaActivity,
                        "Actualizado con Exito",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterEditPeliculaActivity, ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    binding.progressBar2.visibility = View.GONE

                    binding.edtIdPeli.setText("")
                    binding.edtPeli.setText("")
                    binding.edtDescrip.setText("")
                    binding.edtDuracion.setText("")
                    //
                    binding.spGeneros.setText("")
                    binding.edtPrecio.setText("")
                    binding.edtStock.setText("")
                    //
                    binding.spPublico.setText("")
                }
            }

        }
    }
}