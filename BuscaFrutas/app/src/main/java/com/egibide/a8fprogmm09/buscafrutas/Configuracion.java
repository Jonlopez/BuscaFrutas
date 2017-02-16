package com.egibide.a8fprogmm09.buscafrutas;

/**
 * Created by jon on 15/02/2017.
 */

public class Configuracion {

    private int columnCount;
    private int rowCount;
    private int num_frutas;

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
