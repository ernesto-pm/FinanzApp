package com.example.ernesto.finanzapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ernesto.finanzapp.services.ServerService;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    private EditText emailInput, passwordInput;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (EditText) findViewById(R.id.email);
        passwordInput = (EditText) findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("token")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }


    public void tryLogin(View view) {
        final String emailText     = emailInput.getText().toString();
        final String passwordText  = passwordInput.getText().toString();

        if (TextUtils.isEmpty(emailText)) {
            emailInput.setError("Por favor introduce tu email");
            emailInput.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(passwordText)) {
            passwordInput.setError("Por faovr introduce la contraseña");
            passwordInput.requestFocus();
            return;
        }

        // Make Login request
        JSONObject body = new JSONObject();
        try {
            body.put("email", emailText);
            body.put("password", passwordText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // ToDo use amazon server to connect
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://34.237.236.57:8010/api/v1/user/login", body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String token = response.getString("token");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.networkResponse.statusCode == 401) {
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }

                if(error.networkResponse.statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }

                if(error.networkResponse.statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Error de servidor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        VolleySingleton.getInstance(this).getRequestQueue().add(request);

    }


}
