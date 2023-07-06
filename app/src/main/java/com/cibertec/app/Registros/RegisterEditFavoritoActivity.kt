package com.cibertec.app.Registros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.databinding.ActivityRegisterEditFavoritoBinding
import com.cibertec.app.model.RegisterFavoritoRequets
import com.cibertec.app.networking.ApiFavorito
import com.cibertec.app.networking.ApiPelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegisterEditFavoritoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEditFavoritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register_edit_pelicula)
        binding = ActivityRegisterEditFavoritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                Toast.makeText(this@RegisterEditFavoritoActivity,"Ingrese todos los campos ", Toast.LENGTH_LONG).show()
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
                            Toast.makeText(this@RegisterEditFavoritoActivity, it.resultMessage, Toast.LENGTH_LONG).show()
                            //Toast.makeText(this@RegisterEditPeliculaActivity,"Se ha registrado una nueva Pelicula",
                            //Toast.LENGTH_LONG).show()
                        }
                    }

                } catch (ex: IOException) {
                    Toast.makeText(
                        this@RegisterEditFavoritoActivity,
                        "La pelicula favorita ha sido Registrada",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterEditFavoritoActivity, ex.message, Toast.LENGTH_LONG).show()
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
            binding.btnSave5.setOnClickListener{

                onBackPressed()
            }


        }
    }
}