package com.egibide.a8fprogmm09.buscafrutas;


import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by jon on 15/02/2017.
 */

public class Game{

    private ArrayList<Integer> casillas_frutas = new ArrayList<Integer>();//Casillas que contienen frutas
    private Configuracion config;//Configuración de la nueva partida
    private int contador_frutas;//Contador de las frutas que te quedan por descubrir

    public Game(GridLayout tablero, Configuracion config) {
        //Limpiamos el tablero
        tablero.removeAllViews();
        //Le asignamos el numero de columnas del tablero
        tablero.setColumnCount(config.getColumnCount());
        //Le asignamos el numero de filas del tablero
        tablero.setRowCount(config.getRowCount());

        this.config = config;
        this.contador_frutas = config.getNum_frutas();
    }

    //Random para calcular el contenido del nuevo Juego

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
    //Restamos las frutas cada vez que encontramos una para llevar la cuenta
    public int getContador_frutas() {
        contador_frutas -= 1;
        return contador_frutas;
    }

    public void setContador_frutas(int contador_frutas) {
        this.contador_frutas = contador_frutas;
    }

    //Calculamos el numero de frutas que hay cerca cuando pulsamos una casilla
    public Integer calculateFrutasCerca (ArrayList<Integer> arr_idFrutas, Integer id_casilla){
        int cont_frutas_cerca = 0;

        int superior_left = id_casilla - config.getColumnCount() - 1;
        int superior_top = id_casilla - config.getColumnCount();
        int superior_right = id_casilla - config.getColumnCount() + 1;
        int medio_left = id_casilla - 1;
        int medio_right = id_casilla + 1;
        int inferior_left = id_casilla + config.getColumnCount() - 1;
        int inferior_bottom = id_casilla + config.getColumnCount();
        int inferior_right = id_casilla + config.getColumnCount() + 1;

        for (Integer curCasilla_evaluate : arr_idFrutas){

            if(curCasilla_evaluate.equals(superior_left)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(superior_top)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(superior_left)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(medio_left)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(medio_right)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(inferior_left)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(inferior_bottom)){
                cont_frutas_cerca += 1;
            }
            if(curCasilla_evaluate.equals(inferior_right)){
                cont_frutas_cerca += 1;
            }
        }

        return cont_frutas_cerca;

    }
}
