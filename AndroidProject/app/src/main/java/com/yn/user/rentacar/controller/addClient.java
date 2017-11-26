package com.yn.user.rentacar.controller;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;


public class addClient extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private EditText clientFirstname;
    private TextInputLayout textInputLayout3;
    private EditText clientLastname;
    private TextInputLayout textInputLayoutClientEmail;
    private EditText clientEmail;
    private TextInputLayout textInputLayoutClientId;
    private EditText clientId;
    private TextInputLayout textInputLayoutClientPhone;
    private EditText clientPhone;
    private TextInputLayout textInputLayoutclientCreditCard;
    private EditText clientCredit;
    private Button addClient;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-21 00:02:35 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        textInputLayout = (TextInputLayout)findViewById( R.id.textInputLayout );
        clientFirstname = (EditText)findViewById( R.id.client_firstname );
        textInputLayout3 = (TextInputLayout)findViewById( R.id.textInputLayout3 );
        clientLastname = (EditText)findViewById( R.id.client_lastname );
        textInputLayoutClientEmail = (TextInputLayout)findViewById( R.id.textInputLayout_client_email );

        clientEmail = (EditText)findViewById( R.id.client_email );
        clientEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!Patterns.EMAIL_ADDRESS.matcher(charSequence).matches()) {
                    textInputLayoutClientEmail.setErrorEnabled(true);
                    textInputLayoutClientEmail.setError("Enter A Valid Email");

                }
                else
                    textInputLayoutClientEmail.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textInputLayoutClientPhone = (TextInputLayout)findViewById( R.id.textInputLayout_client_phone );
        clientPhone = (EditText)findViewById( R.id.client_phone );
        clientPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!Patterns.PHONE.matcher(charSequence).matches()) {
                    textInputLayoutClientPhone.setErrorEnabled(true);
                    textInputLayoutClientPhone.setError("Enter A Valid Phone Num");

                }
                else
                    textInputLayoutClientPhone.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textInputLayoutClientId = (TextInputLayout)findViewById( R.id.textInputLayout_client_id );
        clientId = (EditText)findViewById( R.id.client_id );
        clientId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()) {
                    textInputLayoutClientId.setErrorEnabled(true);
                    textInputLayoutClientId.setError("This Field Is Mandatory");

                }
                else
                    textInputLayoutClientId.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textInputLayoutclientCreditCard = (TextInputLayout)findViewById( R.id.textInputLayoutclient_credit_card );
        clientCredit = (EditText)findViewById( R.id.client_credit );
        addClient = (Button)findViewById( R.id.add_client );


    }










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        findViews();

    }

    public void onClick(View view) {
      final   ContentValues clientvalues=new ContentValues();
        clientvalues.put(AppContract.Client.ID,((EditText)findViewById(R.id.client_id)).getText().toString());
        clientvalues.put(AppContract.Client.CRADIT_NUMBER,((EditText)findViewById(R.id.client_credit)).getText().toString());
        clientvalues.put(AppContract.Client.PHONE_NUMBER,((EditText)findViewById(R.id.client_phone)).getText().toString());
        clientvalues.put(AppContract.Client.EMAIL_ADDR,((EditText)findViewById(R.id.client_email)).getText().toString());
        clientvalues.put(AppContract.Client.FIRST_NAME,((EditText)findViewById(R.id.client_firstname)).getText().toString());
        clientvalues.put(AppContract.Client.LAST_NAME,((EditText)findViewById(R.id.client_lastname)).getText().toString());
        clientvalues.put(AppContract.Client.PASSWORD,"");


     new AsyncTask<Void, Void, Uri>() {
            @Override
            protected Uri doInBackground(Void... params) {
                return getContentResolver().insert(AppContract.Client.CLIENT_URI, clientvalues);
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
                    Snackbar.make(findViewById(android.R.id.content), "ERROR inserting client " + id, Snackbar.LENGTH_LONG).show();

                }
            }
        }.execute();
       /* long id = ContentUris.parseId(getContentResolver().insert(AppContract.Client.CLIENT_URI, clientvalues));
        if (id > 0)
            Toast.makeText(getBaseContext(), "insert id: " + id, Toast.LENGTH_LONG).show();*/
    }
}
