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
        /*Toast.makeText(this, "create", Toast.LENGTH_LONG).show();
        List<String> text = new ArrayList<String>();
        for (int i =0;i<50;i++)
            text.add("This is the Button " + (i+1) );*/

        List<Flower> FloweraList = new ArrayList<Flower>();
        FloweraList.add(new Flower("Common Almond", "C:\\Users\\USER\\Documents\\GitHub\\UWP\\targil_2\\GUI\\Assets\\Common Almond.jpg"));
        FloweraList.add(new Flower("Grandiflora Rose", "C:\\Users\\USER\\Documents\\GitHub\\UWP\\targil_2\\GUI\\Assets\\Grandiflora Rose.jpg"));
        FloweraList.add(new Flower("Hybrid Tea Rose", "C:\\Users\\USER\\Documents\\GitHub\\UWP\\targil_2\\GUI\\Assets\\Hybrid Tea Rose.jpg"));

        FloweraList.add(new Flower("Trifolium clypeatum", "C:\\Users\\USER\\Documents\\GitHub\\UWP\\targil_2\\GUI\\Assets\\Trifolium clypeatum.jpg"));
        FloweraList.add(new Flower("King Uzziae Iris", "C:\\Users\\USER\\Documents\\GitHub\\UWP\\targil_2\\GUI\\Assets\\King Uzziae Iris.jpg"));

        GridView mygridview = (GridView) findViewById(R.id.mygridview);
        mygridview.setAdapter(new YuvalItemAdapter(this,FloweraList));
    }
}
