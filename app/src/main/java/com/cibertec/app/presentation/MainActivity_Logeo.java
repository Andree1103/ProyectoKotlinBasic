package com.cibertec.app.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.app.Adapter.AdminSQLOpenHelper;
import com.cibertec.app.R;

public class MainActivity_Logeo extends AppCompatActivity {
    private EditText etUsuario,etPassword;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logeo);

        etUsuario= findViewById(R.id.edtUser);
        etPassword= findViewById(R.id.edtContrasenia);
        btnIngresar= findViewById(R.id.btnEntrar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
    }

    public void ingresar() {
        try {
            AdminSQLOpenHelper admin = new AdminSQLOpenHelper(this,"administracion",null,1);
            SQLiteDatabase bd=admin.getWritableDatabase();
            String usuario=etUsuario.getText().toString();
            String password=etPassword.getText().toString();
            Cursor fila=bd.rawQuery("Select usuario, password from admin3 where usuario='"+usuario+"'and password='"
                    +password+"'", null);

            if (fila.moveToFirst()){
                Intent i =new Intent(this, MainActivity.class);
                i.putExtra("cedula",usuario);
                startActivity(i);
            }else {
                Cursor fila2=bd.rawQuery("Select cedula, nombres from personas where cedula='"+ usuario + "" +
                        "' and nombres='" + password + "'",null);

            if (fila2.moveToFirst()){
                Intent i = new Intent(this,MainActivity.class);
                i.putExtra("cedula",usuario);
                startActivity(i);
            }else{
                etPassword.setText("");
                Toast.makeText(this,"Usuario o contraseña incorrectos Intene otravez",Toast.LENGTH_LONG).show();
                bd.close();
            }
            }
        }catch (Exception e){
            Toast.makeText(this,"Error en database" + e,Toast.LENGTH_LONG).show();
        }

    }
}