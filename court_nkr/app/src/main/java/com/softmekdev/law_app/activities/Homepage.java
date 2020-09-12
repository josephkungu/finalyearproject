package com.softmekdev.law_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.softmekdev.law_app.R;

public class Homepage extends AppCompatActivity {

    EditText search_id;
    TextView search, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        this.setTitle("Home page");

        /*Initialization of views*/

        search = findViewById(R.id.search);
        feedback = findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback_intent = new Intent(Homepage.this, Feedback.class);
                startActivity(feedback_intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(Homepage.this, Search.class);
                startActivity(intent_search);
            }
        });
    }
}
