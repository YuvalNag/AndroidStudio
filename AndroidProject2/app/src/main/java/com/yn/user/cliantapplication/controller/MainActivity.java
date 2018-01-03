package com.yn.user.cliantapplication.controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.SHA_256_Helper;
import com.yn.user.cliantapplication.model.backend.updateReceiver;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransactio;
    TextView headerName;
    TextView headerEmail;
    SharedPreferences sharedPreferences;
    private updateReceiver reMyreceive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reMyreceive=new updateReceiver();

        findViews();
       // populateUser();
    }

    private void populateUser() {
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       // SharedPreferences.Editor editor = sharedPreferences.edit();
        headerName.setText(sharedPreferences.getString(getString(R.string.login_user_name),"unknown"));
        headerEmail.setText(sharedPreferences.getString(getString(R.string.login_user_email),"unknown"));
    }

    private void findViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setCheckedItem(R.id.nav_branches);
       // onNavigationItemSelected(navigationView.getMenu().findItem((R.id.nav_branches)));

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        headerName=findViewById(R.id.header_name);
        headerEmail=findViewById(R.id.header_email);
        populateUser();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

     /*   //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
    }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_branches) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            Fragment branchesFragment =new BranchesFragment();


            fragmentTransaction.replace(R.id.f, branchesFragment);

            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_car_models) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            Fragment carModelFragment = new CarModelFragment();
            fragmentTransaction.replace(R.id.f, carModelFragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_open_orders) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            Fragment closeOrder =new CloseOrder();

            fragmentTransaction.replace(R.id.f, closeOrder);

            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_edit_profile) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            Fragment editProfileFragment =new EditProfileFragment();

            fragmentTransaction.replace(R.id.f, editProfileFragment);

            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_log_out) {
             sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent mainIntent = new Intent(this,LoginActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
        }
        else if (id == R.id.nav_about) {
          }
        else if (id == R.id.nav_email) {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","yuval.nag.91@gmail.com", null));
            //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @SuppressLint("StaticFieldLeak")
    private boolean isValidPassword( String password)  {

        try {
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            long salt=sharedPreferences.getLong(getString(R.string.login_user_salt),0);
            String cPassword=sharedPreferences.getString(getString(R.string.login_user_password),"");

            return SHA_256_Helper.getHash256String(password,salt).contentEquals(cPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(reMyreceive);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        registerReceiver(reMyreceive, new IntentFilter("com.model.backend.UPDATE"));
    }
}