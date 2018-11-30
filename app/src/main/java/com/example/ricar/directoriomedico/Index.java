package com.example.ricar.directoriomedico;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Index extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvNombre, tvEspecialidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.specialties,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(this);

        tvNombre = findViewById(R.id.nombre);
        tvEspecialidad = findViewById(R.id.especialidad);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String especialidad = intent.getStringExtra("especialidad_id.nombre");

        tvNombre.setText(nombre);
        tvEspecialidad.setText(especialidad);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final int especialidades_id = Integer.parseInt(parent.getItemAtPosition(position).toString());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse =  new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        String nombre = jsonResponse.getString("nombre");
                        String especialidad_id = jsonResponse.getString("especialidad_id.nombre");

                        Intent intent = new Intent(Index.this, Index.class);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("especialidad_id.nombre",especialidad_id);
                        Index.this.startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Index.this);
                        builder.setMessage("Error de Login")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        IndexRequest indexRequest = new IndexRequest(especialidades_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Index.this);
        queue.add(indexRequest);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}