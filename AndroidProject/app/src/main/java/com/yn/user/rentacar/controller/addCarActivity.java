package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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


public class addCarActivity extends AppCompatActivity {

    ListView branchListView;
    ListView carModelListView;
    TextInputLayout idCar;
    TextInputLayout kilometers;
    String branch_id;
    String carModel_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        findViews();
        showBranches();
        showCarModel();
        branchListView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        branchListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                branch_id=view.getTag().toString();
            }
        });

        carModelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                carModel_id=view.getTag().toString();
            }
        });
    }

    private void findViews() {
        branchListView = (ListView) findViewById(R.id.branch_listview);
        carModelListView = (ListView) findViewById(R.id.model_listview);
        idCar = (TextInputLayout) findViewById(R.id.textInputLayout2);
        kilometers = (TextInputLayout) findViewById(R.id.textInputLayout4);

    }



    @SuppressLint("StaticFieldLeak")
    public void onClick(View view) {



        final ContentValues carContentValues= new ContentValues();
        carContentValues.put(AppContract.Car.ID_CAR_NUMBER,idCar.getEditText().getText().toString());
        carContentValues.put(AppContract.Car.KILOMETRERS,kilometers.getEditText().getText().toString());
        carContentValues.put(AppContract.Car.BRANCH_NUM,branch_id);
        carContentValues.put(AppContract.Car.CAR_MODEL_ID,carModel_id);

        new AsyncTask<Void, Void, Uri>() {
            @Override
            protected Uri doInBackground(Void... params) {
                return getContentResolver().insert(AppContract.Car.CAR_URI, carContentValues);
            }

            @Override
            protected void onPostExecute(Uri uriResult) {
                super.onPostExecute(uriResult);

                long id = ContentUris.parseId(uriResult);
                if (id > 0) {
                    //Toast.makeText(getBaseContext(), "insert car   id: " + id, Toast.LENGTH_LONG).show();
                    Snackbar.make(findViewById(android.R.id.content), "insert car   id: " + id, Snackbar.LENGTH_LONG).show();

                }
                else {
                    //Toast.makeText(getBaseContext(), "error insert car  id: " + id, Toast.LENGTH_LONG).show();
                    Snackbar.make(findViewById(android.R.id.content), "ERROR inserting car" , Snackbar.LENGTH_LONG).show();

                }
            }
        }.execute();
    }
    private void showCarModel() {
        Cursor cursor = getContentResolver().query(AppContract.CarModel.CAR_MODEL_URI, null, null, null, null, null);

        CursorAdapter adapter = new CursorAdapter(addCarActivity.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.carmodel_card, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView trans = (TextView) view.findViewById(R.id.cars_transmition);
                TextView description = (TextView) view.findViewById(R.id.cars_name_description);
                TextView classa = (TextView) view.findViewById(R.id.cars_class);
                TextView engine = (TextView) view.findViewById(R.id.cars_engineCapacity);
                TextView numseats = (TextView) view.findViewById(R.id.cars_numofseats);
                final ImageView imageView = (ImageView) view.findViewById(R.id.cars_carImage);




view.setTag(cursor.getLong(cursor.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL)));

                imageView.setTag(R.id.cars_carImage,cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ID_CAR_MODEL)));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(view == imageView ){
                            ImageView image = (ImageView) view;
                            carModel_id = image.getTag(R.id.cars_carImage).toString();
                        }

                    }
                });

                numseats.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.NUM_OF_SEATS)));
                trans.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.TRANSMISSION_TYPE)));
                description.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.MODEL_NAME)));
                classa.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.CLASS_OF_CAR)));
                engine.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppContract.CarModel.ENGINE_COPACITY)));
                imageView.setImageBitmap(Tools.byteToImage(cursor.getBlob(cursor.getColumnIndexOrThrow(AppContract.CarModel.IMG))));

            }


        };
        adapter.changeCursor(cursor);
        carModelListView.setAdapter(adapter);


    }

    @SuppressLint("StaticFieldLeak")
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
                CursorAdapter adapter = new CursorAdapter(addCarActivity.this, cursor, 0) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        return super.getView(position, convertView, parent);
                    }

                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.branch_item, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, final Cursor cursor) {
                        TextView address = (TextView) view.findViewById(R.id.branch_address);
                        TextView parking_spaces = (TextView) view.findViewById(R.id.branch_parking_spaces);
                        final ImageButton map_button = (ImageButton) view.findViewById(R.id.branch_button);
                        final ImageView branch_imageView = (ImageView) view.findViewById(R.id.branch_image);


                        map_button.setTag(R.id.branch_button, cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY))/* + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET)) + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER))*/);

                       view.setTag(cursor.getString((cursor.getColumnIndexOrThrow(AppContract.Branch.BRANCH_ID))));
                       /* branch_imageView.setOnClickListener(new View.OnClickListener() {


                        branch_imageView.setTag(R.id.branch_image,cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Branch.BRANCH_ID)));
                        branch_imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(view == branch_imageView ){
                                    ImageView image = (ImageView) view;
                                    branch_id = image.getTag(R.id.branch_image).toString();
                                 }

                            }
                        });
*/

                        map_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {
                                if (view == map_button) {
                                   Dialog dialog = new Dialog(addCarActivity.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                        dialog.setContentView(R.layout.dialogmap);
                                        dialog.show();
                                        GoogleMap googleMap;


                                        MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
                                        MapsInitializer.initialize(addCarActivity.this);

                                        mMapView = (MapView) dialog.findViewById(R.id.mapView);
                                        mMapView.onCreate(dialog.onSaveInstanceState());
                                        mMapView.onResume();// needed to get the map to display immediately
                                        mMapView.getMapAsync(new OnMapReadyCallback() {
                                            @Override
                                            public void onMapReady(final GoogleMap googleMap) {
                                                try {
                                                    Geocoder geocoder=new Geocoder(addCarActivity.this);

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

                branchListView.setAdapter(adapter);

            }
        }.execute();
    }
}
