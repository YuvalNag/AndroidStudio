package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
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


public class addManager extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private EditText userFirstname;
    private TextInputLayout textInputLayout3;
    private EditText userLastname;
    private TextInputLayout textInputLayoutuserEmail;
    private EditText userEmail;
    private TextInputLayout textInputLayoutuserId;
    private EditText userId;
    private TextInputLayout textInputLayoutuserPhone;
    private EditText userPhone;
    private TextInputLayout textInputLayoutuserPassword;
    private TextInputLayout textInputLayoutuserConfrimPassword;
    private Button addManager;
    private ListView branchListView;
    private String branch_id;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-21 00:02:35 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        textInputLayoutuserPassword = (TextInputLayout) findViewById(R.id.user_password);
        textInputLayoutuserConfrimPassword= (TextInputLayout) findViewById(R.id.user_password_confrim);
        textInputLayoutuserConfrimPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().contentEquals(textInputLayoutuserPassword.getEditText().getText().toString())) {
                    textInputLayoutuserConfrimPassword.setErrorEnabled(false);
                } else {
                    textInputLayoutuserConfrimPassword.setErrorEnabled(true);
                    textInputLayoutuserConfrimPassword.setError("Password are not matching");

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        userFirstname = (EditText) findViewById(R.id.user_firstname);
        textInputLayout3 = (TextInputLayout) findViewById(R.id.textInputLayout3);
        userLastname = (EditText) findViewById(R.id.user_lastname);
        textInputLayoutuserEmail = (TextInputLayout) findViewById(R.id.textInputLayout_user_email);
        branchListView = (ListView) findViewById(R.id.branch_listview);
        userEmail = (EditText) findViewById(R.id.user_email);


        userEmail.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!Patterns.EMAIL_ADDRESS.matcher(charSequence).matches()) {
                    textInputLayoutuserEmail.setErrorEnabled(true);
                    textInputLayoutuserEmail.setError("Enter A Valid Email");

                } else
                    textInputLayoutuserEmail.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textInputLayoutuserPhone = (TextInputLayout) findViewById(R.id.textInputLayout_user_phone);
        userPhone = (EditText) findViewById(R.id.user_phone);
        userPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!Patterns.PHONE.matcher(charSequence).matches()) {
                    textInputLayoutuserPhone.setErrorEnabled(true);
                    textInputLayoutuserPhone.setError("Enter A Valid Phone Num");

                } else
                    textInputLayoutuserPhone.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textInputLayoutuserId = (TextInputLayout) findViewById(R.id.textInputLayout_user_id);
        userId = (EditText) findViewById(R.id.user_id);
        userId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()||charSequence.toString().trim()=="0") {
                    textInputLayoutuserId.setErrorEnabled(true);
                    textInputLayoutuserId.setError("This Field Is Mandatory");

                } else
                    textInputLayoutuserId.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        addManager = (Button) findViewById(R.id.add_manager);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();
        showBranches();
        branchListView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        branchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 branch_id = view.getTag().toString();

            }
        });
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
                CursorAdapter adapter = new CursorAdapter(com.yn.user.rentacar.controller.addManager.this, cursor, 0) {
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



                        view.setTag(cursor.getString((cursor.getColumnIndexOrThrow(AppContract.Branch.BRANCH_ID))));


                        map_button.setTag(R.id.branch_button, cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.CITY))/* + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.STREET)) + " " + cursor.getString(cursor.getColumnIndexOrThrow(AppContract.Address.NUMBER))*/);
                        map_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {
                                if (view == map_button) {
                                    Dialog dialog = new Dialog(com.yn.user.rentacar.controller.addManager.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                    dialog.setContentView(R.layout.dialogmap);
                                    dialog.show();
                                    GoogleMap googleMap;


                                    MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
                                    MapsInitializer.initialize(com.yn.user.rentacar.controller.addManager.this);

                                    mMapView = (MapView) dialog.findViewById(R.id.mapView);
                                    mMapView.onCreate(dialog.onSaveInstanceState());
                                    mMapView.onResume();// needed to get the map to display immediately
                                    mMapView.getMapAsync(new OnMapReadyCallback() {
                                        @Override
                                        public void onMapReady(final GoogleMap googleMap) {
                                            try {
                                                Geocoder geocoder=new Geocoder(com.yn.user.rentacar.controller.addManager.this);

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

    public void onClick(View view) {
        if (view == addManager) {
            final ContentValues uservalues = new ContentValues();
            uservalues.put(AppContract.Manager.ID, ((EditText) findViewById(R.id.user_id)).getText().toString());
            uservalues.put(AppContract.Manager.BRANCH_ID, branch_id);
            uservalues.put(AppContract.Manager.PHONE_NUMBER, ((EditText) findViewById(R.id.user_phone)).getText().toString());
            uservalues.put(AppContract.Manager.EMAIL_ADDR, ((EditText) findViewById(R.id.user_email)).getText().toString());
            uservalues.put(AppContract.Manager.FIRST_NAME, ((EditText) findViewById(R.id.user_firstname)).getText().toString());
            uservalues.put(AppContract.Manager.LAST_NAME, ((EditText) findViewById(R.id.user_lastname)).getText().toString());
            uservalues.put(AppContract.Manager.PASSWORD, ((EditText) findViewById(R.id.user_pass)).getText().toString());


            new AsyncTask<Void, Void, Uri>() {
                @Override
                protected Uri doInBackground(Void... params) {
                    return getContentResolver().insert(AppContract.Manager.MANAGER_URI, uservalues);
                }

                @Override
                protected void onPostExecute(Uri uriResult) {
                    super.onPostExecute(uriResult);

                    long id = ContentUris.parseId(uriResult);
                    if (id > 0) {
                        // Toast.makeText(getBaseContext(), "insert id: " + id, Toast.LENGTH_LONG).show();
                        Snackbar.make(findViewById(android.R.id.content), "insert id: " + id, Snackbar.LENGTH_LONG).show();
                    } else {

                        //Toast.makeText(getBaseContext(), "error insert id: " + id, Toast.LENGTH_LONG).show();
                        Snackbar.make(findViewById(android.R.id.content), "ERROR inserting user " + id, Snackbar.LENGTH_LONG).show();

                    }
                }
            }.execute();
        }

    }

       /* long id = ContentUris.parseId(getContentResolver().insert(AppContract.user.user_URI, uservalues));
        if (id > 0)
            Toast.makeText(getBaseContext(), "insert id: " + id, Toast.LENGTH_LONG).show();*/
}

