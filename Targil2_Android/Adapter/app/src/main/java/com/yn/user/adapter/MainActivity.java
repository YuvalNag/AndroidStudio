package com.yn.user.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.Spinner;
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

        FloweraList.add(new Flower("Common Almond", R.drawable.common_almond, 1,"Carmel, Samarian mountains, Judean mountains, Shefela"));
        FloweraList.add(new Flower("Grandiflora Rose", R.drawable.grandif_iorarose, 2,"d"));
        FloweraList.add(new Flower("Hybrid Tea Rose", R.drawable.hybridtearose, 2.5,"d"));
        FloweraList.add(new Flower("Trifolium clypeatum", R.drawable.trifolium_clypeatum, 3.5,"d"));
        FloweraList.add(new Flower("King Uzziae Iris", R.drawable.king_uzziaeiris, 4,"d"));


        //Spinner myspinner = (Spinner) findViewById(R.id.myspinner);
        //myspinner.setAdapter(new YuvalItemAdapter(this,FloweraList));
        GridView mygridview = (GridView) findViewById(R.id.mygridview);
        mygridview.setAdapter(new YuvalItemAdapter(this,FloweraList));




    }
}
