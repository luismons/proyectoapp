package com.example.proyectoapp;

import static java.lang.Boolean.TRUE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Hashtable;
import java.util.Map;


public class MainActivity extends AppCompatActivity{

    String URL_SERVIDOR = "http://192.168.0.101/basedatos/validar_usuario.php";

    EditText loUsuario, loPassword;
    Button  btnLogin, btnRegistrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loUsuario=findViewById(R.id.edtUsuario);
        loPassword=findViewById(R.id.edtPassword);
        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,registro.class);
                startActivity(intent);
            }
        });

    }
    public void login() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("ERROR 1")) {
                    Toast.makeText(MainActivity.this, "Favor Completar los datos", Toast.LENGTH_SHORT).show();
                } else if (response.equals("ERROR 2")) {
                    Toast.makeText(MainActivity.this, "No existe Usuario", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR AL INICIAR SESION", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new Hashtable<String, String>();
                parametros.put("rut", loUsuario.getText().toString().trim());
                parametros.put("password", loPassword.getText().toString().trim());


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

}
