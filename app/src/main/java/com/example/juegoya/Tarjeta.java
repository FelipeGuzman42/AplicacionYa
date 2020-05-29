package com.example.juegoya;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tarjeta {
    private ArrayList<String> temasR = new ArrayList(128);
    private ArrayList<String> temasA = new ArrayList(128);
    private int tamanoR = 0, tamanoA = 0, escogido = 0;
    private Context c;

    public Tarjeta(Context con) {
        c = con;
        crear();
    }

    private void crear() {
        try {
            String UTF8 = "ISO-8859-1";
            int BUFFER_SIZE = 8192;
            InputStream file = c.getAssets().open("tarjetasrosadas.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(file, UTF8),BUFFER_SIZE);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                temasR.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            String UTF8 = "ISO-8859-1";
            int BUFFER_SIZE = 8192;
            InputStream file = c.getAssets().open("tarjetasazules.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(file, UTF8),BUFFER_SIZE);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                temasA.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        tamanoR = temasR.size();
        tamanoA = temasA.size();
    }

    public String sacarTarjetaR(){
        escogido = (int)(Math.random() * tamanoR + 1);
        String rtn = temasR.remove(escogido);
        tamanoR--;
        return rtn;
    }
    public String sacarTarjetaA(){
        escogido = (int)(Math.random() * tamanoA + 1);
        String rtn = temasA.remove(escogido);
        tamanoA--;
        return rtn;
    }

    public int tarjetasRestantes(){
        return tamanoA+tamanoR;
    }

    public void reordenar(){
        ArrayList<String> copia;
        if(tamanoA == temasA.size()){
            for(int i = escogido+1; i<temasR.size()-1; i++){
                copia = temasR;
                copia.set(i-1, temasR.get(i));
                temasR = copia;
                temasR.ensureCapacity(tamanoR);
            }
        } else{
            for(int i = escogido+1; i<temasA.size()-1; i++){
                copia = temasA;
                copia.set(i-1, temasA.get(i));
                temasA = copia;
                temasA.ensureCapacity(tamanoA);
            }
        }
    }

    public void reinicio(){
        temasR.clear();
        temasA.clear();
        crear();
    }
}
