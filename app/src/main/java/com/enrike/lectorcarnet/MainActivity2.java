package com.enrike.lectorcarnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView datos;
    private AdapterList rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        datos=findViewById(R.id.RvContenido);
        datos.setLayoutManager(new LinearLayoutManager(this));

        final Conexion connect = new Conexion(getApplicationContext());

        rl= new AdapterList(connect.GetAll_register());
        datos.setAdapter(rl);
    }
}