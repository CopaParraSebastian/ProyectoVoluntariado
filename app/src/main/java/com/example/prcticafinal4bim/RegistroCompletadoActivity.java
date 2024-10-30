package com.example.prcticafinal4bim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroCompletadoActivity extends AppCompatActivity {

    private TextView textViewNombreYApellido, textViewAreaSeleccionada, textViewLugarSeleccionado, textViewHorarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_completado);

        textViewNombreYApellido = findViewById(R.id.textViewNombreYApellido);
        textViewAreaSeleccionada = findViewById(R.id.textViewAreaSeleccionada);
        textViewLugarSeleccionado = findViewById(R.id.textViewLugarSeleccionado);
        textViewHorarioSeleccionado = findViewById(R.id.textViewHorarioSeleccionado);
        Button btnVerTareas = findViewById(R.id.btnVerTareas);

        // Obtener los datos enviados desde las activities anteriores
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombreUsuario");
        String apellido = intent.getStringExtra("apellidoUsuario");
        String areaSeleccionada = intent.getStringExtra("areaApoyo");
        String lugarVoluntariado = intent.getStringExtra("lugar");
        String horarioVoluntariado = intent.getStringExtra("horario");

        // Mostrar los datos en los TextView
        textViewNombreYApellido.setText(nombre + " " + apellido);
        textViewAreaSeleccionada.setText(areaSeleccionada);
        textViewLugarSeleccionado.setText(lugarVoluntariado);
        textViewHorarioSeleccionado.setText(horarioVoluntariado);

        // Configurar el botón para ir a la pantalla de tareas
        btnVerTareas.setOnClickListener(v -> {
            Intent tareasIntent = new Intent(RegistroCompletadoActivity.this, TareasActivity.class);
            startActivity(tareasIntent);
        });
    }
}
