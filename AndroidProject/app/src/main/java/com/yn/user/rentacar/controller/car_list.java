package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;
import com.yn.user.rentacar.model.entities.CarClass;
import com.yn.user.rentacar.model.entities.CarModel;
import com.yn.user.rentacar.model.entities.TransmissionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class car_list extends AppCompatActivity {
    private  Map<Long,CarModel> carModelMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        showCars();
    }



    @SuppressLint("StaticFieldLeak")
    private void showCars() {
        new AsyncTask<Void, Void, Cursor>() {

            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursorCar = getContentResolver().query(AppContract.Car.CAR_URI, null, null, null, null, null);
                Cursor cursorModel =getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);
                carModelMap=new HashMap<>();
//                LongSparseArray

                    cursorModel.moveToFirst();
                    while (!cursorModel.isAfterLast()) {


                        carModelMap.put(cursorModel.getLong(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL)), new CarModel(cursorModel.getLong(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL)),
                                cursorModel.getString(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.COMPENY_NAME)),
                                cursorModel.getString(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)),
                                cursorModel.getLong(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)),
                                TransmissionType.valueOf(cursorModel.getString(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE))),
                                cursorModel.getLong(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.NUM_OF_SEATS)),
                                CarClass.valueOf(cursorModel.getString(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR))),
                                Tools.byteToImage(cursorModel.getBlob(cursorModel.getColumnIndexOrThrow(AppContract.CarModel.IMG)))));


                        cursorModel.moveToNext();
                    }
                    return cursorCar;

                    }






            @Override
            protected void onPostExecute( Cursor cursor) {
                super.onPostExecute(cursor);

                Cursor cursorCar=cursor;

                CursorAdapter adapter = new CursorAdapter(car_list.this, cursorCar, 0) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        return super.getView(position, convertView, parent);
                    }

                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.car_item_card, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        long modelid=cursor.getLong(cursor.getColumnIndexOrThrow(AppContract.Car.CAR_MODEL_ID));
                        CarModel carModel=carModelMap.get(modelid);
                        if(carModel!=null) {
                            TextView trans = (TextView) view.findViewById(R.id.cars_transmition);
                            TextView description = (TextView) view.findViewById(R.id.cars_name_description);
                            TextView classa = (TextView) view.findViewById(R.id.cars_class);
                            TextView engine = (TextView) view.findViewById(R.id.cars_engineCapacity);
                            TextView numseats = (TextView) view.findViewById(R.id.cars_numofseats);
                            ImageView imageView = (ImageView) view.findViewById(R.id.cars_carImage);


                            numseats.setText(String.valueOf(carModel.getNumOfSeats()));
                            trans.setText(carModel.getTransmissionType().toString());
                            description.setText(carModel.getCompenyName() + " " + carModel.getModelName());
                            classa.setText(carModel.getCarClass().toString());
                            engine.setText(String.valueOf(carModel.getEngineCapacity()));
                            imageView.setImageBitmap(carModel.getCarPic());
                        }

                        TextView carid = (TextView) view.findViewById(R.id.car_id);
                        TextView carkilo = (TextView) view.findViewById(R.id.car_kilo);
                        TextView branch = (TextView) view.findViewById(R.id.car_branch);

                        carid.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Car.ID_CAR_NUMBER)));
                        carkilo.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Car.KILOMETRERS)));
                        branch.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Car.BRANCH_NUM)));
                    }


                };
                adapter.changeCursor(cursorCar);
                ((ListView) findViewById(R.id.car_grid_view)).setAdapter(adapter);
            }
        }.execute();


/*  private void showCars() {
        new AsyncTask<Void, Void, List<Cursor>>() {
            @Override
            protected List<Cursor> doInBackground(Void... params) {
                Cursor cursorCar = getContentResolver().query(AppContract.Car.CAR_URI, null, null, null, null, null);
                Cursor cursorModel =getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);
                List<Cursor> cursorList=new ArrayList<>();
                cursorList.add(cursorCar);
                cursorList.add(cursorModel);

                return cursorList;
            }

            @Override
            protected void onPostExecute(final List<Cursor> cursorlist) {
                super.onPostExecute(cursorlist);
                CursorAdapter adapter = new CursorAdapter(car_list.this, cursorlist.get(0), 0) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        return super.getView(position, convertView, parent);
                    }

                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.car_item_card, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        long modelid=cursor.getLong(cursor.getColumnIndexOrThrow(AppContract.Car.CAR_MODEL_ID));
                        Cursor modelCursor =cursorlist.get(1);
                        modelCursor.moveToFirst();
                        while(!modelCursor.isAfterLast()) {
                            if (modelCursor.getLong(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL)) == modelid) {
                                TextView trans = (TextView) view.findViewById(R.id.cars_transmition);
                                TextView description = (TextView) view.findViewById(R.id.cars_name_description);
                                TextView classa = (TextView) view.findViewById(R.id.cars_class);
                                TextView engine = (TextView) view.findViewById(R.id.cars_engineCapacity);
                                TextView numseats = (TextView) view.findViewById(R.id.cars_numofseats);
                                ImageView imageView = (ImageView) view.findViewById(R.id.cars_carImage);


                                numseats.setText(modelCursor.getString(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.NUM_OF_SEATS)));
                                trans.setText(modelCursor.getString(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                                description.setText(modelCursor.getString(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)));
                                classa.setText(modelCursor.getString(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                                engine.setText(modelCursor.getString(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                                imageView.setImageBitmap(Tools.byteToImage(modelCursor.getBlob(modelCursor.getColumnIndexOrThrow(AppContract.CarModel.IMG))));
                                break;

                            }
                            modelCursor.moveToNext();
                        }
                        TextView carid = (TextView) view.findViewById(R.id.car_id);
                        TextView carkilo = (TextView) view.findViewById(R.id.car_kilo);
                        TextView branch = (TextView) view.findViewById(R.id.car_branch);

                        carid.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Car.CAR_MODEL_ID)));
                        carkilo.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Car.KILOMETRERS)));
                        branch.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Car.BRANCH_NUM)));
                    }


                };
                adapter.changeCursor(cursorlist.get(0));
                ((GridView) findViewById(R.id.car_grid_view)).setAdapter(adapter);
            }
        }.execute();*/
    }

}
