package com.example.external;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);




    }

    public void Guardar(View view){
        String nombre = et1.getText().toString();
        String content = et2.getText().toString();

        try{
            File tarjeta_sd = Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjeta_sd.getPath(),Toast.LENGTH_SHORT).show();
            File rutaArchivo = new File(tarjeta_sd.getPath(), nombre);
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));


            crearArchivo.write(content);
            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this,"guarfafo bien", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");

        }catch (IOException e){
            Toast.makeText(this,"no se pudo guardar",Toast.LENGTH_SHORT).show();
        }

    }

    public void Consultar(View view){
        String nombreArchivo =et1.getText().toString();


        try{
            File tarjeta_sd = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File (tarjeta_sd.getPath(),nombreArchivo);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombreArchivo));


            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            String contenidoCompleto ="";


            while (linea != null){
                contenidoCompleto = contenidoCompleto + linea + "\n";
                linea = leerArchivo.readLine();
            }

            leerArchivo.close();
            abrirArchivo.close();
            et2.setText(contenidoCompleto);

        }catch (IOException e){
            Toast.makeText(this,"error al leer",Toast.LENGTH_SHORT).show();
        }
    }
}
