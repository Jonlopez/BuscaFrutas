package com.egibide.a8fprogmm09.buscafrutas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by 8fprogmm09 on 9/2/17.
 */

public class DialogoPersonajes extends  DialogFragment {

    RespuestaPersonaje respuesta;
    Spinner spFrutas;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Usamos la clase Builder para construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Escribimos el título
        builder.setTitle("Pregunta muy importante:");
        //Escribimos la pregunta
        builder.setMessage("¿Eres una chica?");
        //añadimos el botón de Si y su acción asociada
//        this.spFrutas = (Spinner) findViewById(R.id.sp_frutas);
        // Crear el AlertDialog y devolverlo
        return builder.create();
    }

//    private void loadSpinnerFrutas() {
//
//        // Create an ArrayAdapter using the string array and a default spinner
//        // layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this, R.array.frutas, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        this.spFrutas.setAdapter(adapter);
//
//        // This activity implements the AdapterView.OnItemSelectedListener
//        this.spFrutas.setOnItemSelectedListener(this);
//
//    }

    public interface RespuestaPersonaje{
        public void onRespuesta(String s);
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        respuesta=(RespuestaPersonaje) activity;
//    }

}
