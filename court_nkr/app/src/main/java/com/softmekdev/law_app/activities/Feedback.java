package com.softmekdev.law_app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softmekdev.law_app.R;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {

    private static String FEEDBACK_URL = "http://ct.softmekdev.xyz/api/feedback.php";
    Button submit, clear;
    EditText feedback;
    TextView textView_response;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        this.setTitle("FEEDABACK");

        /*Attach to Main*/
        requestQueue = Volley.newRequestQueue(this);

        /*Initialize the views*/
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        textView_response = findViewById(R.id.response);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback.setText("");
            }
        });
        feedback = findViewById(R.id.textarea);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFeedback();
            }
        });
    }

    private void saveFeedback() {
        String feedback_content = feedback.getText().toString().trim();
        // submit.setText(feedback_content);
        saveFeeds(feedback_content);
    }

    private void saveFeeds(final String feedback_content) {
        StringRequest request = new StringRequest(Request.Method.POST, FEEDBACK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        feedback.setText("");
                        feedback.setText("Thank for your Feedback.Feedback sent");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                feedback.setText(error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("message", feedback_content);
                return parms;

            }
        };
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
