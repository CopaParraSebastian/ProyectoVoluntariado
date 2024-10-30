package com.example.prcticafinal4bim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TareasActivity extends AppCompatActivity {

    private CheckBox checkBoxTarea1, checkBoxTarea2, checkBoxTarea3;
    private Button buttonGuardarTareas;
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        checkBoxTarea1 = findViewById(R.id.checkBoxTarea1);
        checkBoxTarea2 = findViewById(R.id.checkBoxTarea2);
        checkBoxTarea3 = findViewById(R.id.checkBoxTarea3);
        buttonGuardarTareas = findViewById(R.id.buttonGuardarTareas);

        // Obtener idUsuario desde el intent
        idUsuario = getIntent().getIntExtra("idUsuario", -1);

        // Listener para el botón "Guardar"
        buttonGuardarTareas.setOnClickListener(v -> actualizarTareas());
    }

    private void actualizarTareas() {
        String url = "http://192.168.1.140:80/conexion/actualizar_tareas.php";

        int tarea1 = checkBoxTarea1.isChecked() ? 1 : 0;
        int tarea2 = checkBoxTarea2.isChecked() ? 1 : 0;
        int tarea3 = checkBoxTarea3.isChecked() ? 1 : 0;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> Toast.makeText(TareasActivity.this, "Tareas actualizadas", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(TareasActivity.this, "Error al actualizar tareas", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUsuario", String.valueOf(idUsuario));
                params.put("tarea1", String.valueOf(tarea1));
                params.put("tarea2", String.valueOf(tarea2));
                params.put("tarea3", String.valueOf(tarea3));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
