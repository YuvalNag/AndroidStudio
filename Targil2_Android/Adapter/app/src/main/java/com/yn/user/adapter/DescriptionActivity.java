package com.yn.user.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Flower flower = (Flower) getIntent().getSerializableExtra("Flower");
        TextView textViewName = (TextView) findViewById(R.id.textView);
        textViewName.setText(flower.getName());
        TextView textViewD = (TextView) findViewById(R.id.textView2);
        textViewD.setText(flower.getDescription());

    }
}
