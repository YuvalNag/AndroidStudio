package com.yn.user.cliantapplication.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yn.user.cliantapplication.R;

/**
 * Created by USER on 28/12/2017.
 */

public class EditProfileFragment extends Fragment {

    SharedPreferences mSharedPreferences;
    private TextInputLayout textInputLayout;
    private EditText userFirstname;
    private TextInputLayout textInputLayout3;
    private EditText userLastname;
    private TextInputLayout textInputLayoutuserEmail;
    private EditText userEmail;
    private TextInputLayout textInputLayoutuserCrediCard;
    private EditText CrediCard;
    private TextInputLayout textInputLayoutuserId;
    private EditText userId;
    private TextInputLayout textInputLayoutuserPhone;
    private EditText userPhone;
    private TextInputLayout textInputLayoutuserPassword;
    private TextInputLayout textInputLayoutuserConfrimPassword;
    private Button updateClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=     inflater.inflate(R.layout.activity_register_client,container,false);
    findView(view);
    populatView();
    return view;
    }

    private void populatView() {
        userEmail.setText(mSharedPreferences.getString(getString(R.string.login_user_email),"Can't find your email"));
        userLastname.setText(mSharedPreferences.getString(getString(R.string.login_user_last_name),"Can't find your name"));
        userFirstname.setText(mSharedPreferences.getString(getString(R.string.login_user_first_name),"Can't find your name"));
        userId.setText(String.valueOf(mSharedPreferences.getLong(getString(R.string.login_user_id),0)));
        userPhone.setText(mSharedPreferences.getString(getString(R.string.login_user_phone_number),""));
        textInputLayoutuserCrediCard.getEditText().setText(String.valueOf(mSharedPreferences.getLong(getString(R.string.login_user_credit_card),0)));

    }

    private void findView(View view){
        textInputLayoutuserPassword = (TextInputLayout) view.findViewById(R.id.user_password);
        textInputLayoutuserConfrimPassword= (TextInputLayout) view.findViewById(R.id.user_password_confrim);
        textInputLayoutuserConfrimPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().contentEquals(textInputLayoutuserPassword.getEditText().getText().toString())) {
                    textInputLayoutuserConfrimPassword.setErrorEnabled(false);
                    textInputLayoutuserPassword.setErrorEnabled(false);
                } else {
                    textInputLayoutuserConfrimPassword.setErrorEnabled(true);
                    textInputLayoutuserPassword.setErrorEnabled(true);
                    textInputLayoutuserConfrimPassword.setError("Password are not matching");
                    textInputLayoutuserPassword.setError("Password are not matching");

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        textInputLayoutuserCrediCard=view.findViewById(R.id.textInputLayout_user_creditCard);
        textInputLayoutuserCrediCard.getEditText().addTextChangedListener(new TextWatcher() {
                                                                              @Override
                                                                              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                                                              }

                                                                              @Override
                                                                              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                                                  if(charSequence.toString().trim().length()<9)
                                                                                  {
                                                                                      textInputLayoutuserCrediCard.setErrorEnabled(true);
                                                                                      textInputLayoutuserCrediCard.setError("min length is 8 numbers");

                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void afterTextChanged(Editable editable) {

                                                                              }
                                                                          }
        );
        textInputLayoutuserPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().contentEquals(textInputLayoutuserConfrimPassword.getEditText().getText().toString())) {
                    textInputLayoutuserConfrimPassword.setErrorEnabled(false);
                    textInputLayoutuserPassword.setErrorEnabled(false);
                } else {
                    textInputLayoutuserConfrimPassword.setErrorEnabled(true);
                    textInputLayoutuserPassword.setErrorEnabled(true);
                    textInputLayoutuserConfrimPassword.setError("Password are not matching");
                    textInputLayoutuserPassword.setError("Password are not matching");

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        textInputLayout = (TextInputLayout) view.findViewById(R.id.textInputLayout);
        userFirstname = (EditText) view.findViewById(R.id.user_firstname);
        textInputLayout3 = (TextInputLayout) view.findViewById(R.id.textInputLayout3);
        userLastname = (EditText) view.findViewById(R.id.user_lastname);

        textInputLayoutuserEmail = (TextInputLayout) view.findViewById(R.id.textInputLayout_user_email);

        userEmail = (EditText) view.findViewById(R.id.user_email);


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

        textInputLayoutuserPhone = (TextInputLayout) view.findViewById(R.id.textInputLayout_user_phone);
        userPhone = (EditText) view.findViewById(R.id.user_phone);
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

        textInputLayoutuserId = (TextInputLayout) view.findViewById(R.id.textInputLayout_user_id);
        userId = (EditText) view.findViewById(R.id.user_id);
        textInputLayoutuserId.setEnabled(false);

        updateClient = (Button) view.findViewById(R.id.add_manager);
        updateClient.setText("update");
    }



}
