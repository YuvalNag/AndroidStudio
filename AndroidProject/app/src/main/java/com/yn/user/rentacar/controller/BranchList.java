package com.yn.user.rentacar.controller;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.datasource.Tools;
import com.yn.user.rentacar.model.entities.Branch;

import java.util.HashMap;
import java.util.Map;

public class BranchList extends AppCompatActivity {

    ListView branchListView;
    ImageButton imageButton;
   //Map<Long,Bitmap> carsImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
        showBranches();

 /*       branchListView=(ListView) findViewById(R.id.branch_listview);
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
        });*/

    }

    @SuppressLint("StaticFieldLeak")
    private void showBranches() {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(AppContract.Branch.BRANCH_URI, null, null, null, null, null);
               /* carsImages=new HashMap<>();
                if (cursor!=null) {
                    cursor.moveToFirst();
                    while(!cursor.isAfterLast()) {
                        carsImages.put(cursor.getLong(cursor.getColumnIndexOrThrow(AppContract.Branch.)))
                    }
                }*/

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
                                public void onClick(final View view) {
                                    if (view == map_button){
                                        Dialog dialog = new Dialog(BranchList.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                        dialog.setContentView(R.layout.dialogmap);
                                        dialog.show();
                                        GoogleMap googleMap;


                                        MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
                                        MapsInitializer.initialize(BranchList.this);

                                        mMapView = (MapView) dialog.findViewById(R.id.mapView);
                                        mMapView.onCreate(dialog.onSaveInstanceState());
                                        mMapView.onResume();// needed to get the map to display immediately
                                        mMapView.getMapAsync(new OnMapReadyCallback() {
                                            @Override
                                            public void onMapReady(final GoogleMap googleMap) {
                                                try {
                                                    Geocoder geocoder=new Geocoder(BranchList.this);

                                                    Address addresses= geocoder.getFromLocationName(((ImageButton) view).getTag(R.id.branch_button).toString(),1).get(0);////your lat lng
                                                    LatLng posisiabsen=new LatLng(addresses.getLatitude(),addresses.getLongitude());
                                                    googleMap.addMarker(new MarkerOptions().position(posisiabsen).title(((ImageButton) view).getTag(R.id.branch_button).toString()));
                                                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(posisiabsen));
                                                    googleMap.getUiSettings().setAllGesturesEnabled(true);
                                                    googleMap.getUiSettings().setMapToolbarEnabled(true);
                                                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posisiabsen,14), 1000, null);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }

                                }
                            });
                       // branch_imageView.setImageResource(R.drawable.ashdod);
                       // branch_imageView.setImageResource(R.drawable.netanya2);
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
                                // branch_imageView.setImageResource(R.drawable.netanya2);
                                branch_imageView.setImageResource(R.drawable.tel_aviv);
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


}



