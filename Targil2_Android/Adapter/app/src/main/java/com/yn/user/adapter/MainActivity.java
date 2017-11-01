package com.yn.user.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "create", Toast.LENGTH_LONG).show();
        List<String> text = new ArrayList<String>();
        for (int i =0;i<50;i++)
            text.add("This is the Button " + (i+1) );
        GridView mygridview = (GridView) findViewById(R.id.mygridview);
        mygridview.setAdapter(new ItemAdapter(this,text));
    }
}
