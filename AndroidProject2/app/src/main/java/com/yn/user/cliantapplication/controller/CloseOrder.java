package com.yn.user.cliantapplication.controller;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yn.user.cliantapplication.R;

/**
 * Created by nissy34 on 31/12/2017.
 */

public class CloseOrder extends Fragment implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;

    private ListView carGridView;
    private TextView textView2;
    private Button buttonCloseorder;
    private TextInputLayout textInputLayoutKilo;
    private TextInputLayout textInputLayoutFouled;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=     inflater.inflate(R.layout.activity_register_client,container,false);
        findViews(view);
       // populatView();
        return view;
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-12-31 19:44:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        carGridView = (ListView)view.findViewById( R.id.car_grid_view );

        buttonCloseorder = (Button)view.findViewById( R.id.button_closeorder );
        textInputLayoutKilo = (TextInputLayout)view.findViewById( R.id.textInputLayout_kilo );
        textInputLayoutFouled = (TextInputLayout)view.findViewById( R.id.textInputLayout_fouled );

        buttonCloseorder.setOnClickListener( this );
    }

    private void visibiltyopenOrders(int visibility)
    {
        buttonCloseorder.setVisibility(visibility);
        textInputLayoutKilo.setVisibility(visibility);
        textInputLayoutFouled.setVisibility(visibility);n
    }
    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-12-31 19:44:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == buttonCloseorder ) {
            // Handle clicks for buttonCloseorder
        }
    }

}
