package com.example.nissy304929995_yuval305302937.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    final String ACTIVITY_LIFE_TAG = "activity lifecycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this, "onCreate()",Toast.LENGTH_SHORT).show();
    }
}
