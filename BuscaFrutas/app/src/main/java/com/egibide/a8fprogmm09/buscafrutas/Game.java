package com.egibide.a8fprogmm09.buscafrutas;


import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by jon on 15/02/2017.
 */

public class Game{

    private ArrayList<Integer> casillas_frutas = new ArrayList<Integer>();
    private Configuracion config;
    private int contador_frutas;

    public Game(GridLayout tablero, Configuracion config) {
        tablero.removeAllViews();
        tablero.setColumnCount(config.getColumnCount());
        tablero.setRowCount(config.getRowCount());

        this.config = config;
        this.contador_frutas = config.getNum_frutas();
    }

    public ArrayList<Integer> getCasillas_frutas() {

        int min = 1;
        int max = config.getColumnCount() * config.getRowCount();
        int id_casilla = 0;
        boolean repetido = false;

        for(int x=0; x<config.getNum_frutas(); x++){

            repetido = false;
            Random r = new Random();
            id_casilla = r.nextInt(max - min + 1) + min;

            if(x > 0){
                for (Integer curCasilla : casillas_frutas){
                    if(curCasilla.equals(id_casilla)){
                       repetido = true;
                    }                }
            }

            if(!repetido) casillas_frutas.add(id_casilla); else x--;
        }

        return casillas_frutas;

    }

    public int getContador_frutas() {
        contador_frutas -= 1;
        return contador_frutas;
    }

    public void setContador_frutas(int contador_frutas) {
        this.contador_frutas = contador_frutas;
    }
}
