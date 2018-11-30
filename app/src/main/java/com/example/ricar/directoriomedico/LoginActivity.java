package com.example.ricar.directoriomedico;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity{
    EditText tv_user, tv_password;
    TextView tv_userRegistrar;
    TextView tv_medicalRegistry;
    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_user = findViewById(R.id.tv_user);
        tv_password = findViewById(R.id.tv_password);
        tv_userRegistrar = findViewById(R.id.tv_userRegistry);
        btn_start = findViewById(R.id.btn_start);

        tv_userRegistrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(LoginActivity.this, Registro.class);
                LoginActivity.this.startActivity(intentReg);
            }
        });

        tv_medicalRegistry = findViewById(R.id.tv_medicalRegistry);
        tv_medicalRegistry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegMed = new Intent(LoginActivity.this, RegistroMedicos.class);
                LoginActivity.this.startActivity(intentRegMed);
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombreUsuario = tv_user.getText().toString();
                final String password = tv_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse =  new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String nombre = jsonResponse.getString("nombre");
                                String password = jsonResponse.getString("password");

                                Intent intent = new Intent(LoginActivity.this, Index.class);

                                LoginActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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

                LoginRequest loginRequest = new LoginRequest(nombreUsuario, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}

