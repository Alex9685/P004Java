package com.example.p004java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.RadioButton;
import java.util.Random;



public class ReciboNominaActivity extends AppCompatActivity {
    private ReciboNomina recibo;
    private TextView txtRecibo;
    private TextView txtNombre;

    private TextView lblMostrarNombreUser;

    private EditText txtHorasNormal;

    private EditText txtHorasExtras;
    private RadioButton rdbPuesto1;
    private RadioButton rdbPuesto2;
    private RadioButton rdbPuesto3;
    private TextView lblImpuestoPor;
    private TextView lblSubtotal;
    private TextView lblImpuesto;
    private TextView lblTotal;
    private Button btnCalcular;
    private Button btnLimpiar;
    private Button btnSalir;

    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibonomina);

        // Inicializar los componentes de la interfaz

        lblMostrarNombreUser = findViewById(R.id.lblMostrarNombreUser);
        txtHorasNormal = findViewById(R.id.txtHorasNormal);
        txtRecibo = findViewById(R.id.txtRecibo);
        txtRecibo.setEnabled(false); // Deshabilitar la edición del campo de texto del número de recibo
        txtNombre = findViewById(R.id.txtNombre);
        txtHorasExtras = findViewById(R.id.txtHorasExtras);
        rdbPuesto1 = findViewById(R.id.rdbPuesto1);
        rdbPuesto2 = findViewById(R.id.rdbPuesto2);
        rdbPuesto3 = findViewById(R.id.rdbPuesto3);
        lblSubtotal = findViewById(R.id.lblSubtotal);
        lblImpuesto = findViewById(R.id.lblImpuesto);
        lblTotal = findViewById(R.id.lblTotal);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnSalir = findViewById(R.id.btnSalir);

        String nombre = getIntent().getStringExtra("nombre");

        lblMostrarNombreUser.setText(nombre);

        random = new Random();

        // Establecer el número de recibo automáticamente
        int numReciboInt = generarNumeroReciboAleatorio(5, 10);
        txtRecibo.setText(String.valueOf(numReciboInt));

        // Asignar acciones a los botones
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularReciboNomina();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setText("");
                // Establecer el número de recibo automáticamente
                int numReciboInt = generarNumeroReciboAleatorio(5, 10);
                txtRecibo.setText(String.valueOf(numReciboInt));
                txtHorasNormal.setText("");
                txtHorasExtras.setText("");
                rdbPuesto1.setChecked(false);
                rdbPuesto2.setChecked(false);
                rdbPuesto3.setChecked(false);
                lblSubtotal.setText("");
                lblImpuesto.setText("");
                lblTotal.setText("");
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReciboNominaActivity.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro de querer cerrar sesión?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones a realizar si se selecciona "Sí"
                        Intent intent = new Intent(ReciboNominaActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Finaliza la actividad actual (CalculadorActivity)
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

    private void calcularReciboNomina() {
        // Obtener los valores de los componentes
        String numRecibo = txtRecibo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String horasNormal = txtHorasNormal.getText().toString();
        String horasExtras = txtHorasExtras.getText().toString();
        int puesto = obtenerPuestoSeleccionado();

        // Validar que todos los campos estén llenos y el puesto esté seleccionado
        if (TextUtils.isEmpty(numRecibo) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(horasNormal)
                || TextUtils.isEmpty(horasExtras) || puesto == 0) {
            Toast.makeText(ReciboNominaActivity.this, "Llene todos los campos y seleccione un puesto.", Toast.LENGTH_SHORT).show();
            return; // Salir del método si no se cumplen las condiciones
        }

        // Convertir los valores a los tipos de datos correspondientes
        int numReciboInt = Integer.parseInt(numRecibo);
        int horasTrabNormal = Integer.parseInt(horasNormal);
        int horasTrabExtras = Integer.parseInt(horasExtras);

        // Crear una instancia de ReciboNomina con los valores obtenidos
        recibo = new ReciboNomina(numReciboInt, nombre, horasTrabNormal, horasTrabExtras, puesto);

        // Calcular los valores y mostrarlos en los TextView correspondientes
        double subtotal = recibo.calcularSubtotal();
        double impuesto = recibo.calcularImpuesto();
        double total = recibo.calcularTotal();

        lblSubtotal.setText(String.valueOf(subtotal));
        lblImpuesto.setText(String.valueOf(impuesto));
        lblTotal.setText(String.valueOf(total));
    }




    private int obtenerPuestoSeleccionado() {
        if (rdbPuesto1.isChecked()) {
            return 1;
        } else if (rdbPuesto2.isChecked()) {
            return 2;
        } else if (rdbPuesto3.isChecked()) {
            return 3;
        } else {
            return 0; // Valor por defecto en caso de que no se seleccione ningún puesto
        }
    }

    private int generarNumeroReciboAleatorio(int minDigits, int maxDigits) {
        int min = (int) Math.pow(10, minDigits - 1);
        int max = (int) Math.pow(10, maxDigits) - 1;
        return random.nextInt(max - min + 1) + min;
    }
}
