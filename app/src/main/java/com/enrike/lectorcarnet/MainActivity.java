package com.enrike.lectorcarnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button escanear, registrare, registrars, ver;
    TextView resultado;
    String [] arrContents = new String[3];
    String [] arrcc = new String[1];
    int vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escanear = findViewById(R.id.scan);
        registrare = findViewById(R.id.registrar);
        registrars = findViewById(R.id.registrar2);
        ver = findViewById(R.id.registro);
        resultado = findViewById(R.id.resultado);

        final Conexion connect = new Conexion(getApplicationContext());

        escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector Carnet Unitrópico");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });

        registrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vc == 1){
                    arrcc = arrContents[1].split(" ");
                    String id = arrcc[1];
                    String name = arrContents[0];
                    String carr = arrContents[3];

                    Persona persona = new Persona();

                    connect.obtenerpersona(persona, id);

                    if (persona.getId()==null){
                        ZonedDateTime timestamp = ZonedDateTime.now();
                        String fechaentrada = timestamp.format(DateTimeFormatter.ofPattern("MM.dd.yyy, hh.mm.ss a"));
                        String fechasalida = "No hay registro";
                        connect.agregar(id, name, carr, fechaentrada, fechasalida);
                        Toast.makeText(getApplicationContext(), "---REGISTRO EXITOSO---", Toast.LENGTH_LONG).show();
                        resultado.setText("");
                    }else{
                        if (persona.getSalida().contains("No hay registro")){
                            Toast.makeText(getApplicationContext(), "---REGISTRO ANTERIOR PENDIENTE---", Toast.LENGTH_LONG).show();
                            resultado.setText("");
                        }else{
                            ZonedDateTime timestamp = ZonedDateTime.now();
                            String fechaentrada = timestamp.format(DateTimeFormatter.ofPattern("MM.dd.yyy, hh.mm.ss a"));
                            String fechasalida = "No hay registro";
                            connect.agregar(id, name, carr, fechaentrada, fechasalida);
                            Toast.makeText(getApplicationContext(), "---REGISTRO EXITOSO---", Toast.LENGTH_LONG).show();
                            resultado.setText("");
                        }

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "---REALICE EL ESCANEO PRIMERO---", Toast.LENGTH_LONG).show();
                }
            }
        });

        registrars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vc == 1){
                    arrcc = arrContents[1].split(" ");
                    String id = arrcc[1];
                    String name = arrContents[0];
                    String carr = arrContents[3];

                    Persona persona = new Persona();

                    connect.obtenerpersona(persona, id);

                    if (persona.getId()==null){
                        Toast.makeText(getApplicationContext(), "---NO SE HA REGISTRADO UNA ENTRADA---", Toast.LENGTH_LONG).show();
                        resultado.setText("");
                    }else{
                        if (persona.getSalida().equals("No hay registro")){
                            ZonedDateTime timestamp = ZonedDateTime.now();
                            String fechaentrada = persona.getEntrada();
                            String fechasalida = timestamp.format(DateTimeFormatter.ofPattern("MM.dd.yyy, hh.mm.ss a"));
                            connect.agregar(id, name, carr, fechaentrada, fechasalida);
                            Toast.makeText(getApplicationContext(), "---REGISTRO EXITOSO---", Toast.LENGTH_LONG).show();
                            resultado.setText("");
                            connect.borrarpersona(id,"No hay registro");
                        }else{
                            Toast.makeText(getApplicationContext(), "---YA SE REGISTRÓ LA SALIDA---", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "---REALICE EL ESCANEO PRIMERO---", Toast.LENGTH_LONG).show();
                }
            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
                vc = 0;
            }else{

                arrContents = result.getContents().split("\n");

                Toast.makeText(this, "Escaneo correcto", Toast.LENGTH_SHORT).show();
                resultado.setText(result.getContents());
                vc = 1;
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
            vc = 0;
        }
    }
}