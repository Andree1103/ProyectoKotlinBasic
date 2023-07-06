package com.cibertec.app.Registros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.databinding.ActivityRegisterEditFavoritoAdminBinding
import com.cibertec.app.databinding.ActivityRegisterEditFavoritoBinding
import com.cibertec.app.model.RegisterFavoritoRequets
import com.cibertec.app.networking.ApiFavorito
import com.cibertec.app.networking.ApiPelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegisterEditFavoritoAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEditFavoritoAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register_edit_pelicula)
        binding = ActivityRegisterEditFavoritoAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener{
            onBackPressed()
        }

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

            binding.edtIdPeli.setText(codigo)
            binding.edtNomCli.setText(titulo)
            binding.edtEmaCli.setText(descripcion)
            binding.edtPeli.setText(duracion)
            binding.edtResenia.setText(genero)
            binding.chkTerminos.text = precio
            binding.btnSave.visibility = View.GONE
        }?: kotlin.run {
            //Grbar Bundle_ nulo
            binding.edtIdPeli.setText("")
            binding.edtNomCli.setText("")
            binding.edtEmaCli.setText("")
            binding.edtResenia.setText("")
            binding.btnSave.visibility = View.VISIBLE
        }

        binding.btnSave.setOnClickListener {

            val idpeli = binding.edtIdPeli.text.toString()
            val nomcli = binding.edtNomCli.text.toString()
            val emailcli = binding.edtEmaCli.text.toString()
            val nompeli = binding.edtPeli.text.toString()
            val resenia = binding.edtResenia.text.toString()
            //
            val terminos = if (binding.chkTerminos.isChecked) {
                "Acepto"
            }else {
                "No Acepto"
            }

            if (idpeli.isEmpty() || idpeli.isEmpty() || nomcli.isEmpty() || emailcli.isEmpty() || nompeli.isEmpty()
                || resenia.isEmpty()|| terminos.isEmpty()) {
                Toast.makeText(this@RegisterEditFavoritoAdminActivity,"Ingrese todos los campos ", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.Main) {

                try {
                    binding.progressBar2.visibility = View.VISIBLE

                    val response = withContext(Dispatchers.IO) {
                            ApiFavorito.build().saveFav(request = RegisterFavoritoRequets(idpeli,nomcli,emailcli,nompeli,resenia,terminos))
                        // Api.build().savePet(request = RegisterPetRequest(customer, petName, age))
                    }

                    if (response.isSuccessful) {
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@RegisterEditFavoritoAdminActivity, it.resultMessage, Toast.LENGTH_LONG).show()
                            //Toast.makeText(this@RegisterEditPeliculaActivity,"Se ha registrado una nueva Pelicula",
                            //Toast.LENGTH_LONG).show()
                        }
                    }

                } catch (ex: IOException) {
                    Toast.makeText(
                        this@RegisterEditFavoritoAdminActivity,
                        "La pelicula favorita ha sido Registrada",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterEditFavoritoAdminActivity, ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    binding.progressBar2.visibility = View.GONE

                    binding.edtIdPeli.setText("")
                    binding.edtNomCli.setText("")
                    binding.edtEmaCli.setText("")
                    binding.edtPeli.setText("")
                    //
                    binding.edtResenia.setText("")

                }
            }
        }

        binding.btnSave8.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        val idPelicula = binding.edtIdPeli.text.toString()
                        val apiService = ApiFavorito.build()
                        apiService.deleteFav(idPelicula)
                    }
                    if (response.isSuccessful) {
                        val resultApi = response.body()
                        resultApi?.let {
                            Toast.makeText(this@RegisterEditFavoritoAdminActivity, "Eliminado correctamente", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        // Manejar el caso de error en la respuesta
                        Toast.makeText(this@RegisterEditFavoritoAdminActivity, "Error al eliminar la película", Toast.LENGTH_LONG).show()
                    }
                } catch (ex: IOException) {
                    Toast.makeText(this@RegisterEditFavoritoAdminActivity, "Eliminado correctamente", Toast.LENGTH_LONG).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterEditFavoritoAdminActivity, ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    // Limpieza o acciones finales, si es necesario
                }
            }
        }




        binding.btnSave5.setOnClickListener {

            val idpeli = binding.edtIdPeli.text.toString()
            val nomcli = binding.edtNomCli.text.toString()
            val emailcli = binding.edtEmaCli.text.toString()
            val nompeli = binding.edtPeli.text.toString()
            val resenia = binding.edtResenia.text.toString()
            //
            val terminos = if (binding.chkTerminos.isChecked) {
                "Acepto"
            }else {
                "No Acepto"
            }
            GlobalScope.launch(Dispatchers.Main) {

                try {
                    binding.progressBar2.visibility = View.VISIBLE

                    val response = withContext(Dispatchers.IO) {
                        ApiFavorito.build().updateFav(request = RegisterFavoritoRequets(idpeli,nomcli,emailcli,nompeli,resenia,terminos))
                        // Api.build().savePet(request = RegisterPetRequest(customer, petName, age))
                    }

                    if (response.isSuccessful) {
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@RegisterEditFavoritoAdminActivity, it.resultMessage, Toast.LENGTH_LONG).show()
                            //Toast.makeText(this@RegisterEditPeliculaActivity,"Se ha registrado una nueva Pelicula",
                            //Toast.LENGTH_LONG).show()
                        }
                    }

                } catch (ex: IOException) {
                    Toast.makeText(
                        this@RegisterEditFavoritoAdminActivity,
                        "La pelicula favorita ha sido Registrada",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterEditFavoritoAdminActivity, ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    binding.progressBar2.visibility = View.GONE

                    binding.edtIdPeli.setText("")
                    binding.edtNomCli.setText("")
                    binding.edtEmaCli.setText("")
                    binding.edtPeli.setText("")
                    //
                    binding.edtResenia.setText("")

                }
            }
        }




    }
}