package com.yn.user.rentacar.controller;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;
import com.yn.user.rentacar.model.entities.Branch;

public class BranchList extends AppCompatActivity {

    ListView branchListView;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
        showBranches();

        branchListView=(ListView) findViewById(R.id.branch_listview);
        branchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                imageButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        branchListView.getItemAtPosition(i);


                    }
                });

            }
        });

    }

    private void showBranches() {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.Branch.BRANCH_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                CursorAdapter adapter = new CursorAdapter(BranchList.this, cursor, 0) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        return super.getView(position, convertView, parent);
                    }

                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.branch_item, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        TextView address = (TextView) view.findViewById(R.id.branch_address);
                        TextView parking_spaces = (TextView) view.findViewById(R.id.branch_parking_spaces);
                        final ImageButton  map_button=   (ImageButton) view.findViewById(R.id.branch_button);
                        final ImageView branch_imageView = (ImageView) view.findViewById(R.id.branch_image);
                        map_button.setTag(R.id.branch_button, cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY))/* + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET)) + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER))*/);


                        map_button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (view == map_button){
                                        ImageButton imageButton=(ImageButton) view;
                                        String adrdress =imageButton.getTag(R.id.branch_button).toString();

                                        Intent intent =new Intent(BranchList.this, MapsActivity.class);
                                        intent.putExtra("Address",adrdress);
                                        startActivity(intent);
                                    }

                                }
                            });
                        address.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY)) + "    " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET)) + "  #:" + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER)));
                        parking_spaces.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Branch.NUMBER_OF_PARKING_SPACES)));
                        switch (cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY))) {
                            case "Hadera":
                                branch_imageView.setImageResource(R.drawable.hadera);
                                break;
                            case "Ashdod":
                                branch_imageView.setImageResource(R.drawable.ashdod);
                                break;
                            case "Tel Aviv":
                                branch_imageView.setImageResource(R.drawable.tel_aviv);
                                break;
                            case "Petah Tikva":

                               // branch_imageView.setImageBitmap(Tools.scaleDown(BitmapFactory.decodeResource(getResources(),R.drawable.pt),4096,true));
                                branch_imageView.setImageResource(R.drawable.pt);
                                break;
                            case "Netanya":
                               // branch_imageView.setImageBitmap(Tools.scaleDown(BitmapFactory.decodeResource(getResources(),R.drawable.netanya2),4096,true));
                                branch_imageView.setImageResource(R.drawable.netanya2);

                                break;
                            default:
                              //  branch_imageView.setImageBitmap(Tools.scaleDown(BitmapFactory.decodeResource(getResources(),R.drawable.netanya2),4096,true));
                                branch_imageView.setImageResource(R.drawable.netanya);

                                break;
                        }

                    }


                };
                adapter.changeCursor(cursor);
                ((ListView) findViewById(R.id.branch_listview)).setAdapter(adapter);
            }
        }.execute();
    }

    /*private void showBranches() {

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
        ((ListView) findViewById(R.id.branch_listview)).setAdapter(adapter);*/
}



