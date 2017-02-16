package com.egibide.a8fprogmm09.buscafrutas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Configuracion config;
    int width, height;
    GridLayout tablero;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Incializamos la configuracion por defecto en modo facil
        config = new Configuracion(8, 8);
        //Calculamos las dimensiones de la pantalla para tenerlas en cuenta  a la hora de dibujar el tablero
        calcularDimensiones();
        //Guardamos en variable Global el Grid para pintar en el
        tablero = (GridLayout) findViewById(R.id.gridTablero);
        //Generamos el Juego por defecto en modo facil
        nuevoJuego();
    }

    /*MENU DE OPCIONES */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Eventos al ulsar las diferentes opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bpersonaje:
                changePersonaje();
                System.out.println("Se presionó cambiar personaje");
                return true;
            case R.id.instrucciones:
                getIntrucciones();
                System.out.println("Se presionó instrucciones");
                return true;
            case R.id.nuevo_juego:
                nuevoJuego();
                System.out.println("Se presionó nuevo juego");
                return true;
            case R.id.config:
                changeConfiguracion();
                System.out.println("Se presionó configuracion del juego");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Spinner para cambiar el personaje
    private void changePersonaje(){
        AlertDialog.Builder dgl_intrucciones = new AlertDialog.Builder(this);
        dgl_intrucciones.setTitle(getText(R.string.str_instrucciones));

        Spinner spinner_frutas = new Spinner(MainActivity.this);;

        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.frutas, android.R.layout.simple_spinner_item);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_frutas.setAdapter(spinnerArrayAdapter);

        spinner_frutas.setOnItemSelectedListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setView(spinner_frutas);

        builder.create().show();
    }

    //Eventos al clicar los diferentes personajes
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (pos){
            case 0:
                System.out.println("Platano");
            break;
            case 1:
                System.out.println("Naranja");
            break;
            case 2:
                System.out.println("Fresa");
                break;
            case 3:
                System.out.println("Piña");
                break;
            default:
                break;
        }

    }

    //Se ejecuta cuando el spinner se deja vacio
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Evento que Genera una nueva partida
    private void nuevoJuego(){

        //Creamos el juego y le pasamos la configuración
        game = new Game(tablero, this.config);
        //El deficit es lo que he calculado para que las casillas del tablero se centren correctamente
        //Teniendo en cuenta el numero de columnas que hay
        int deficit_w = 0;
        int deficit_h = 0;
        switch (config.getColumnCount()){
            case 8:
                deficit_w = 10;deficit_h = 10;
                break;
            case 12:
                deficit_h = 10;
                break;
            case 16:
                deficit_w = 5;deficit_h = 5;
                break;
            default:
                deficit_w = 10;deficit_h = 10;
                break;
        }
        //Es un array que acumular los id de las casillas que contienen las frutas  = (MINAS)
        final ArrayList<Integer> arr_idFrutas = game.getCasillas_frutas();
        //Generamos el tablero y le asignamos los eventos click y LognClick
        int id_casilla = 1;
        for(int x = 0; x < config.getColumnCount(); x++){
            for(int y = 0; y < config.getRowCount(); y++) {

                final Button btn = new Button(this);
                btn.setId(id_casilla);
                for (Integer curCasilla : arr_idFrutas){
                    if(curCasilla.equals(id_casilla)){
                        btn.setText("");
                    }
                }
                btn.setLayoutParams(new LinearLayout.LayoutParams( (width / config.getColumnCount()) - deficit_w, (height / config.getRowCount()) - deficit_h));
                btn.setBackgroundResource(R.drawable.custom_button);
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("boton pulsado -> "  +  v.getId());
                        boolean perder = false;
                        for (Integer curCasilla : arr_idFrutas){

                            if(curCasilla.equals( v.getId())){
                                v.setBackgroundColor(888888);
                                btn.setText("D=");
                                alertFinalizar("¡Has Perdido!");

                                perder = true;
                            }

                        }

                        if(!perder){
                            btn.setText(Integer.toString(game.calculateFrutasCerca(arr_idFrutas, v.getId())));
                        }
                    }
                });

                btn.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        System.out.println("pulsado largooo");
                        boolean perder = false;
                        for (Integer curCasilla : arr_idFrutas){
                            if(curCasilla.equals( v.getId())){
                                v.setBackgroundColor(888888);
                                btn.setText("=D");
                                if(game.getContador_frutas() == 0)
                                    alertFinalizar("¡Has ganado!");

                                perder = false;

                            }else perder = true;

                        }

                        if(perder){
                            btn.setText(Integer.toString(game.calculateFrutasCerca(arr_idFrutas, v.getId())));
                            alertFinalizar("¡Has perdido!");
                        }

                        return true;
                    }
                });

                tablero.addView(btn);
                id_casilla += 1;
            }
        }

    }
    //Mensaje de Victoria o Derrota del juego
    private void alertFinalizar(String mensaje){
        AlertDialog.Builder dgl_intrucciones = new AlertDialog.Builder(this);

        dgl_intrucciones.setTitle("El Juego a terminado");
        dgl_intrucciones.setMessage(mensaje);
        dgl_intrucciones.setNeutralButton(getText(R.string.str_ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.dismiss();
                //nuevoJuego();
            }
        });

        dgl_intrucciones.show();
    }

    //Calculo de las dimensiones de la pantalla
    private void calcularDimensiones(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int actionBarHeight = 0;
        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize }
        );

        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        int navigationBarHeight = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        this.width = size.x;
        this.height = size.y - (actionBarHeight + navigationBarHeight) + 75;
    }

    //Mensaje con las instrucciones
    private void getIntrucciones(){
        AlertDialog.Builder dgl_intrucciones = new AlertDialog.Builder(this);

        dgl_intrucciones.setTitle(getText(R.string.str_instrucciones));
        dgl_intrucciones.setMessage(getText(R.string.str_dlg_instrucciones));
        dgl_intrucciones.setNeutralButton(getText(R.string.str_ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.dismiss();
            }
        });

        dgl_intrucciones.show();
    }

    //Radio Buttons para cambiar la configuracion
    private void changeConfiguracion(){
        AlertDialog alertDialog1;
        CharSequence[] values = {"Facil","Medio","Dificil"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Elige el nivel de dificultad");
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:
                        config = new Configuracion(8, 8);
                        System.out.println("Facil");
                        break;
                    case 1:
                        config = new Configuracion(12, 12);
                        System.out.println("Amateur");
                        break;
                    case 2:
                        config = new Configuracion(16, 16);
                        System.out.println("Avanzado");
                        break;
                    default:
                        config = new Configuracion(8, 8);
                        System.out.println("por defecto");
                        break;
                }

                nuevoJuego();
            }
        });

        alertDialog1 = builder.create();
        alertDialog1.show();
    }

    /*FIN DE MENU*/

}
