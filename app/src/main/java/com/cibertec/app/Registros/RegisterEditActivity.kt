package com.cibertec.app.Registros
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.database.AppDatabase
import com.cibertec.app.databinding.ActivityRegisterEditBinding
import com.cibertec.app.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterEditActivity : AppCompatActivity() {

        private lateinit var binding: ActivityRegisterEditBinding

        private lateinit var  appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEditBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_register_edit)
        setContentView(binding.root)

        val spDocuments: AutoCompleteTextView = findViewById(R.id.spDocuments)
        val DocumentsList: List<String> = listOf("DNI", "Carnet de Extranjería", "Pasaporte", "RUT")

        spDocuments.setAdapter(
            ArrayAdapter(this,R.layout.item_spinner,DocumentsList)
        )

        spDocuments.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, position, l ->
            val selected = DocumentsList[position]
            println(selected)
        }

        binding.btnSave.setOnClickListener{

            val nomUser = binding.edtNames.text.toString()
            val apeUser = binding.edtPeli.text.toString()
            val emailUser = binding.edtDescrip.text.toString()
            val passwordUser = binding.edtPassword.text.toString()
            val phoneUser = binding.edtPhone.text.toString()

            val tipdocUser =  binding.spDocuments.text.toString()

            val nrdocUser = binding.edtPrecio.text.toString()
            val genero = if(binding.rbMasculino.isChecked)"Masculino" else "Femenino"
            val chkTerms = binding.chkTerms.text.toString()

            //COMBOBOX
            if (tipdocUser == "DNI") {
                binding.spDocuments.text.toString()
            }
            if (tipdocUser == "Carnet de Extranjería") {
                binding.spDocuments.text.toString()
            }
            if (tipdocUser == "Pasaporte") {
                binding.spDocuments.text.toString()
            }
            if (tipdocUser == "RUT") {
                binding.spDocuments.text.toString()
            }


            if (nomUser.isEmpty()){
                Toast.makeText(this,"Ingresa el Nombre de Usuario",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (apeUser.isEmpty()){
                Toast.makeText(this,"Ingresa el Apellido del Usuario",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (emailUser.isEmpty()){
                Toast.makeText(this,"Ingresa el Correo de Usuario",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (phoneUser.isEmpty()){
                Toast.makeText(this,"Ingresa el numero del Usuario",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (passwordUser.isEmpty()){
                Toast.makeText(this,"Ingresa la contraseña del Usuario",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (nrdocUser.isEmpty()){
                Toast.makeText(this,"Ingresa el numero de documento",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!binding.rbMasculino.isChecked && !binding.rbFemenino.isChecked ) {
                Toast.makeText(this, "Selecciona uno de los dos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val user = User(0,nomUser,apeUser,emailUser,phoneUser, passwordUser,tipdocUser,nrdocUser,genero)
            appDatabase = AppDatabase.getInstance(this)
            GlobalScope.launch(Dispatchers.Main) {
                binding.progressBar2.visibility = View.VISIBLE
                withContext(Dispatchers.IO){
                    appDatabase.userDao().insert(user)
                    //appDatabase.userDao().insert(user)
                }
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(this@RegisterEditActivity,"Usuario Registrado Correctamente", Toast.LENGTH_LONG).show()
            }

        } //FIN DEL BOTON


        binding.btnSave2.setOnClickListener{
            onBackPressed()
        }




    }
}