package com.example.prcticafinal4bim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private TextView textViewNombreCompleto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        textViewNombreCompleto = findViewById(R.id.textViewNombreCompleto);
        Button buttonIniciar = findViewById(R.id.buttonIniciar);

        // Recibir el nombre y apellido del intent
        String nombre = getIntent().getStringExtra("nombreUsuario");
        String apellido = getIntent().getStringExtra("apellidoUsuario");

        // Mostrar el nombre completo
        textViewNombreCompleto.setText(nombre + " " + apellido);

        // Acción para el botón "INICIAR"
        buttonIniciar.setOnClickListener(v -> {
            Intent intent = new Intent(PrincipalActivity.this, TiposDeVoluntariadoActivity.class);
            intent.putExtra("nombreUsuario", nombre);
            intent.putExtra("apellidoUsuario", apellido);
            startActivity(intent);
        });
    }
}
