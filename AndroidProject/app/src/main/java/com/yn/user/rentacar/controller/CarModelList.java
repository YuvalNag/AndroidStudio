package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;


public class CarModelList extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_model_list);


       new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                CursorAdapter adapter = new CursorAdapter(CarModelList.this, cursor, 0) {
                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.carmodel_card, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        TextView trans = (TextView) view.findViewById(R.id.cars_transmition);
                        TextView description = (TextView) view.findViewById(R.id.cars_name_description);
                        TextView classa = (TextView) view.findViewById(R.id.cars_class);
                        TextView engine = (TextView) view.findViewById(R.id.cars_engineCapacity);
                        TextView numseats = (TextView) view.findViewById(R.id.cars_numofseats);
                        ImageView imageView = (ImageView) view.findViewById(R.id.cars_carImage);


                        numseats.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.NUM_OF_SEATS)));
                        trans.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                        description.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)));
                        classa.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                        engine.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                        imageView.setImageBitmap(Tools.byteToImage(cursor.getBlob(cursor.getColumnIndexOrThrow(AppContract.CarModel.IMG))));

                    }


                };
                adapter.changeCursor(cursor);
                ((GridView) findViewById(R.id.model_listview)).setAdapter(adapter);
            }
        }.execute();
        /*Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);

        CursorAdapter adapter = new CursorAdapter(CarModelList.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.carmodel_card, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView trans = (TextView) view.findViewById(R.id.cars_transmition);
                TextView description = (TextView) view.findViewById(R.id.cars_name_description);
                TextView classa = (TextView) view.findViewById(R.id.cars_class);
                TextView engine = (TextView) view.findViewById(R.id.cars_engineCapacity);
                TextView numseats = (TextView) view.findViewById(R.id.cars_numofseats);
                ImageView imageView = (ImageView) view.findViewById(R.id.cars_carImage);


                numseats.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.NUM_OF_SEATS)));
                trans.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                description.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)));
                classa.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                engine.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                imageView.setImageBitmap(Tools.byteToImage(cursor.getBlob(cursor.getColumnIndexOrThrow(AppContract.CarModel.IMG))));

            }


        };
        adapter.changeCursor(cursor);
        ((GridView) findViewById(R.id.model_listview)).setAdapter(adapter);
    }*/
}

