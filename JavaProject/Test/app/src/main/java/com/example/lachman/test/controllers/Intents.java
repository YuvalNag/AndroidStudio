package com.example.lachman.test.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lachman.test.R;
import com.example.lachman.test.constants.KeyValues;

public class Intents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents);

        Intent intent = getIntent();
        String message = intent.getStringExtra(KeyValues.KEY1);

        TextView title = (TextView) findViewById(R.id.title2);
        title.setText(message);
    }
}
