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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class StartActivity extends AppCompatActivity {

    ProgressBar progressBar;
    SharedPreferences mSharedPreferences;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_start);
        progressBar =findViewById(R.id.progressBar2);

        //Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // smoothProgressBar = (SmoothProgressBar)findViewById(R.id.pb_start);

//Remove notification bar
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if((activeNetwork != null && activeNetwork.isConnectedOrConnecting()))
        {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected void onProgressUpdate(Void... values) {
                    super.onProgressUpdate(values);
                    for (float i=progressBar.getProgress();i<16.66;++i){
                        progressBar.setProgress((int) i);
                    }

                }

                @Override
                protected void onPreExecute() {
                    // smoothProgressBar.setVisibility(View.VISIBLE);
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if (mSharedPreferences.getBoolean(getString(R.string.is_login), false)) {
                        DBManagerFactory.getManager().updateCarModellist();
                        publishProgress();
                        DBManagerFactory.getManager().updateCarlist();
                        publishProgress();
                        DBManagerFactory.getManager().updateOrderList();
                        publishProgress();
                        DBManagerFactory.getManager().updateAvailablecarList();
                        publishProgress();
                        DBManagerFactory.getManager().updateBranchesList();
                        publishProgress();
                        DBManagerFactory.getManager().updateClientList();
                        publishProgress();

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

