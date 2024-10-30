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

public class OrfanatosActivity extends AppCompatActivity {

    private Spinner spinnerOrfanatos, spinnerHorarioOrfanatos;
    private String orfanatoSeleccionado, horarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orfanatos);

        spinnerOrfanatos = findViewById(R.id.spinnerOrfanatos);
        spinnerHorarioOrfanatos = findViewById(R.id.spinnerHorarioOrfanatos);
        Button btnConfirmarOrfanatos = findViewById(R.id.btnConfirmar);

        String[] opcionesOrfanatos = {"Aldeas Infantiles SOS", "Cristo Rey", "Hogar Solomon Klein"};
        ArrayAdapter<String> adapterOrfanatos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesOrfanatos);
        adapterOrfanatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrfanatos.setAdapter(adapterOrfanatos);

        spinnerOrfanatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orfanatoSeleccionado = opcionesOrfanatos[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                orfanatoSeleccionado = "";
            }
        });

        String[] opcionesHorarios = {"Mañana", "Tarde", "Noche"};
        ArrayAdapter<String> adapterHorarios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesHorarios);
        adapterHorarios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorarioOrfanatos.setAdapter(adapterHorarios);

        spinnerHorarioOrfanatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horarioSeleccionado = opcionesHorarios[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horarioSeleccionado = "";
            }
        });

        btnConfirmarOrfanatos.setOnClickListener(v -> {
            if (!orfanatoSeleccionado.isEmpty() && !horarioSeleccionado.isEmpty()) {
                String nombre = getIntent().getStringExtra("nombreUsuario");
                String apellido = getIntent().getStringExtra("apellidoUsuario");

                Intent intent = new Intent(OrfanatosActivity.this, RegistroCompletadoActivity.class);
                intent.putExtra("nombreUsuario", nombre);
                intent.putExtra("apellidoUsuario", apellido);
                intent.putExtra("areaApoyo", "Orfanatos");
                intent.putExtra("lugar", orfanatoSeleccionado);
                intent.putExtra("horario", horarioSeleccionado);
                startActivity(intent);
            } else {
                Toast.makeText(OrfanatosActivity.this, "Por favor, seleccione un orfanato y un horario.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
