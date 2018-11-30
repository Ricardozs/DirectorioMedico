package com.example.ricar.directoriomedico;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroMedicos extends AppCompatActivity implements View.OnClickListener{
    EditText medicalName, medicalUser, medicalBirthDay, medicalMail, medicalPassword, medicalPhone, medicalCel, medicalCedula, medicalUserType;
    Spinner especialidad;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medicos);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.specialties,
                        android.R.layout.simple_spinner_dropdown_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        medicalName = findViewById(R.id.tv_medicalName);
        medicalUser = findViewById(R.id.tv_user);
        medicalBirthDay = findViewById(R.id.tv_birthDay);
        medicalMail = findViewById(R.id.tv_email);
        medicalPassword = findViewById(R.id.tv_password);
        especialidad = findViewById(R.id.static_spinner);
        medicalPhone = findViewById(R.id.tv_phone);
        medicalCel = findViewById(R.id.tv_cel);
        medicalCedula = findViewById(R.id.tv_cedula);
        medicalUserType = findViewById(R.id.tv_userType);
        btnRegister = findViewById(R.id.btn_medicalRegistry);
    }
    @Override
    public void onClick(View v) {
        final String nombre = medicalName.getText().toString();
        final String nombreUsuario = medicalUser.getText().toString();
        final String fechaNacimiento = medicalBirthDay.getText().toString();
        final String correo = medicalMail.getText().toString();
        final String password = medicalPassword.getText().toString();
        final int tipoUsuario_id = Integer.parseInt(medicalUserType.getText().toString());
        final int especialidades_id = especialidad.getSelectedItemPosition();
        final int telefono = Integer.parseInt(medicalPhone.getText().toString());
        final int celular = Integer.parseInt(medicalCel.getText().toString());
        final int cedula = Integer.parseInt(medicalCedula.getText().toString());


        Response.Listener<String> respoListener = new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success =  jsonResponse.getBoolean("success");
                    if(success){
                        Intent intent = new Intent(RegistroMedicos.this,LoginActivity.class);
                        RegistroMedicos.this.startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroMedicos.this);
                        builder.setMessage("Error de Registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        MedicalRegisterRequest medicalRegisterRequest = new MedicalRegisterRequest(nombre, nombreUsuario, fechaNacimiento,
                correo, password, tipoUsuario_id, especialidades_id, telefono, celular, cedula, respoListener);
        RequestQueue queue = Volley.newRequestQueue(RegistroMedicos.this);
        queue.add(medicalRegisterRequest);
    }
}
