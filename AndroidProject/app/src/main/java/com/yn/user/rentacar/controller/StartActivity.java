package com.yn.user.rentacar.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;
import com.yn.user.rentacar.model.entities.CarClass;
import com.yn.user.rentacar.model.entities.CarModel;
import com.yn.user.rentacar.model.entities.TransmissionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mSharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        long lo=mSharedPreferences.getLong(getString(R.string.login_user_id),0);
        if(mSharedPreferences.getBoolean(getString(R.string.is_login),false)){
            Intent mainIntent =new Intent(this,MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
        }
        else {
            Intent mainIntent = new Intent(this,LoginActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
        }
    }
}
