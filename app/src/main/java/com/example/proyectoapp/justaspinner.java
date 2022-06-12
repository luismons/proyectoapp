package com.example.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class justaspinner extends AppCompatActivity {
    private Spinner spinerCategoriaLugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justaspinner);
        spinerCategoriaLugar = findViewById(R.id.spinnerCategoriaLugar);

        String[] categorias = {"Maquilero", "Deshebrado"};

        spinerCategoriaLugar.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias));


    }
}