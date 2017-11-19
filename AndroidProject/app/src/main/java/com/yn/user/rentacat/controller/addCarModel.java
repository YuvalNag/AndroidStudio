package com.yn.user.rentacat.controller;

import android.support.design.widget.Snackbar;
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
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.CarClass;
import com.yn.user.rentacat.model.entities.TransmissionType;

public class addCarModel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);
        Toast.makeText(getBaseContext(), "error insert car model id: " , Toast.LENGTH_LONG).show();
        /*Snackbar.make(findViewById(R.id.model_spin_class), "Replace with your own action", Snackbar.LENGTH_LONG).show();*/
        ((Spinner)findViewById(R.id.model_spin_class)).setAdapter(new ArrayAdapter<CarClass>(this, android.R.layout.simple_list_item_1, CarClass.values()));
        ((Spinner)findViewById(R.id.model_spin_trans)).setAdapter(new ArrayAdapter<TransmissionType>(this, android.R.layout.simple_list_item_1, TransmissionType.values()));
    }

    public void onClick(View view) {
       final ContentValues modelcontentValues= new ContentValues();
        modelcontentValues.put(AppContract.CarModel.CLASS_OF_CAR,((Spinner)findViewById(R.id.model_spin_class)).getSelectedItem().toString());
        modelcontentValues.put(AppContract.CarModel.PATH_OF_IMG,((EditText)findViewById(R.id.model_image)).getText().toString());
        modelcontentValues.put(AppContract.CarModel.ENGINE_COPACITY,((EditText)findViewById(R.id.model_engine)).getText().toString());
        modelcontentValues.put(AppContract.CarModel.MODEL_NAME,((EditText)findViewById(R.id.model_name)).getText().toString());
        modelcontentValues.put(AppContract.CarModel.COMPENY_NAME,((EditText)findViewById(R.id.model_comname)).getText().toString());
        modelcontentValues.put(AppContract.CarModel.ID_CAR_MODEL,((EditText)findViewById(R.id.model_id)).getText().toString());
        modelcontentValues.put(AppContract.CarModel.NUM_OF_SEATS,((EditText)findViewById(R.id.model_numofseats)).getText().toString());
        modelcontentValues.put(AppContract.CarModel.TRANSMISSION_TYPE,((Spinner)findViewById(R.id.model_spin_trans)).getSelectedItem().toString());


        new AsyncTask<Void, Void, Uri>() {
            @Override
            protected Uri doInBackground(Void... params) {
                return getContentResolver().insert(AppContract.CarModel.CAR_MODEL_URI, modelcontentValues);
            }

            @Override
            protected void onPostExecute(Uri uriResult) {
                super.onPostExecute(uriResult);

                long id = ContentUris.parseId(uriResult);
                if (id > 0)
                    Toast.makeText(getBaseContext(), "insert car model  id: " + id, Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(getBaseContext(), "error insert car model id: " + id, Toast.LENGTH_LONG).show();

            }
        }.execute();
    }
}
