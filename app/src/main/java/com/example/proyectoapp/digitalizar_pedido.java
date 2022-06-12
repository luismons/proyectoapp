package com.example.proyectoapp;

import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class digitalizar_pedido extends AppCompatActivity {

    String url="192.168.0.101/basedatos/digitalizar.php";
    private TableLayout tbdigitalizar;

    private BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitalizar_pedido);

        //
        // Configuración bottomAppBar
        //
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        configurarBottomAppBar(bottomAppBar);
    }
    public void llenarTabla(View view){
        tbdigitalizar = findViewById(R.id.tbdigitalizar);
        tbdigitalizar.removeAllViews();
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    JSONArray array = new JSONArray();
                    public void onResponse(JSONObject response){
                        try {
                            array = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < array.length(); i++) {
                            try {
                                JSONObject object = array.getJSONObject(i);

                                TableRow registro = (TableRow) LayoutInflater.from(digitalizar_pedido.this).inflate(R.layout.table_row_digitalizar, null, false);
                                TextView txtClienteDigitalizar = registro.findViewById(R.id.txtClienteDigitalizar);
                                TextView txtMesProcesodigitalizar = registro.findViewById(R.id.txtMesProcesodigitalizar);
                                TextView txtEncargadoDigitalizar = registro.findViewById(R.id.txtEncargadoDigitalizar);
                                TextView txtEstadoDigitalizar = registro.findViewById(R.id.txtEstadoDigitalizar);
                                ImageButton btnDigitalizar = registro.findViewById(R.id.btnDigitalizar);
                                ImageButton btnFinalizar = registro.findViewById(R.id.btnFinalizar);

                                txtClienteDigitalizar.setText(object.getString("nombreCliente"));
                                txtMesProcesodigitalizar.setText(object.getString("mesprocesamiento"));
                                txtEncargadoDigitalizar.setText(object.getString("idTextoPlano"));
                                txtEstadoDigitalizar.setText(object.getString("estado"));
                                btnDigitalizar.setId(Integer.parseInt(object.getString("idPedido")));
                                btnFinalizar.setId(Integer.parseInt(object.getString("idPedido")));
                                tbdigitalizar.addView(registro);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }
    public void clickDigitalizar (View view){
        String viewidst = String.valueOf(view.getId());
        Toast.makeText(this,String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();

    }
    public void clickFinalizar (View view){
        String viewidst = String.valueOf(view.getId());
        Toast.makeText(this,String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();

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