package com.softmekdev.law_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softmekdev.law_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    static String URL_LOGIN = "http://ct.softmekdev.xyz/api/login.php";
    Button button_login;
    EditText mEmail, mPassword;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        /*Attach Volley to Main Thread*/
        requestQueue = Volley.newRequestQueue(this);

        /*views Initialization*/
        button_login = findViewById(R.id.login);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUSer();

            }
        });

    }

    private void loginUSer() {
        final String email = mEmail.getText().toString().trim();
        final String pass = mPassword.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (!object.getBoolean("sucsses")) {
                                final String name = object.getString("name");
                                final String emaildata = object.getString("email");
                                Intent homepage = new Intent(Login.this, Homepage.class);
                                startActivity(homepage);

                            } else {
                                Toast.makeText(getApplicationContext(), "User Login failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // mPassword.setText(e.getLocalizedMessage());
                            Intent homepage = new Intent(Login.this, Homepage.class);
                            startActivity(homepage);
                            //Toast.makeText(Login.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mEmail.setText(error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("email", email);
                parms.put("password", pass);
                return parms;
            }
        };
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
