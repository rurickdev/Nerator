package com.blogspot.mosaicogeek.skintigth.nerator;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonGenerar;
    private TextInputEditText textNumeroCaracteres, textCaracteresEspeciales;
    TextView textViewGenerado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        botonGenerar = (Button) findViewById(R.id.Boton_Generar);
        textNumeroCaracteres = (TextInputEditText) findViewById(R.id.Text_Numero_Caracteres);
        textCaracteresEspeciales = (TextInputEditText) findViewById(R.id.Text_Caracteres_Escpeciales);
        textViewGenerado = (TextView) findViewById(R.id.TextView_Texto_Generado);

    }

    //Metodo encargado de generar una cadena aleatoria
    //Recive Int Cantidad de caracteres, String caracteres especiales que se quieran agregar
    public String GenerarCadenaAleatoria(int numCaracteres, String caracteresEsp){

        String alfabeto = "abcdefghijklmnñopqrstuvwxyz", cadena="", listaCadenas="";
        int numeroRandom;

        //Agrega al alfabeto los caracteres especiales
        alfabeto = alfabeto+caracteresEsp;

        //Crea la lista de cadenas
        for(int h=0;h<=20;h++) {

            //Crea la cadena tomando letras aleatorias del alfabeto
            for(int i = 0; i <= numCaracteres; i++) {
                numeroRandom = (int) (Math.random() * alfabeto.length());
                cadena = cadena + alfabeto.charAt(numeroRandom);
                //Convierte a mayuscula la primera letra del nombre
                if(i == 0) {
                    cadena = cadena.toUpperCase();
                }
            }

            //Concatena las cadenas para la lista
            listaCadenas=listaCadenas+cadena+"\n";
            //Reinicia la cadena para agregar una nueva
            cadena="";
        }

        //Regresa la lista generada
        return listaCadenas;
    }

    public void Botonaso(View v){
        String numero="", caracteres="";
        int numeroInt=14;

        //Obtiene el numero de caracteres del EditText y los convierte en entero
        try{
            numeroInt = Integer.parseInt(numero = textNumeroCaracteres.getText().toString().trim());
            if(numeroInt>15){
                numeroInt=14;
            }
        }catch(Exception e){
            Toast toastError = Toast.makeText(getApplicationContext(), "Error Get Text Numero", Toast.LENGTH_SHORT);
            toastError.show();
        }

        //Obtiene los caracteres especiales del EditText
        try{
            caracteres = textCaracteresEspeciales.getText().toString().trim();
        }catch(Exception e){
            Toast toastError = Toast.makeText(getApplicationContext(), "Error Get Text Caracteres", Toast.LENGTH_SHORT);
            toastError.show();
        }

        //Muestra la cadena generada en el TextView usando el metodo "GenerarCadenaAleatoria"
        textViewGenerado.setText(GenerarCadenaAleatoria(numeroInt, caracteres));
    }

    public void DialogoSoloNumeros(View v){
        AlertDialog.Builder constructorDialogo = new AlertDialog.Builder(this);
        constructorDialogo.setIcon(R.drawable.ic_info)
                .setTitle("Info")
                .setMessage("Solo se permite poner numeros.\nEl numero maximo de caracteres es 15.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog mensaje = constructorDialogo.create();
        mensaje.show();
    }

    public void DialogoCualquierCaracter(View v){
        AlertDialog.Builder constructorDialogo = new AlertDialog.Builder(this);
        constructorDialogo.setIcon(R.drawable.ic_info)
                .setTitle("Info")
                .setMessage("Puedes agregar cualquier caracter que desees que aparesca en los nombre.\nEstos pueden o no aparecer en los mismos.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog mensaje = constructorDialogo.create();
        mensaje.show();
    }

}
