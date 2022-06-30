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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class exportar extends AppCompatActivity {
    String obtenerMesesPHP="registrosPedidosObtenerMeses.php";
    String pedidosExportarPHP="registrosPedidosExportar.php";
    String urlPedidosExportar = null;
    String urlPedidosMeses = null;
    private BottomAppBar bottomAppBar;
    private TableLayout tbExportar;
    private Spinner spinnerMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);
        //
        // Configuración bottomAppBar
        //
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        configurarBottomAppBar(bottomAppBar);
        //
        // Configuración conexión
        //
        String ip = getString(R.string.ipServidor);
        String carpetaPhp = getString(R.string.carpetaPhp);
        urlPedidosExportar = "http://"+ ip +"/"+ carpetaPhp +"/"+pedidosExportarPHP;
        urlPedidosMeses = "http://" + ip +"/"+carpetaPhp+"/"+obtenerMesesPHP;

        //
        // Configuración Spinner
        //
        spinnerMes = findViewById(R.id.seleccionarMesSpinner);

        ArrayList<String> meses = new ArrayList<>();
        meses.add("Seleccionar mes...");

        spinnerMes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meses));
        //json Spinner
        RequestQueue queue2 = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlPedidosMeses, null,
                new Response.Listener<JSONObject>(){
                    JSONArray array = new JSONArray();
                    public void onResponse(JSONObject response){
                        try {
                            array = response.getJSONArray("data");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = null;
                            try {
                                object = array.getJSONObject(i);
                                meses.add(object.getString("mes"));
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
        queue2.add(jsonObjectRequest);
    }

    //
    // Configuración tabla
    //
    public void llenarTabla(View view){
        tbExportar = findViewById(R.id.tbExportar);
        tbExportar.removeAllViews();
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, urlPedidosExportar,
                new Response.Listener<String>(){
                    JSONArray array = new JSONArray();

                    public void onResponse(String response){
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            array = jsonObject.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (array.length() == 0){
                            TableRow registro = (TableRow) LayoutInflater.from(exportar.this).inflate(R.layout.table_row_sin_registros, null, false);
                            tbExportar.addView(registro);

                        }
                        for (int i = 0; i < array.length(); i++) {
                            try {
                                JSONObject object = array.getJSONObject(i);

                                TableRow registro = (TableRow) LayoutInflater.from(exportar.this).inflate(R.layout.table_row_exportar, null, false);
                                TextView colCliente = registro.findViewById(R.id.txtClienteExportar);
                                TextView colMes = registro.findViewById(R.id.txtMesProcesoExportar);
                                TextView colCaducidad = registro.findViewById(R.id.txtCaducidadExportar);
                                TextView colEstado = registro.findViewById(R.id.txtEstadoExportar);
                                ImageButton colDetalle = registro.findViewById(R.id.btnDetalleExportar);
                                ImageButton colExportar = registro.findViewById(R.id.btnExportarExportar);
                                colCliente.setText(object.getString("nombreCliente"));
                                colMes.setText(object.getString("mes"));
                                colCaducidad.setText(object.getString("idTextoPlano"));
                                colEstado.setText(object.getString("estado"));
                                colExportar.setId(Integer.parseInt(object.getString("idPedido")));
                                colDetalle.setId(Integer.parseInt(object.getString("idPedido")));
                                tbExportar.addView(registro);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
                )
        {
            @Override
            protected Map<String, String> getParams()
            {
                TextView numeroContrato = findViewById(R.id.numContratoTxtExportar);
                Spinner spinnerMes = findViewById(R.id.seleccionarMesSpinner);


                Map<String, String>  params = new HashMap<String, String>();
                params.put("mes", (String) spinnerMes.getSelectedItem());
                params.put("numeroContrato", numeroContrato.getText().toString());



                return params;
            }
        };

            queue.add(postRequest);
    }
    //
    // Botones en tabla
    //
    public void clickDetalleExportar(View view){
        String viewidst = String.valueOf(view.getId());
        Toast.makeText(this,String.valueOf(view.getId()), Toast.LENGTH_LONG).show();
    }
    public void clickExportar(View view){
        String viewidst = String.valueOf(view.getId());
        Toast.makeText(this,String.valueOf(view.getId()), Toast.LENGTH_LONG).show();
    }

    //
    // Configuración BottomAppBar
    //
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
                        digitalizarPedido();
                        return true;
                    case R.id.ingresarBoleta:
                        ingresarBoleta();
                        return true;
                    case R.id.reporteDigitalizacion:
                        reporteDigitalizacion();
                        return true;
                    case R.id.exportar:

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