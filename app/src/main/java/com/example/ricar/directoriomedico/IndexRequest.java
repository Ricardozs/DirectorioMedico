package com.example.ricar.directoriomedico;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class IndexRequest extends StringRequest{
    private static final String INDEX_REQUEST_URL="https://www.quianty.com/directoriomedico/app/Login.php";
    private Map<String,String> params;
    public IndexRequest(int especialidad_id, Response.Listener<String> listener){
        super(Request.Method.POST, INDEX_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("especialidad_id",especialidad_id+"");
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
