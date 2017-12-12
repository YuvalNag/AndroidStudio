package com.yn.user.rentacar.controller;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yn.user.rentacar.R;
import com.yn.user.rentacar.controller.Adapters.BranchCurserAdapter;
import com.yn.user.rentacar.controller.Adapters.CarModelCurserAdapter;
import com.yn.user.rentacar.model.backend.AppContract;

public class BranchList extends AppCompatActivity {


    long branch_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
        showBranches();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);


        ((ListView) findViewById(R.id.branch_listview)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fab.setVisibility(View.VISIBLE);
                branch_num=l;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are You Sure", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {

                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(View view) {
                                final Uri uri= ContentUris.withAppendedId(AppContract.Branch.BRANCH_URI,branch_num);
                                new AsyncTask<Void, Void, Integer>() {
                                    @Override
                                    protected Integer doInBackground(Void... params) {
                                        return getContentResolver().delete(uri, null,null);
                                    }

                                    @Override
                                    protected void onPostExecute(Integer result) {
                                        super.onPostExecute(result);


                                        if (result > 0) {
                                            //Toast.makeText(getBaseContext(), "insert car   id: " + id, Toast.LENGTH_LONG).show();
                                            Snackbar.make(findViewById(android.R.id.content), "delete barnch  num: " + branch_num, Snackbar.LENGTH_LONG).show();
                                            showBranches();
                                            fab.setVisibility(View.INVISIBLE);

                                        }
                                        else {
                                            //Toast.makeText(getBaseContext(), "error insert car  id: " + id, Toast.LENGTH_LONG).show();
                                            Snackbar.make(findViewById(android.R.id.content), "ERROR deleting branch" , Snackbar.LENGTH_LONG).show();

                                        }
                                    }
                                }.execute();
                            }
                        }).show();
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    private void showBranches() {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.Branch.BRANCH_URI, null, null, null, null, null);

                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                CursorAdapter adapter = new BranchCurserAdapter(BranchList.this, cursor, 0);

                adapter.changeCursor(cursor);
                ((ListView) findViewById(R.id.branch_listview)).setAdapter(adapter);
            }
        }.execute();
    }


}



