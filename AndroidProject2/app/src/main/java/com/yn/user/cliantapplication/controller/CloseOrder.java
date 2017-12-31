package com.yn.user.cliantapplication.controller;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;

/**
 * Created by nissy34 on 31/12/2017.
 */

public class CloseOrder extends Fragment implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;

    private ListView carGridView;
    private TextView textView2;
    private Button button_closeorder;
    private TextInputLayout textInputLayoutKilo;
    private TextInputLayout textInputLayoutFouled;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=     inflater.inflate(R.layout.open_orders_fragment,container,false);
        findViews(view);
         populatView();
        return view;
    }

    private void populatView() {
        new AsyncTask<Void,Void,ArrayAdapter>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(ArrayAdapter arrayAdapter) {
                super.onPostExecute(arrayAdapter);
                carGridView.setAdapter(arrayAdapter);
            }

            @Override
            protected ArrayAdapter doInBackground(Void... voids) {
              return new OrderAdapter(CloseOrder.this.getActivity(), DBManagerFactory.getManager().getCars(),DBManagerFactory.getManager().getOpenOrders(mSharedPreferences.getLong(getString(R.string.login_user_id),-1)),DBManagerFactory.getManager().getCarModels());

            }
        }.execute();
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-12-31 19:44:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        carGridView = (ListView)view.findViewById( R.id.car_grid_view );

        button_closeorder = (Button)view.findViewById( R.id.button_closeorder );
        textInputLayoutKilo = (TextInputLayout)view.findViewById( R.id.textInputLayout_kilo );
        textInputLayoutFouled = (TextInputLayout)view.findViewById( R.id.textInputLayout_fouled );

        button_closeorder.setOnClickListener( this );
    }

    private void visibiltyopenOrders(int visibility)
    {
        button_closeorder.setVisibility(visibility);
        textInputLayoutKilo.setVisibility(visibility);
        textInputLayoutFouled.setVisibility(visibility);
    }
    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-12-31 19:44:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == button_closeorder) {
            // Handle clicks for button_closeorder
        }
    }

}
