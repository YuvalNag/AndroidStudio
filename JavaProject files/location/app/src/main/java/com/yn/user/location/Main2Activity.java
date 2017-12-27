package com.yn.user.location;

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

                // if we can't access the location yet
                if (!location.hasLocationEnabled()) {
                    // ask the user to enable location access
                    SimpleLocation.openSettings(this);
                }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.textView)).setText(String.valueOf(location.getLatitude()));
                ((TextView)findViewById(R.id.textView2)).setText(String.valueOf(location.getLongitude()));
                double startLatitude = location.getLatitude();
                double startLongitude = location.getLongitude();
                double endLatitude = 32.768747;
                
                double endLongitude =  35.5465553;
                ((TextView)findViewById(R.id.textView3)).setText(String.valueOf(location.calculateDistance(startLatitude, startLongitude, endLatitude, endLongitude)));

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


