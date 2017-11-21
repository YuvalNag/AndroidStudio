package com.yn.user.rentacar.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;


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
