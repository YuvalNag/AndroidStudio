package com.yn.user.rentacat.controller;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.TransmissionType;

public class MainActivity extends AppCompatActivity {

    Button addCarButton;
    Button addCarModelButton;
    Button addClientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }



    void findViews(){
       addCarButton      = (Button) findViewById(R.id.addCarButton);
       addCarModelButton = (Button) findViewById(R.id.addCarModelButton);
       addClientButton   = (Button) findViewById(R.id.addClientButton);
        }
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.addClientButton:
            {
                Intent intent = new Intent(this, addClient.class);
                startActivity(intent);
                break;
            }
            case R.id.showallClientButton: {
                Intent intent = new Intent(this, client_list.class);
                startActivity(intent);
                break;
            }
            case R.id.addCarModelButton:{
                Intent intent = new Intent(this, addCarModel.class);
                startActivity(intent);
                break;
            }
            case R.id.showAllCarModels:{
                Intent intent = new Intent(this, CarModelList.class);
                startActivity(intent);
                break;
            }
            case R.id.showAllBranches:{
                Intent intent = new Intent(this, BranchList.class);
                startActivity(intent);
                break;
            }
        }



    }

    private void showCarList() {
        Cursor cursor;
        /*final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.item_row,
                        null,
                        new String[]{Student.ID, Student.NAME},
                        new int[]{R.id.itemId, R.id.itemName}
                );*/

        cursor = getContentResolver().query(AppContract.Car.CAR_URI, null, null, null, null, null);


    }

    private void addCarModelButton() {
        final Uri uri = AppContract.CarModel.CAR_MODEL_URI;
        final ContentValues contentValues = new ContentValues();

        contentValues.put( AppContract.CarModel.ID_CAR_MODEL,528);
        contentValues.put( AppContract.CarModel.TRANSMISSION_TYPE, TransmissionType.AUTOMATIC.toString());
        contentValues.put( AppContract.CarModel.MODEL_NAME,"cv");
        contentValues.put( AppContract.CarModel.ENGINE_COPACITY,3);
        contentValues.put( AppContract.CarModel.COMPENY_NAME,"ds");
        contentValues.put( AppContract.CarModel.NUM_OF_SEATS,3);
        getBaseContext().getContentResolver().insert(uri, contentValues);

    }


    private void addClientButton() {
        final Uri uri = AppContract.Client.CLIENT_URI;
        final ContentValues contentValues = new ContentValues();

        contentValues.put( AppContract.Client.ID,528);
        contentValues.put( AppContract.Client.CRADIT_NUMBER,1);
        contentValues.put( AppContract.Client.PHONE_NUMBER,"05-2244456");
        contentValues.put( AppContract.Client.EMAIL_ADDR,"nn@gmg.com");
        contentValues.put( AppContract.Client.LAST_NAME,"3");
        contentValues.put( AppContract.Client.FIRST_NAME,"3");
        getBaseContext().getContentResolver().insert(uri, contentValues);


    }

    private void addCarButton() {
        final Uri uri = AppContract.Car.CAR_URI;
        final ContentValues contentValues = new ContentValues();

        contentValues.put( AppContract.Car.ID_CAR_NUMBER,528);
        contentValues.put( AppContract.Car.KILOMETRERS,1);
        contentValues.put( AppContract.Car.BRANCH_NUM,2);
        contentValues.put( AppContract.Car.CAR_MODEL_ID,3);
        getBaseContext().getContentResolver().insert(uri, contentValues);

    }
}
