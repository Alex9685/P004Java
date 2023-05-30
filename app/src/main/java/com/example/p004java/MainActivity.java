package com.example.p004java;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputUser = findViewById(R.id.inputUser);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSalir = findViewById(R.id.btnSalir);

        //Boton para ingresar
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = inputUser.getText().toString().trim();

                if (!nombre.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ReciboNominaActivity.class);
                    intent.putExtra("nombre", nombre);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese un nombre de usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro de querer cerrar sesión?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones a realizar si se selecciona "Sí"
                        finish(); // Finaliza la actividad actual (MainActivity)
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones a realizar si se selecciona "No"
                        dialog.dismiss(); // Cierra el diálogo sin realizar ninguna acción adicional
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}