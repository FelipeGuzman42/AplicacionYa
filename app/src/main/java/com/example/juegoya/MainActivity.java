package com.example.juegoya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean otraActividad = false;
    private Tarjeta tarjetas = null;
    TextView TRestantes = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tarjetas = new Tarjeta(this);
        TRestantes = findViewById(R.id.TRestante);
        TRestantes.setText("Quedan "+tarjetas.tarjetasRestantes()+" tarjetas");
    }
    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();
        if(!otraActividad){
           return;
        }
        tarjetas.reordenar();
        TRestantes.setText("Quedan "+tarjetas.tarjetasRestantes()+" tarjetas");
    }

    public void cartasRosadas(View view){
        Intent intent = new Intent(this, Cronometro.class);
        String message = tarjetas.sacarTarjetaR();
        intent.putExtra("tema", message);
        otraActividad = true;
        startActivity(intent);
    }
    public void cartasAzules(View view){
        Intent intent = new Intent(this, Cronometro.class);
        String message = tarjetas.sacarTarjetaA();
        intent.putExtra("tema", message);
        otraActividad = true;
        startActivity(intent);
    }
    public void temaPropio(View view){
        Intent intent = new Intent(this, Cronometro.class);
        String message = "TEMA PROPIO";
        intent.putExtra("tema", message);
        otraActividad = true;
        startActivity(intent);
    }
    public void botonReinicio(View view){
        tarjetas.reinicio();
        TRestantes.setText("Quedan "+tarjetas.tarjetasRestantes()+" tarjetas");
    }
}
