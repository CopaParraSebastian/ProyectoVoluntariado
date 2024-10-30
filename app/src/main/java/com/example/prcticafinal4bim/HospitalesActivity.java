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

public class HospitalesActivity extends AppCompatActivity {

    private Spinner spinnerHospitales, spinnerHorarioHospitales;
    private String hospitalSeleccionado, horarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitales);

        spinnerHospitales = findViewById(R.id.spinnerHospitales);
        spinnerHorarioHospitales = findViewById(R.id.spinnerHorarioHospitales);
        Button btnConfirmarHospital = findViewById(R.id.btnConfirmar);

        // Opciones de hospitales y horarios
        String[] opcionesHospitales = {"Viedman", "Hospital Del Norte", "Caja Petrolera de Salud"};
        ArrayAdapter<String> adapterHospitales = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesHospitales);
        adapterHospitales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHospitales.setAdapter(adapterHospitales);

        spinnerHospitales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hospitalSeleccionado = opcionesHospitales[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hospitalSeleccionado = "";
            }
        });

        String[] opcionesHorarios = {"Mañana", "Tarde", "Noche"};
        ArrayAdapter<String> adapterHorarios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesHorarios);
        adapterHorarios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorarioHospitales.setAdapter(adapterHorarios);

        spinnerHorarioHospitales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horarioSeleccionado = opcionesHorarios[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horarioSeleccionado = "";
            }
        });

        btnConfirmarHospital.setOnClickListener(v -> {
            if (!hospitalSeleccionado.isEmpty() && !horarioSeleccionado.isEmpty()) {
                String nombre = getIntent().getStringExtra("nombreUsuario");
                String apellido = getIntent().getStringExtra("apellidoUsuario");

                Intent intent = new Intent(HospitalesActivity.this, RegistroCompletadoActivity.class);
                intent.putExtra("nombreUsuario", nombre);
                intent.putExtra("apellidoUsuario", apellido);
                intent.putExtra("areaApoyo", "Hospitales");
                intent.putExtra("lugar", hospitalSeleccionado);
                intent.putExtra("horario", horarioSeleccionado);
                startActivity(intent);
            } else {
                Toast.makeText(HospitalesActivity.this, "Por favor, seleccione un hospital y un horario.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
