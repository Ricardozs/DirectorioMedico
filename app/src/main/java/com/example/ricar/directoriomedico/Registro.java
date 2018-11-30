package com.example.ricar.directoriomedico;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etUser, etBirthDay, etEmail, etPassword, etUserType;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etName = findViewById(R.id.tv_user);
        etUser = findViewById(R.id.tv_userName);
        etBirthDay = findViewById(R.id.tv_birthDay);
        etEmail = findViewById(R.id.tv_email);
        etPassword = findViewById(R.id.tv_password);
        etUserType = findViewById(R.id.tv_userType);
        btnRegister = findViewById(R.id.btn_userRegistry);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String nombre = etName.getText().toString();
        final String nombreUsuario = etUser.getText().toString();
        final String fechaNacimiento = etBirthDay.getText().toString();
        final String correo = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        final int tipoUsuario_id = Integer.parseInt(etUserType.getText().toString());


        Response.Listener<String> respoListener = new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success =  jsonResponse.getBoolean("success");
                    if(success){
                        Intent intent = new Intent(Registro.this,LoginActivity.class);
                        Registro.this.startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
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

        RegisterRequest registerRequest = new RegisterRequest(nombre, nombreUsuario, fechaNacimiento, correo, password, tipoUsuario_id, respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);
    }
}
