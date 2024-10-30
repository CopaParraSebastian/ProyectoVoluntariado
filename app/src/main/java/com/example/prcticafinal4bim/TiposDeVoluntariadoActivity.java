package com.example.prcticafinal4bim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TiposDeVoluntariadoActivity extends AppCompatActivity {

    private Button buttonHospitales;
    private Button buttonOrfanatos;
    private Button buttonAsilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_de_voluntariado);

        buttonHospitales = findViewById(R.id.buttonHospitales);
        buttonOrfanatos = findViewById(R.id.buttonOrfanatos);
        buttonAsilo = findViewById(R.id.buttonAsilo);

        // Obtenemos el nombre y apellido del intent para pasar a los siguientes activities
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
        String apellidoUsuario = getIntent().getStringExtra("apellidoUsuario");

        buttonHospitales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar HospitalesActivity y pasar datos
                Intent intent = new Intent(TiposDeVoluntariadoActivity.this, HospitalesActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                intent.putExtra("apellidoUsuario", apellidoUsuario);
                intent.putExtra("tipoVoluntariado", "Hospitales");
                startActivity(intent);
            }
        });

        buttonOrfanatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar OrfanatosActivity y pasar datos
                Intent intent = new Intent(TiposDeVoluntariadoActivity.this, OrfanatosActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                intent.putExtra("apellidoUsuario", apellidoUsuario);
                intent.putExtra("tipoVoluntariado", "Orfanatos");
                startActivity(intent);
            }
        });

        buttonAsilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar AsiloActivity y pasar datos
                Intent intent = new Intent(TiposDeVoluntariadoActivity.this, AsiloActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                intent.putExtra("apellidoUsuario", apellidoUsuario);
                intent.putExtra("tipoVoluntariado", "Asilo de Ancianos");
                startActivity(intent);
            }
        });
    }
}
