package com.egibide.a8fprogmm09.buscafrutas;

/**
 * Created by jon on 15/02/2017.
 */

public class Configuracion {

    private int columnCount;//Número de columnas del tablero
    private int rowCount;//Número de filas del tablero
    private int num_frutas;//Número de frutas que contiene el tablero

    public Configuracion(int columnCount, int rowCount) {
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        calculateFrutas();
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getNum_frutas() {
        return num_frutas;
    }

    public void setNum_frutas(int num_frutas) {
        this.num_frutas = num_frutas;
    }

    //El número de frutas cambia segun la cantidad de casillas
    private void calculateFrutas (){
        switch (this.getColumnCount()){
            case 8:
                this.setNum_frutas(10);
                break;
            case 12:
                this.setNum_frutas(30);
                break;
            case 16:
                this.setNum_frutas(60);
                break;
            default:
                break;
        }
    }

}
