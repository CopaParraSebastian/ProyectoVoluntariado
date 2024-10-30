package com.example.prcticafinal4bim;

import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
                new CrearUsuarioTask().execute(nombre, apellido, usuario, contrasena);
            } else {
                Toast.makeText(CrearUsuarioActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class CrearUsuarioTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String nombre = params[0];
            String apellido = params[1];
            String usuario = params[2];
            String contrasena = params[3];

            try {
                URL url = new URL("http://192.168.1.140:80/conexion/remoto_crearusuario.php"); // Cambia "tu_servidor" por la URL de tu servidor
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String postData = "nombre=" + URLEncoder.encode(nombre, "UTF-8")
                        + "&apellido=" + URLEncoder.encode(apellido, "UTF-8")
                        + "&usuario=" + URLEncoder.encode(usuario, "UTF-8")
                        + "&contrasena=" + URLEncoder.encode(contrasena, "UTF-8");

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(postData.getBytes());
                    os.flush();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("Registro exitoso")) {
                Toast.makeText(CrearUsuarioActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                // Redirigir a MainActivity para iniciar sesión
                Intent intent = new Intent(CrearUsuarioActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(CrearUsuarioActivity.this, "Error al crear usuario: " + result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
