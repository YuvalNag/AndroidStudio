package com.yn.user.rentacar.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;
import com.yn.user.rentacar.model.entities.CarClass;
import com.yn.user.rentacar.model.entities.TransmissionType;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class addCarModel extends AppCompatActivity implements View.OnClickListener {
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmodel);
        ((Button)findViewById(R.id.model_add)).setOnClickListener(this);
        // Toast.makeText(getBaseContext(), "error insert car model id: " , Toast.LENGTH_LONG).show();
        /*Snackbar.make(findViewById(R.id.model_spin_class), "Replace with your own action", Snackbar.LENGTH_LONG).show();*/
        ((Spinner)findViewById(R.id.model_spin_class)).setAdapter(new ArrayAdapter<CarClass>(this, android.R.layout.simple_list_item_1, CarClass.values()));
        ((Spinner)findViewById(R.id.model_spin_trans)).setAdapter(new ArrayAdapter<TransmissionType>(this, android.R.layout.simple_list_item_1, TransmissionType.values()));
       ((ImageView)findViewById(R.id.model_image)).setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        addCarModel.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                ((ImageView) findViewById(R.id.model_image)).setImageBitmap(Tools.scaleDown(bitmap,500,true));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



//TODO check permissions

    @SuppressLint("StaticFieldLeak")
    public void onClick(View view) {
       final ContentValues modelcontentValues= new ContentValues();
        modelcontentValues.put(AppContract.CarModel.CLASS_OF_CAR,((Spinner)findViewById(R.id.model_spin_class)).getSelectedItem().toString());
        modelcontentValues.put(AppContract.CarModel.IMG, Tools.imageToByte(((BitmapDrawable)((ImageView)findViewById(R.id.model_image)).getDrawable()).getBitmap()));
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
                Toast toast;
                if (id > 0) {
                    //Toast.makeText(getApplicationContext(), "insert car model  id: " + id, Toast.LENGTH_LONG).show();
                    Snackbar.make(findViewById(android.R.id.content), "insert car model  id: " + id, Snackbar.LENGTH_LONG)
                            .show();





                }
                else {
                    Snackbar.make(findViewById(android.R.id.content), "ERROR inserting car model  " , Snackbar.LENGTH_LONG).show();
                    //toast.show();
                }
            }
        }.execute();
    }
}