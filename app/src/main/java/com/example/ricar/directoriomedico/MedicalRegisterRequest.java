package com.example.ricar.directoriomedico;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MedicalRegisterRequest extends StringRequest {
    private static final String MEDICAL_REGISTER_REQUEST_URL="https://www.quianty.com/directoriomedico/app/MedicalRegister.php";
    private Map<String,String> params;
    public MedicalRegisterRequest(String nombre, String nombreUsuario, String fechaNacimiento, String correo, String password, int tipoUsuario_id, int especialidades_id, int telefono, int celular, int cedula, Response.Listener<String> listener){
        super(Request.Method.POST, MEDICAL_REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombre",nombre);
        params.put("nombreUsuario",nombreUsuario);
        params.put("fechaNacimiento",fechaNacimiento);
        params.put("correo",correo);
        params.put("password",password);
        params.put("tipoUsuario_id",tipoUsuario_id+"");
        params.put("especialidades_id",especialidades_id+"");
        params.put("telefono",telefono+"");
        params.put("celular",celular+"");
        params.put("cedula",cedula+"");
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
