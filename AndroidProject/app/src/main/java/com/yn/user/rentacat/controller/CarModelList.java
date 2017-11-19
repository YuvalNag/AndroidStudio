package com.yn.user.rentacat.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.CarClass;
import com.yn.user.rentacat.model.entities.TransmissionType;


public class CarModelList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_model_list);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.carmodel_item,
                        null,
                        new String[]{AppContract.CarModel.MODEL_NAME,
                                AppContract.CarModel.TRANSMISSION_TYPE,
                                AppContract.CarModel.CLASS_OF_CAR,
                                AppContract.CarModel.ENGINE_COPACITY,},
                        new int[]{R.id.cars_name_description,
                                R.id.cars_transmition,
                                R.id.cars_class,
                                R.id.cars_engineCapacity}
                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();
        ((ListView)findViewById(R.id.model_listview)).setAdapter(adapter);
    }
    }

