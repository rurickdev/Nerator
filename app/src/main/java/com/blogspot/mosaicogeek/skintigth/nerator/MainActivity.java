package com.blogspot.mosaicogeek.skintigth.nerator;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

        textViewGenerado.setMovementMethod(new ScrollingMovementMethod());
        textViewGenerado.setTextIsSelectable(true);
    }

    //Metodo encargado de generar una cadena aleatoria
    //Recive Int Cantidad de caracteres, String caracteres especiales que se quieran agregar
    public String GenerarCadenaAleatoria(int numCaracteres, String caracteresEsp){

        String consonantes = "bcdfghjklmnpqrstvwxyz", vocales="aeiou", cadena="", listaCadenas="";
        int numeroRandom, eleccion,
                //Contadores para control de repeticiones
                cont1=0, cont2=0, cont3=0;

        //Crea la lista de cadenas
        for(int h=0;h<=20;h++) {
            //Crea la cadena tomando letras aleatorias de las consonantes, vocales y simbolos
            do{
                eleccion=(int)((Math.random()*3)+1);

                //Si un tipo de letra se repite 2 veces se ignorara hasta que tique un tipo diferente
                switch(eleccion){
                    case 1:
                        if(cont1!=2) {
                            numeroRandom = (int) (Math.random() * consonantes.length());
                            cadena = cadena + consonantes.charAt(numeroRandom);
                            //Cuenta cuantas veces se a repetido
                            cont1++;
                            //Al entrar 1 vez las debas letras quedan disponibles reiniciando sus contadores
                            cont2 = 0;
                            cont3 = 0;
                        }
                        break;
                    case 2:
                        if(cont2!=2) {
                            numeroRandom = (int) (Math.random() * vocales.length());
                            cadena = cadena + vocales.charAt(numeroRandom);
                            cont2++;
                            cont1 = 0;
                            cont3 = 0;
                        }
                        break;
                    case 3:
                        if((caracteresEsp != null)&&(!caracteresEsp.equals(""))) {
                            if(cont3!=2) {
                                numeroRandom = (int) (Math.random() * caracteresEsp.length());
                                cadena = cadena + caracteresEsp.charAt(numeroRandom);
                                cont3++;
                                cont1 = 0;
                                cont2 = 0;
                            }
                        }
                        break;
                    default:
                        break;
                }

                //Convierte a mayuscula la primera letra del nombre
                if(cadena.length()==1) {
                    cadena = cadena.toUpperCase();
                }
            //Se repite hasta que la cadena tenga el tamaño deseado
            }while(cadena.length()<numCaracteres);

            //Concatena las cadenas para la lista
            listaCadenas=listaCadenas+cadena+"\n";
            //Reinicia la cadena para agregar una nueva
            cadena="";
        }

        //Regresa la lista generada
        return listaCadenas;
    }

    public void GenerarNombre(View v){
        String caracteres="";
        int numeroInt=15;

        //Obtiene el numero de caracteres del EditText y los convierte en entero, si es mayor a 15 usa 14 en su lugar
        try{
            numeroInt = Integer.parseInt(textNumeroCaracteres.getText().toString().trim());
            if(numeroInt>15){
                Toast toastError = Toast.makeText(getApplicationContext(), "Numero mayor al limite, maximo 15", Toast.LENGTH_SHORT);
                toastError.show();
                numeroInt=15;
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

    //Metodo encargado de mostrar un dialogo informativo sobre el numero maximo de caracteres
    public void DialogoSoloNumeros(View v){
        AlertDialog.Builder constructorDialogo = new AlertDialog.Builder(this);
        constructorDialogo.setIcon(R.drawable.ic_info)
                .setTitle("Info")
                .setMessage("Solo se permite poner numeros.\n" +
                        "El numero maximo de caracteres es 15.\n" +
                        "En caso de que se deje vacio se usará 15")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog mensaje = constructorDialogo.create();
        mensaje.show();
    }

    //Muestra un dialogo informativo sobre los caracteres que se pueden utilizar
    public void DialogoCualquierCaracter(View v){
        AlertDialog.Builder constructorDialogo = new AlertDialog.Builder(this);
        constructorDialogo.setIcon(R.drawable.ic_info)
                .setTitle("Info")
                .setMessage("Puedes agregar cualquier caracter que desees que aparesca en los nombre.\n" +
                        "Estos pueden o no aparecer en los mismos.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog mensaje = constructorDialogo.create();
        mensaje.show();
    }

}
