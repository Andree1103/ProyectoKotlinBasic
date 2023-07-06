package com.cibertec.app.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cibertec.app.Registros.RegisterEditActivity
import com.cibertec.app.database.AppDatabaseInvitados
import com.cibertec.app.databinding.ActivityLogueoBinding
import com.cibertec.app.model.Invitados
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LogueoActivity : AppCompatActivity() {


    private lateinit var binding : ActivityLogueoBinding

    private lateinit var  appDatabase: AppDatabaseInvitados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogueoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_logueo)


        binding.btnJoin.setOnClickListener {
            val intent = Intent(this, RegisterEditActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            val nombre = binding.edtUsuario.text.toString()
            val correo = binding.edtCorreo.text.toString()

            if (nombre.isEmpty()){
                Toast.makeText(this,"Ingresa tu Nombre", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (correo.isEmpty()){
                Toast.makeText(this,"Ingresa tu Correo",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //instanciar clase
            val invitados = Invitados(0,nombre,correo)
            appDatabase = AppDatabaseInvitados.getInstance(this)
            GlobalScope.launch(Dispatchers.Main) {

                withContext(Dispatchers.IO){
                    appDatabase.invitadosDAO().insert(invitados)
                    //appDatabase.userDao().insert(user)
                }
                Toast.makeText(this@LogueoActivity,"Invitado Registrado Correctamente", Toast.LENGTH_LONG).show()
            }
            val intent = Intent(this, MenuInvitado::class.java)
            startActivity(intent)
        }


    }




}