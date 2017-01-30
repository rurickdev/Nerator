package com.blogspot.mosaicogeek.skintigth.nerator;

import android.support.design.widget.TextInputEditText;
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

        String alfabeto = "abcdefghijklmnñopqrstuvwxyz", cadena="";
        int numeroRandom;

        //Agrega al alfabeto los caracteres especiales
        alfabeto = alfabeto+caracteresEsp;

        //Crea la cadena tomando letras aleatorias del alfabeto
        for(int i=0;i<=numCaracteres;i++){
            numeroRandom = (int)(Math.random()*alfabeto.length());
            cadena=cadena+alfabeto.charAt(numeroRandom);
        }

        //Regresa la cadena generada
        return cadena;
    }

    public void Botonaso(View v){
        String numero="", caracteres="";
        int numeroInt=10;

        //Obtiene el numero de caracteres del EditText y los convierte en entero
        try{
            numeroInt = Integer.parseInt(numero = textNumeroCaracteres.getText().toString().trim());
            Toast toastError = Toast.makeText(getApplicationContext(), "Numero Cadena: "+numero, Toast.LENGTH_SHORT);
            toastError.show();
        }catch(Exception e){
            Toast toastError = Toast.makeText(getApplicationContext(), "Error Get Text Numero", Toast.LENGTH_SHORT);
            toastError.show();
        }

        //Obtiene los caracteres especiales del EditText
        try{
            caracteres = textCaracteresEspeciales.getText().toString().trim();
            Toast toastError = Toast.makeText(getApplicationContext(), "Cadena Caracteres: "+caracteres, Toast.LENGTH_SHORT);
            toastError.show();
        }catch(Exception e){
            Toast toastError = Toast.makeText(getApplicationContext(), "Error Get Text Caracteres", Toast.LENGTH_SHORT);
            toastError.show();
        }

        //Muestra la cadena generada en el TextView usando el metodo "GenerarCadenaAleatoria"
        textViewGenerado.setText(GenerarCadenaAleatoria(numeroInt, caracteres));

    }

}
