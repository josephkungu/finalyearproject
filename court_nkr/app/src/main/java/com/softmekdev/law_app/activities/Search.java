package com.softmekdev.law_app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softmekdev.law_app.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Search extends AppCompatActivity {

    ImageView search;
    EditText search_id;
    TextView textView;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.setTitle("Search Case");

        /*Attach to main Thread*/
        requestQueue = Volley.newRequestQueue(this);
        /*View Initilaization*/
        search = findViewById(R.id.search);
        search_id = findViewById(R.id.search_id);
        textView = findViewById(R.id.dump);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String case_no = search_id.getText().toString().trim();
                if (case_no.isEmpty()) {
                    textView.setText("Input valid Case");
                } else {
                    String url = "http://ct.softmekdev.xyz/apicontrollers/searchcase.php?caseNo=" + case_no;
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray itemArray = new JSONArray(response);
                                        String va = itemArray.getString(0);

                                        textView.setText(va);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        // textView.setText(e.getLocalizedMessage());
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText(error.getLocalizedMessage());
                        }
                    });
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(request);
                }
            }
        });
    }
}
