package com.yn.user.cliantapplication.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;

public class StartActivity extends AppCompatActivity {


    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//Remove notification bar
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (mSharedPreferences.getBoolean(getString(R.string.is_login), false)) {
                    Intent mainIntent = new Intent(StartActivity.this, MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainIntent);
                } else {
                    Intent mainIntent = new Intent(StartActivity.this, LoginActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainIntent);
                }
            }

            @Override
            protected Void doInBackground(Void... voids) {
                 DBManagerFactory.getManager();
                return null;
            }
        };
    }



    }
}
