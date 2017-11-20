package com.yn.user.rentacat.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.datasource.Tools;
import com.yn.user.rentacat.model.entities.CarClass;
import com.yn.user.rentacat.model.entities.TransmissionType;


public class CarModelList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_model_list);
       /* final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.carmodel_item,
                        null,
                        new String[]{AppContract.CarModel.MODEL_NAME,
                                AppContract.CarModel.TRANSMISSION_TYPE,
                                AppContract.CarModel.CLASS_OF_CAR,
                                AppContract.CarModel.ENGINE_COPACITY,
                                AppContract.CarModel.IMG},

                        new int[]{R.id.cars_name_description,
                                R.id.cars_transmition,
                                R.id.cars_class,
                                R.id.cars_engineCapacity,
                                R.id.cars_carImage}
                );
*/
      new AsyncTask<Void, Void, Cursor>() {
           @Override
           protected Cursor doInBackground(Void... params) {
               Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);
               return cursor;
           }

           @Override
           protected void onPostExecute(Cursor cursor) {
               super.onPostExecute(cursor);
               CursorAdapter adapter = new CursorAdapter(CarModelList.this, cursor,0) {
                   @Override
                   public View newView(Context context, Cursor cursor, ViewGroup parent) {
                       return LayoutInflater.from(context).inflate(R.layout.carmodel_item, parent, false);
                   }
//
                   @Override
                   public void bindView(View view, Context context, Cursor cursor) {
                   TextView trans=    (TextView)view.findViewById(R.id.cars_transmition);
                   TextView description=    (TextView)view.findViewById(R.id.cars_name_description);
                   TextView classa=    (TextView) view.findViewById(R.id.cars_class);
                   TextView  engine=   (TextView)view.findViewById(R.id.cars_engineCapacity);
                    ImageView imageView=  (ImageView)view.findViewById(R.id.cars_carImage);



                    trans.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                       description.setText(cursor.getString(cursor.getColumnIndexOrThrow( AppContract.CarModel.MODEL_NAME)));
                       classa.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                       engine.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                       imageView.setImageBitmap(Tools.byteToImage(cursor.getBlob(cursor.getColumnIndexOrThrow(AppContract.CarModel.IMG))));

                   }


               };
               adapter.changeCursor(cursor);
               ((ListView)findViewById(R.id.model_listview)).setAdapter(adapter);
           }
       }.execute();
/*
     Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);
     CursorAdapter adapter = new CursorAdapter(CarModelList.this, cursor,0) {
         @Override
         public View newView(Context context, Cursor cursor, ViewGroup parent) {
             return LayoutInflater.from(context).inflate(R.layout.carmodel_item, parent, false);
         }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView trans = (TextView) view.findViewById(R.id.cars_transmition);
                TextView description = (TextView) view.findViewById(R.id.cars_name_description);
                TextView classa = (TextView) view.findViewById(R.id.cars_class);
                TextView engine = (TextView) view.findViewById(R.id.cars_engineCapacity);
                ImageView imageView = (ImageView) view.findViewById(R.id.cars_carImage);


                trans.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                description.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)));
                classa.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                engine.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                imageView.setImageBitmap(Tools.byteToImage(cursor.getBlob(cursor.getColumnIndexOrThrow(AppContract.CarModel.IMG))));

            }

        };
        adapter.changeCursor(cursor);

        ((ListView)findViewById(R.id.model_listview)).setAdapter(adapter);*/
    }
    }

