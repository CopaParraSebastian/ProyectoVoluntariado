package com.example.prcticafinal4bim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextContrasena;
    Button buttonIngresar, buttonCrearUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonIngresar = findViewById(R.id.buttonIngresar);
        buttonCrearUsuario = findViewById(R.id.buttonCrearUsuario);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin("http://192.168.1.140:80/conexion/remoto_validalogin.php");
            }
        });

        buttonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), CrearUsuarioActivity.class);
                startActivity(it);
            }
        });
    }

    private void validarLogin(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ServerResponse", response); // Agregar log para ver la respuesta

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        int idUsuario = jsonResponse.getInt("idUsuario");
                        String nombreUsuario = jsonResponse.getString("nombreUsuario");
                        String apellidoUsuario = jsonResponse.getString("apellidoUsuario");

                        Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                        intent.putExtra("idUsuario", idUsuario);
                        intent.putExtra("nombreUsuario", nombreUsuario);
                        intent.putExtra("apellidoUsuario", apellidoUsuario);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    String statusCode = String.valueOf(error.networkResponse.statusCode);
                    Toast.makeText(MainActivity.this, "Error: " + statusCode, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error de conexión: " + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", editTextUsuario.getText().toString());
                parametros.put("contrasena", editTextContrasena.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
