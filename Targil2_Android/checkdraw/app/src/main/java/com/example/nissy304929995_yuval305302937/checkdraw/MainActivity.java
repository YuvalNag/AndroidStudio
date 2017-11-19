package com.example.nissy304929995_yuval305302937.checkdraw;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,BlankFragment.OnFragmentInteractionListener,main_order.OnFragmentInteractionListener {

    FragmentTransaction transaction;
int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_NEGATIVE:
                    Toast.makeText(MainActivity.this, "negative button click", Toast.LENGTH_SHORT).show();
                    break;

                case Dialog.BUTTON_NEUTRAL:

                    Toast.makeText(MainActivity.this, "netural button click", Toast.LENGTH_SHORT).show();
                    break;
                case Dialog.BUTTON_POSITIVE:
                    Toast.makeText(MainActivity.this, "positive button click", Toast.LENGTH_SHORT).show();

                    break;
            }
        }};
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag("cam");

            boolean b = getSupportFragmentManager().popBackStackImmediate("cambs", 0);
            if (blankFragment == null && !b) {
                transaction = getSupportFragmentManager().beginTransaction();
                blankFragment = BlankFragment.newInstance(String.valueOf(i), String.valueOf(i));

                transaction.replace(R.id.f, blankFragment, "cam").addToBackStack("cambs");

                transaction.commit();
            }
        } else if (id == R.id.nav_gallery) {
            constrains blankFragment = (constrains) getSupportFragmentManager().findFragmentByTag("con");

            boolean b = getSupportFragmentManager().popBackStackImmediate("con", 0);
            if (blankFragment == null && !b) {
                transaction = getSupportFragmentManager().beginTransaction();
                blankFragment = constrains.newInstance(String.valueOf(i), String.valueOf(i));

                transaction.replace(R.id.f, blankFragment, "con").addToBackStack("con");

                transaction.commit();
            }
        } else if (id == R.id.nav_slideshow) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("dialog title"); //alertDialogBuilder.setMessage("dialog message ....");
            alertDialogBuilder.setNeutralButton("Remind me later", onClickListener);
            alertDialogBuilder.setPositiveButton("Ok", onClickListener);
            alertDialogBuilder.setNegativeButton("Cancel ", onClickListener);
            /*alertDialogBuilder.setAdapter(new ListAdapter() {
            })*/

            alertDialogBuilder.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.item, Arrays.asList(strings.CHEESES)), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            // alertDialogBuilder.setView(R.layout.checkcons);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (id == R.id.nav_manage) {

            main_order blankFragment = (main_order) getSupportFragmentManager().findFragmentByTag("main");

            boolean b = getSupportFragmentManager().popBackStackImmediate("main", 0);
            if (blankFragment == null && !b) {
                transaction = getSupportFragmentManager().beginTransaction();
                blankFragment = main_order.newInstance(String.valueOf(i), String.valueOf(i));

                transaction.replace(R.id.f, blankFragment, "main").addToBackStack("main");

                transaction.commit();
            }} else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

    }
        @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
