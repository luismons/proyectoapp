package com.example.proyectoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {

     public EditText loginusuario,loginpassword;
     public Button   btnregistrar;

     RequestQueue requestQueue;
    private static final String urlservidor="http://192.168.43.131/basedatos/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestQueue= Volley.newRequestQueue(this);

        loginusuario=(EditText) findViewById(R.id.idRut);
        loginpassword=(EditText)findViewById(R.id.idPassword);
        btnregistrar=(Button) findViewById(R.id.btnLogin);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlservidor=("http://192.168.43.131/basedatos/login.php");
            }
        });


    }
    private void validarUsuario(String urlservidor){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlservidor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    Intent intent=new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this, "Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                    
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new  HashMap<String,String>();
                parametros.put("rut",loginusuario.getText().toString());
                parametros.put("password",loginpassword.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
}

}
