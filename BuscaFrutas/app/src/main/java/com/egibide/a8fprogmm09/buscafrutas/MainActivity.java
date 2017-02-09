package com.egibide.a8fprogmm09.buscafrutas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener,
        DialogoPersonajes.RespuestaPersonaje{

    Spinner spFrutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.spFrutas = (Spinner) findViewById(R.id.sp_frutas);

        loadSpinnerFrutas();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        System.out.println("Se presionó pos -> " + pos + " id -> " + id);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Callback method to be invoked when the selection disappears from this
        // view. The selection can disappear for instance when touch is
        // activated or when the adapter becomes empty.
    }

    /**
     * Populate the Spinner.
     */
    private void loadSpinnerFrutas() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.frutas, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spFrutas.setAdapter(adapter);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.spFrutas.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onRespuesta(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG ).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bpersonaje:
                //metodoAdd()
                System.out.println("Se presionó cambiar personaje");
                DialogoPersonajes dp = new DialogoPersonajes();
                dp.show(getFragmentManager(),"Mi diálogo");
                return true;
            case R.id.instrucciones:
                //metodoSearch()
                System.out.println("Se presionó instrucciones");
                return true;
            case R.id.nuevo_juego:
                //metodoEdit()
                System.out.println("Se presionó nuevo juego");
                return true;
            case R.id.config:
                //metodoDelete()
                System.out.println("Se presionó configuracion del juego");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
