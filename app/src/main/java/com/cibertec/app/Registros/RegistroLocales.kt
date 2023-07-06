package com.cibertec.app.Registros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.database.AppDatabaseLocales
import com.cibertec.app.databinding.ActivityRegistroLocalesBinding
import com.cibertec.app.model.Locales
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistroLocales : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroLocalesBinding

    private lateinit var  appDatabase: AppDatabaseLocales
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroLocalesBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_register_locales)
        setContentView(binding.root)

        val spDistr: AutoCompleteTextView = findViewById(R.id.spDistritos)
        val spDistritos: List<String> = listOf("LIMA", "MIRAFLORES", "SAN ISIDRO", "SURCO", "SAN MIGUEL")

        spDistr.setAdapter(
            ArrayAdapter(this, R.layout.item_spinner,spDistritos)
        )
        spDistr.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, position, l ->
            val selected = spDistritos[position]
            println(selected)
        }

        binding.btnSaveLocal.setOnClickListener{
            val nomLoc = binding.edtNombreLocal.text.toString()
            val desLoc = binding.edtDescripcionLocal.text.toString()
            val emailLoc = binding.edtEmailLocal.text.toString()
            val phoneLoc = binding.edtPhoneLocal.text.toString()
            val disLoc =  binding.spDistritos.text.toString()
            val tipLoc = if(binding.rbPremiun.isChecked)"Premiun" else "Normal"
            val chkTerms =if(binding.chkTermsLocal.isChecked) "SI" else "NO"

            //COMBOBOX
            if (disLoc == "LIMA") {
                binding.spDistritos.text.toString()
            }
            if (disLoc == "MIRAFLORES") {
                binding.spDistritos.text.toString()
            }
            if (disLoc == "SAN ISIDRO") {
                binding.spDistritos.text.toString()
            }
            if (disLoc == "SURCO") {
                binding.spDistritos.text.toString()
            }
            if (disLoc == "SAN MIGUEL") {
                binding.spDistritos.text.toString()
            }

            if (nomLoc.isEmpty()){
                Toast.makeText(this,"Ingresa el Nombre del Local",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (desLoc.isEmpty()){
                Toast.makeText(this,"Ingresa la Descripcion del Local",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (emailLoc.isEmpty()){
                Toast.makeText(this,"Ingresa el correo del Local",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (phoneLoc.isEmpty()){
                Toast.makeText(this,"Ingresa el numero del Local",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!binding.rbPremiun.isChecked && !binding.rbNormal.isChecked ) {
                Toast.makeText(this, "Selecciona uno de los dos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!binding.chkTermsLocal.isChecked) {
                Toast.makeText(this, "Acepta los terminos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            //instanciar clase
            val locales = Locales(0,nomLoc,desLoc,emailLoc,phoneLoc,disLoc, tipLoc,chkTerms)
            appDatabase = AppDatabaseLocales.getInstance(this)
            GlobalScope.launch(Dispatchers.Main) {
                binding.progressBar.visibility = View.VISIBLE
                withContext(Dispatchers.IO){
                    appDatabase.localDao().insert(locales)
                    //appDatabase.userDao().insert(user)
                }
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@RegistroLocales,"Local Registrado Correctamente", Toast.LENGTH_LONG).show()
            }


        }
        binding.btnAtras.setOnClickListener{
            onBackPressed()
        }
    }
}