package com.yn.user.rentacat.controller;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.TransmissionType;


public class addClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
    }

    public void onClick(View view) {
      final   ContentValues clientvalues=new ContentValues();
        clientvalues.put(AppContract.Client.ID,((EditText)findViewById(R.id.client_id)).getText().toString());
        clientvalues.put(AppContract.Client.CRADIT_NUMBER,((EditText)findViewById(R.id.client_credit)).getText().toString());
        clientvalues.put(AppContract.Client.PHONE_NUMBER,((EditText)findViewById(R.id.client_phone)).getText().toString());
        clientvalues.put(AppContract.Client.EMAIL_ADDR,((EditText)findViewById(R.id.client_email)).getText().toString());
        clientvalues.put(AppContract.Client.FIRST_NAME,((EditText)findViewById(R.id.client_firstname)).getText().toString());
        clientvalues.put(AppContract.Client.LAST_NAME,((EditText)findViewById(R.id.client_lastname)).getText().toString());
        clientvalues.put(AppContract.Client.PASSWORD,"");


     new AsyncTask<Void, Void, Uri>() {
            @Override
            protected Uri doInBackground(Void... params) {
                return getContentResolver().insert(AppContract.Client.CLIENT_URI, clientvalues);
            }

            @Override
            protected void onPostExecute(Uri uriResult) {
                super.onPostExecute(uriResult);

                long id = ContentUris.parseId(uriResult);
                if (id > 0)
                    Toast.makeText(getBaseContext(), "insert id: " + id, Toast.LENGTH_LONG).show();

                else
                Toast.makeText(getBaseContext(), "error insert id: " + id, Toast.LENGTH_LONG).show();

            }
        }.execute();
       /* long id = ContentUris.parseId(getContentResolver().insert(AppContract.Client.CLIENT_URI, clientvalues));
        if (id > 0)
            Toast.makeText(getBaseContext(), "insert id: " + id, Toast.LENGTH_LONG).show();*/
    }
}
