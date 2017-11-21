package com.yn.user.rentacat.controller;

import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.yn.user.rentacat.R;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.datasource.Tools;
import com.yn.user.rentacat.model.entities.TransmissionType;

public class BranchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
        showBranches();

    }

    /*private void showBranches() {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.Branch.BRANCH_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                CursorAdapter adapter = new CursorAdapter(BranchList.this, cursor,0) {
                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.branch_item, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        TextView address=    (TextView)view.findViewById(R.id.branch_address);
                        TextView parking_spaces=    (TextView)view.findViewById(R.id.branch_parking_spaces);
                        Button  map_button=   (Button) view.findViewById(R.id.branch_button);
                        ImageView branch_imageView = (ImageView) view.findViewById(R.id.branch_image);
                        view.setTag(5,cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY ))+" " +cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET ))+" "+cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER )));


                        address.setText("City: "+cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY ))+" Street: " +cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET ))+" #: "+cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER )));
                        parking_spaces.setText(cursor.getString(cursor.getColumnIndexOrThrow( AppContract.Branch.NUMBER_OF_PARKING_SPACES)));
                        switch (cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY ))) {
                            case "H":
                                branch_imageView.setImageResource(R.drawable.hadera);
                            case "A":
                                branch_imageView.setImageResource(R.drawable.ashdod);
                            case "T":
                                branch_imageView.setImageResource(R.drawable.tel_aviv);
                            case "P":
                                branch_imageView.setImageResource(R.drawable.pt);
                            case "N":
                                branch_imageView.setImageResource(R.drawable.netanya2);
                        }

                    }


                };
                adapter.changeCursor(cursor);
                ((ListView)findViewById(R.id.branch_listview)).setAdapter(adapter);
            }
        }.execute();
    }*/
    private void showBranches() {

        Cursor cursor = getContentResolver().query(AppContract.Branch.BRANCH_URI, null, null, null, null, null);


        CursorAdapter adapter = new CursorAdapter(BranchList.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.branch_item, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView address = (TextView) view.findViewById(R.id.branch_address);
                TextView parking_spaces = (TextView) view.findViewById(R.id.branch_parking_spaces);
               // ImageButton map_button = (ImageButton) view.findViewById(R.id.branch_button);
                ImageView branch_imageView = (ImageView) view.findViewById(R.id.branch_image);
                //view.setTag(5, cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY)) + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET)) + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER)));


                address.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY)) + "\n" + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET)) + "\n #:" + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER)));
                parking_spaces.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Branch.NUMBER_OF_PARKING_SPACES)));
                switch (cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY))) {
                    case "H":
                        branch_imageView.setImageResource(R.drawable.hadera);
                        break;
                    case "A":
                        branch_imageView.setImageResource(R.drawable.ashdod);
                        break;
                    case "T":
                        branch_imageView.setImageResource(R.drawable.tel_aviv);
                        break;
                    case "P":
                        branch_imageView.setImageResource(R.drawable.pt);
                        break;
                    case "N":
                        branch_imageView.setImageResource(R.drawable.netanya2);
                        break;
                    default: branch_imageView.setImageResource(R.drawable.netanya);
                        break;

                }

            }


        };
        adapter.changeCursor(cursor);
        ((ListView) findViewById(R.id.branch_listview)).setAdapter(adapter);
    }

}

