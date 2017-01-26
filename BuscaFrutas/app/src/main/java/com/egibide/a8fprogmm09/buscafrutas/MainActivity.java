package com.egibide.a8fprogmm09.buscafrutas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bpersonaje:
                //metodoAdd()
                System.out.println("Se presionó cambiar personaje");
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
