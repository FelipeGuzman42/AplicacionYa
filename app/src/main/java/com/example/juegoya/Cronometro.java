package com.example.juegoya;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cronometro extends AppCompatActivity{
    private long tiempo = 65000;
    Button Regreso;
    TextView Reloj;
    Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        Intent intent = getIntent();
        String message = intent.getStringExtra("tema");

        Regreso = findViewById(R.id.Regreso);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        Reloj = findViewById(R.id.Reloj);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Regreso.setOnClickListener(salir);
        Reloj.setOnClickListener(salir);
        new CountDownTimer(tiempo, 1000) {
            public void onTick(long l) {
                tiempo = l;
                int min = (int)tiempo / 60000;
                int sec = (int) tiempo % 60000 / 1000;
                String texto = ""+min+":";
                if(sec<10) texto += "0";
                texto += sec;
                Reloj.setText(texto);
            }

            public void onFinish() {
                Reloj.setText("YA!");
                v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }.start();

    }
    private View.OnClickListener salir = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            v.cancel();
            finish();
        }
    };
}
