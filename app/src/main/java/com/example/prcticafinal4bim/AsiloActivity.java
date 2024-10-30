package com.example.prcticafinal4bim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AsiloActivity extends AppCompatActivity {

    private Spinner spinnerAsilos, spinnerHorarioAsilos;
    private String asiloSeleccionado, horarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asilo);

        spinnerAsilos = findViewById(R.id.spinnerAsilos);
        spinnerHorarioAsilos = findViewById(R.id.spinnerHorarioAsilos);
        Button btnConfirmarAsilo = findViewById(R.id.btnConfirmar);

        // Opciones de asilos y horarios
        String[] opcionesAsilos = {"Hogar San José", "La Casa del Abuelo", "Hogar San Ramón"};
        ArrayAdapter<String> adapterAsilos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesAsilos);
        adapterAsilos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAsilos.setAdapter(adapterAsilos);

        spinnerAsilos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                asiloSeleccionado = opcionesAsilos[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                asiloSeleccionado = "";
            }
        });

        String[] opcionesHorarios = {"Mañana", "Tarde", "Noche"};
        ArrayAdapter<String> adapterHorarios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesHorarios);
        adapterHorarios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorarioAsilos.setAdapter(adapterHorarios);

        spinnerHorarioAsilos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horarioSeleccionado = opcionesHorarios[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horarioSeleccionado = "";
            }
        });

        btnConfirmarAsilo.setOnClickListener(v -> {
            if (!asiloSeleccionado.isEmpty() && !horarioSeleccionado.isEmpty()) {
                String nombre = getIntent().getStringExtra("nombreUsuario");
                String apellido = getIntent().getStringExtra("apellidoUsuario");

                Intent intent = new Intent(AsiloActivity.this, RegistroCompletadoActivity.class);
                intent.putExtra("nombreUsuario", nombre);
                intent.putExtra("apellidoUsuario", apellido);
                intent.putExtra("areaApoyo", "Asilos");
                intent.putExtra("lugar", asiloSeleccionado);
                intent.putExtra("horario", horarioSeleccionado);
                startActivity(intent);
            } else {
                Toast.makeText(AsiloActivity.this, "Por favor, seleccione un asilo y un horario.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
