package com.example.prcticafinal4bim;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextApellido;
    private EditText editTextNuevoUsuario;
    private EditText editTextNuevaContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextNuevoUsuario = findViewById(R.id.editTextNuevoUsuario);
        editTextNuevaContrasena = findViewById(R.id.editTextNuevaContrasena);
        Button buttonCrearUsuario = findViewById(R.id.buttonCrearUsuario);

        buttonCrearUsuario.setOnClickListener(view -> {
            String nombre = editTextNombre.getText().toString().trim();
            String apellido = editTextApellido.getText().toString().trim();
            String usuario = editTextNuevoUsuario.getText().toString().trim();
            String contrasena = editTextNuevaContrasena.getText().toString().trim();

            if (!nombre.isEmpty() && !apellido.isEmpty() && !usuario.isEmpty() && !contrasena.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nombre", nombre);
                resultIntent.putExtra("apellido", apellido);
                resultIntent.putExtra("usuario", usuario);
                resultIntent.putExtra("contrasena", contrasena);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(CrearUsuarioActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
