package com.example.lachman.test.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lachman.test.R;
import com.example.lachman.test.constants.KeyValues;

public class Main extends AppCompatActivity {

    EditText userName,userPassword;
    Button cancel,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);

        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submit);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userName.setText("");
                    userPassword.setText("");
                }
                catch(Exception ex){
                    Log.d("APP","error: " + ex.getMessage());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String password = userPassword.getText().toString();

                if (user.equals(KeyValues.VALUE_USER) && password.equals(KeyValues.VALUE_PASSWORD)){
                    Intent intent = new Intent(Main.this, Intents.class);
                    intent.putExtra(KeyValues.KEY1 , KeyValues.VALUE1);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Main.this, R.string.alerts_wrong_credentials, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
