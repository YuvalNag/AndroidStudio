package com.yn.user.rentacat.controller;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.TransmissionType;



public class client_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.client_item,
                        null,
                        new String[]{AppContract.Client.LAST_NAME,
                                AppContract.Client.EMAIL_ADDR,
                                AppContract.Client.PHONE_NUMBER},
                        new int[]{R.id.client_name, R.id.client_email, R.id.client_phone}
                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.Client.CLIENT_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();
        ((ListView)findViewById(R.id.client_listview)).setAdapter(adapter);
    }
}
