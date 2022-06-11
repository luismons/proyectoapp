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

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class nuevo_contrato extends AppCompatActivity {

    public EditText contrato,cliente,fechainicio,fechatermino,fechaCaducidad,tiempocaducidad,correo;
    public Button btncontrato;

    RequestQueue requestQueue;
    private static final String urlservidor="http://192.168.43.131/basedatos/nuevo_contrato.php";

    private BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contrato);

        requestQueue= Volley.newRequestQueue(this);

        contrato=(EditText) findViewById(R.id.ncContrato);
        cliente=(EditText)findViewById(R.id.ncCliente);
        fechainicio=(EditText) findViewById(R.id.ncFechaInicio);
        fechatermino=(EditText) findViewById(R.id.ncFechaTermino);
        fechaCaducidad=(EditText) findViewById(R.id.ncCaducidad);
        tiempocaducidad=(EditText) findViewById(R.id.ncCaducidadImagenes);
        correo=(EditText) findViewById(R.id.ncCorreo);

        //
        // Configuración bottomAppBar
        //
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        configurarBottomAppBar(bottomAppBar);
    }

    public void guardarContrato(View view){

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST, urlservidor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(nuevo_contrato.this, "Contrato Ingresado", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("contrato",contrato.getText().toString());
                params.put("cliente",cliente.getText().toString());
                params.put("fecha_inicio",fechainicio.getText().toString());
                params.put("fecha_termino",fechatermino.getText().toString());
                params.put("tiempo_caducidadimg",tiempocaducidad.getText().toString());
                params.put("correo",correo.getText().toString());

                return params;
            }
        };
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
                        notificar();
                        return true;
                    case R.id.perfilar:
                        perfilar();
                        return true;
                    case R.id.asignarTurnos:
                        asignarTurnos();
                        return true;
                    case R.id.nuevoContrato:

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