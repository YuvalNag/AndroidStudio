package com.yn.user.rentacar.controller;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;

public class managerList extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_list);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.manager_card,
                        null,
                        new String[]{AppContract.Manager.FIRST_NAME,
                                AppContract.Manager.LAST_NAME,
                                AppContract.Manager.EMAIL_ADDR,
                                AppContract.Manager.PHONE_NUMBER,
                                AppContract.Manager.BRANCH_ID},
                        new int[]{R.id.manager_firstname,
                                  R.id.manager_lastname,
                                  R.id.manager_email,
                                  R.id.manager_phone,
                                R.id.manager_branch}
                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.Manager.MANAGER_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();
        ((GridView) findViewById(R.id.manager_listview)).setAdapter(adapter);
    }
}



