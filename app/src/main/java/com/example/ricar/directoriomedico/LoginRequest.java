package com.example.ricar.directoriomedico;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL="https://www.quianty.com/directoriomedico/app/Tablamedicos.php";
    private Map<String,String> params;
    public LoginRequest(String nombreUsuario, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombreUsuario",nombreUsuario);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
