package com.cibertec.app.Registros
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cibertec.app.R
import com.cibertec.app.databinding.ActivityRegisterVentaClienteBinding
import com.cibertec.app.model.RegisterVentaRequest
import com.cibertec.app.networking.ApiVenta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

class RegisterVentaClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterVentaClienteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register_venta)
        binding =  ActivityRegisterVentaClienteBinding.inflate(layoutInflater)
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
            val publico2 = it.getString("KEY_DESCRIPCION8")?: ""

            binding.edtIdVenta.setText(codigo)
            binding.edtNombre.setText(titulo)
            binding.edtCorreo.setText(descripcion)
            binding.edtDireccion.setText(duracion)
            binding.edtDni.setText(genero)
            binding.edtNombrePeli.setText(precio)
            binding.etCantidad.setText(stock)
            binding.spPago.setText(publico)
            binding.checkBox.text = publico2
            binding.btnRegister.visibility = View.GONE
        }?: kotlin.run {
            //Grbar Bundle_ nulo
            binding.edtIdVenta.setText("")
            binding.edtNombre.setText("")
            binding.edtCorreo.setText("")
            binding.edtDireccion.setText("")
            binding.edtDni.setText("")
            binding.edtNombrePeli.setText("")
            binding.etCantidad.setText("")
            binding.spPago.setText("")
            binding.btnRegister.visibility = View.VISIBLE
        }

        val spPagos : AutoCompleteTextView = findViewById(R.id.spPago)
        val PagosList: List<String> = listOf("Efectico", "Yape", "Tarjeta", "Plin")

        spPagos.setAdapter(
            ArrayAdapter(this,R.layout.item_spinner_pago,PagosList)
        )
        spPagos.onItemClickListener=AdapterView.OnItemClickListener{
            adapterView, view, position, l ->
            val selected = PagosList[position]
            println(selected)
        }

        binding.btnRegister.setOnClickListener{
            val venta = binding.edtIdVenta.text.toString()
            val nombre = binding.edtNombre.text.toString()
            val correo = binding.edtCorreo.text.toString()
            val direccion = binding.edtDireccion.text.toString()
            val dni = binding.edtDni.text.toString()
            val peli = binding.edtNombrePeli.text.toString()
            val cantidad = binding.etCantidad.text.toString()
            val pago = binding.spPago.text.toString()
            val terminos = "Aceptado"
            if (!binding.checkBox.isChecked){
                Toast.makeText(this, "Porfavor aceptar los Terminos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (venta.isEmpty() || nombre.isEmpty() || correo.isEmpty() || direccion.isEmpty()
                || dni.isEmpty()
                || peli.isEmpty()|| cantidad.isEmpty()|| pago.isEmpty()|| terminos.isEmpty()) {
                Toast.makeText(this@RegisterVentaClienteActivity,"Ingrese todos los campos ", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }



            GlobalScope.launch(Dispatchers.Main) {
                try {
                    // progres var
                    val response = withContext(Dispatchers.IO) {
                        ApiVenta.build().saveVenta(request = RegisterVentaRequest(venta,nombre,correo,direccion,dni,peli,cantidad, pago,terminos))
                    }
                    if (response.isSuccessful){
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@RegisterVentaClienteActivity, "Registro Correcto", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@RegisterVentaClienteActivity,  response.message(), Toast.LENGTH_SHORT).show()
                    }
                }catch ( ex: IOException) {
                    Toast.makeText(this@RegisterVentaClienteActivity, "Registro Correcto", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterVentaClienteActivity, ex.message, Toast.LENGTH_SHORT).show()
                } finally {
                    binding.edtIdVenta.setText("")
                    binding.edtNombre.setText("")
                    binding.edtCorreo.setText("")
                    binding.edtDireccion.setText("")
                    binding.edtDireccion.setText("")
                    binding.edtNombrePeli.setText("")
                    binding.etCantidad.setText("")
                    binding.edtDni.setText("")
                }
            }
        }

        



    }
}