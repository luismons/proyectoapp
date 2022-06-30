package com.example.proyectoapp;

import static java.lang.Boolean.TRUE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.Hashtable;
import java.util.Map;

public class registro extends AppCompatActivity {

    String URL_SERVIDOR = "http://192.168.0.101/basedatos/registro.php";

    EditText regUsuario, regPassword;
    Button  btnRegistrar;

    private BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        regUsuario=findViewById(R.id.edtUsuario);
        regPassword=findViewById(R.id.edtPassword);

        //
        // Configuración bottomAppBar
        //
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        configurarBottomAppBar(bottomAppBar);

    }
    public void registrar(){
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("ERROR 1")) {
                    Toast.makeText(registro.this, "Favor Completar los datos", Toast.LENGTH_SHORT).show();
                } else if (response.equals("ERROR 2")) {
                    Toast.makeText(registro.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                } else if (response.equals("MENSAJE")){
                    Toast.makeText(registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(registro.this, "ERROR AL REGISTRARSE", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new Hashtable<String, String>();
                parametros.put("Rut", regUsuario.getText().toString().trim());
                parametros.put("password", regPassword.getText().toString().trim());


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(registro.this);
        requestQueue.add(stringRequest);

    }
    public void configurarBottomAppBar(BottomAppBar bottomAppBar){
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        home();
                        return true;
                    case R.id.notificar:

                        return true;
                    case R.id.perfilar:
                        perfilar();
                        return true;
                    case R.id.asignarTurnos:
                        asignarTurnos();
                        return true;
                    case R.id.nuevoContrato:
                        nuevoContrato();
                        return true;
                    case R.id.registroConexion:
                        registroConexiones();
                        return true;
                    case R.id.reporteDigitacion:
                        reporteDigitacion();
                        return true;
                    case R.id.pedidosDigitar:
                        digitarPedido();
                        return true;
                    case R.id.pedidosDigitalizar:
                        digitalizarPedido();
                        return true;
                    case R.id.ingresarBoleta:
                        ingresarBoleta();
                        return true;
                    case R.id.reporteDigitalizacion:
                        reporteDigitalizacion();
                        return true;
                    case R.id.exportar:
                        exportar();
                        return true;
                    default:
                        return false;
                }

            }
        });
    }
    public void home(){
        Intent home = new Intent(this,cuenta.class);
        startActivity(home);
    }
    public void notificar(){
        Intent home = new Intent(this,notificar_hhee.class);
        startActivity(home);
    }
    public void perfilar(){
        Intent home = new Intent(this,perfilar_listausuarios.class);
        startActivity(home);
    }
    public void asignarTurnos(){
        Intent home = new Intent(this,asignacion_de_turnos.class);
        startActivity(home);
    }
    public void nuevoContrato(){
        Intent home = new Intent(this,nuevo_contrato.class);
        startActivity(home);
    }
    public void registroConexiones(){
        Intent home = new Intent(this,registro_conexiones.class);
        startActivity(home);
    }
    public void reporteDigitacion(){
        Intent home = new Intent(this,reporte_digitacion.class);
        startActivity(home);
    }
    public void digitarPedido(){
        Intent home = new Intent(this,digitar_pedido.class);
        startActivity(home);
    }
    public void digitalizarPedido(){
        Intent digitarPedido = new Intent(this,digitalizar_pedido.class);
        startActivity(digitarPedido);
    }
    public void ingresarBoleta(){
        Intent digitarPedido = new Intent(this,ingresar_boleta.class);
        startActivity(digitarPedido);
    }
    public void reporteDigitalizacion(){
        Intent digitarPedido = new Intent(this,reporte_digitalizacion.class);
        startActivity(digitarPedido);
    }
    public void exportar(){
        Intent digitarPedido = new Intent(this,exportar.class);
        startActivity(digitarPedido);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return TRUE;
    }
}