package com.example.ricar.directoriomedico;

import android.provider.ContactsContract;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://www.quianty.com/directoriomedico/app/Register.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, String nombreUsuario, String fechaNacimiento, String correo, String password, int tipoUsuario_id, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombre",nombre);
        params.put("nombreUsuario",nombreUsuario);
        params.put("fechaNacimiento",fechaNacimiento);
        params.put("correo",correo);
        params.put("password",password);
        params.put("tipoUsuario_id",tipoUsuario_id+"");
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
