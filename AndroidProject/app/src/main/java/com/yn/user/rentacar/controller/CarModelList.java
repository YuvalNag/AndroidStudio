package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;

import java.util.HashMap;
import java.util.Map;


public class CarModelList extends AppCompatActivity {

   private long carModel_id;

    ProgressBar carModelProgressBar;



   private ListView carModelListView;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_model_list);

        carModelListView= ((ListView)findViewById(R.id.model_listview));
        carModelProgressBar = (ProgressBar)findViewById(R.id.carModel_pb);

        showCarModels();
        manageDeleteOrEdit();

    }

    @SuppressLint("StaticFieldLeak")
    private void showCarModels()
    {
        new AsyncTask<Void, Void, Cursor>() {


            @Override
            protected void onPreExecute() {
                carModelProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);


                return cursor;
            }

            @Override
            protected void onPostExecute(final Cursor cursor) {
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



                        //view.setTag(cursor.getLong(cursor.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL)));

                        numseats.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.NUM_OF_SEATS)));
                        trans.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                        description.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)));
                        classa.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                        engine.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                        //imageView.setImageBitmap(bitmapMap.get(cursor.getLong(cursor.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL))));
                        GlideApp.with(CarModelList.this)
                                .load(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.IMG)))
                                .placeholder(R.drawable.progress_animation)
                                .centerCrop()
                                .into(imageView);
                    }


                };
                adapter.changeCursor(cursor);
               carModelListView.setAdapter(adapter);
                carModelProgressBar.setVisibility(View.GONE);
            }
        }.execute();
    }

    private  void manageDeleteOrEdit() {
        final FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        fabDelete.setVisibility(View.INVISIBLE);
        fabEdit.setVisibility(View.INVISIBLE);


        carModelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fabDelete.setVisibility(View.VISIBLE);
                fabEdit.setVisibility(View.VISIBLE);
                carModel_id =l;
            }
        });
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are You Sure", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {

                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(View view) {

                                final Uri uri = ContentUris.withAppendedId(AppContract.CarModel.CAR_MODEL_URI, carModel_id);
                                new AsyncTask<Void, Void, Integer>() {
                                    @Override
                                    protected Integer doInBackground(Void... params) {
                                        return getContentResolver().delete(uri, null, null);
                                    }

                                    @Override
                                    protected void onPostExecute(Integer result) {
                                        super.onPostExecute(result);


                                        if (result > 0) {
                                            //Toast.makeText(getBaseContext(), "insert car   id: " + id, Toast.LENGTH_LONG).show();
                                            Snackbar.make(findViewById(android.R.id.content), "delete car model   id: " + carModel_id, Snackbar.LENGTH_LONG).show();
                                            showCarModels();
                                            fabDelete.setVisibility(View.INVISIBLE);
                                            fabEdit.setVisibility(View.INVISIBLE);

                                        } else {
                                            //Toast.makeText(getBaseContext(), "error insert car  id: " + id, Toast.LENGTH_LONG).show();
                                            Snackbar.make(findViewById(android.R.id.content), "ERROR deleting car model id: " + carModel_id, Snackbar.LENGTH_LONG).show();

                                        }
                                    }
                                }.execute();
                            }
                        }).show();
            }
        });



        fabEdit.setOnClickListener(new View.OnClickListener() {



                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(View view) {



                                 Intent intent=new Intent(CarModelList.this,UpdateCarModel.class);
                                 intent.putExtra(AppContract.CarModel.ID_CAR_MODEL,carModel_id);

                                startActivityForResult(intent,1);

                            }


        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==1)
        {
            showCarModels();
            Snackbar.make(findViewById(android.R.id.content), "updated car model", Snackbar.LENGTH_LONG)
                    .show();
            final FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fab);
            final FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
            fabDelete.setVisibility(View.INVISIBLE);
            fabEdit.setVisibility(View.INVISIBLE);
        }
        else if (requestCode==2&&resultCode==1)
        {
            data.getLongExtra(AppContract.CarModel.ID_CAR_MODEL,0);
            Snackbar.make(findViewById(android.R.id.content), "insert car model  id: "+data.getLongExtra(AppContract.CarModel.ID_CAR_MODEL,0), Snackbar.LENGTH_LONG).show();
            showCarModels();
        }



    }

    public void onClick(View view) {
        startActivityForResult(new Intent(this,addCarModel.class),2);
    }
}

