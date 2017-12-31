package com.yn.user.cliantapplication.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class StartActivity extends AppCompatActivity {


    SharedPreferences mSharedPreferences;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // smoothProgressBar = (SmoothProgressBar)findViewById(R.id.pb_start);

//Remove notification bar
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if((activeNetwork != null && activeNetwork.isConnectedOrConnecting()))
        {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    // smoothProgressBar.setVisibility(View.VISIBLE);
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
            }.execute();
        }
        else
        {
            Toast.makeText(this,"This App needs internet connection to work",Toast.LENGTH_LONG).show();
            finishAffinity();

        }

    }
}

