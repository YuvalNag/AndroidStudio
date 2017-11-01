package com.yn.user.writeonjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = new Button(this);
        button1.setText("button1");
        TextView textView1 = new TextView(this);
        textView1.setText("text view 1");
        Button button2 = new Button(this);
        button2.setText("button2");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(button1);
        layout.addView(textView1);
        layout.addView(button2);
        setContentView(layout);
    }
}
