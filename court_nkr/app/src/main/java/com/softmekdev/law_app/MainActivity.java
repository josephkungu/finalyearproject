package com.softmekdev.law_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softmekdev.law_app.activities.Login;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static String URL_REGISTER = "http://ct.softmekdev.xyz/api/signup.php";
    RequestQueue requestQueue;
    private EditText c_password;
    private EditText email;
    private TextView have_account;
    private ProgressBar loading;
    private Button login;
    private EditText name;
    private EditText password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Attach to main Thread*/
        requestQueue = Volley.newRequestQueue(this);

        /*Initialization of Views*/
        register = findViewById(R.id.register);
        have_account = findViewById(R.id.have_account);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        c_password = findViewById(R.id.c_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
               /* Intent dashboard = new Intent(MainActivity.this, Homepage.class);
                startActivity(dashboard);*/
            }
        });
        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });
    }

    private void registerUser() {
        final String user_name = this.name.getText().toString().trim();
        final String user_email = this.email.getText().toString().trim();
        final String user_password = this.password.getText().toString().trim();

        createSignup(user_name, user_email, user_password);
    }

    private void createSignup(final String user_name, final String user_email, final String user_password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("name", user_name);
                parms.put("email", user_email);
                parms.put("password", user_password);

                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}


