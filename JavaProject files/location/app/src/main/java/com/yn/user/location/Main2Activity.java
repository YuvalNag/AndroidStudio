package com.yn.user.location;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private SimpleLocation location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

                // ...

                // construct a new instance of SimpleLocation
                location = new SimpleLocation(this);


                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... voids) {
                        // if we can't access the location yet
                        if (!location.hasLocationEnabled()) {
                            // ask the user to enable location access
                            SimpleLocation.openSettings(Main2Activity.this);
                        }
                        return null;
                    }
                }.execute();


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.textView)).setText(String.valueOf(location.getLatitude()));
                ((TextView)findViewById(R.id.textView2)).setText(String.valueOf(location.getLongitude()));
                double startLatitude = location.getLatitude();
                double startLongitude = location.getLongitude();
                double endLatitude = 31.059830;

                double endLongitude =  35.046334;
                ((TextView)findViewById(R.id.textView3)).setText(String.valueOf((int)location.calculateDistance(startLatitude, startLongitude, endLatitude, endLongitude)));

                // TODO
            }

        });
            }


            @Override
            protected void onResume() {
                super.onResume();

                // make the device update its location
                location.beginUpdates();

                // ...
            }

            @Override
            protected void onPause() {
                // stop location updates (saves battery)
                location.endUpdates();

                // ...

                super.onPause();
            }

        }


