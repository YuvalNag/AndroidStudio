package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;

public class managerList extends AppCompatActivity {

private Long manager_id;
private GridView managerGridView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_list);
        managerGridView =(GridView)findViewById(R.id.manager_listview);
       showManagers();
        manageDeleteOrEdit();
    }

    @SuppressLint("StaticFieldLeak")
    private void showManagers()
    {
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.manager_card,
                        null,
                        new String[]{AppContract.Manager.FIRST_NAME,
                                AppContract.Manager.LAST_NAME,
                                AppContract.Manager.EMAIL_ADDR,
                                AppContract.Manager.PHONE_NUMBER,
                                AppContract.Manager.BRANCH_ID},
                        new int[]{R.id.manager_firstname,
                                R.id.manager_lastname,
                                R.id.manager_email,
                                R.id.manager_phone,
                                R.id.manager_branch}
                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                return getContentResolver().query(AppContract.Manager.MANAGER_URI, null, null, null, null, null);

            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();
        managerGridView.setAdapter(adapter);
    }

    private  void manageDeleteOrEdit() {
        final FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        fabDelete.setVisibility(View.INVISIBLE);
        fabEdit.setVisibility(View.INVISIBLE);


        managerGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fabDelete.setVisibility(View.VISIBLE);
                fabEdit.setVisibility(View.VISIBLE);
                manager_id =l;
            }
        });
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are You Sure", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {

                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(View view) {

                                final Uri uri = ContentUris.withAppendedId(AppContract.Manager.MANAGER_URI, manager_id);
                                new AsyncTask<Void, Void, Integer>() {
                                    @Override
                                    protected Integer doInBackground(Void... params) {
                                        return getContentResolver().delete(uri, null, null);
                                    }

                                    @Override
                                    protected void onPostExecute(Integer result) {
                                        super.onPostExecute(result);


                                        if (result > 0) {
                                            //Toast.makeText(getBaseContext(), "insert car   id: " + id, Toast.LENGTH_LONG).show();
                                            Snackbar.make(findViewById(android.R.id.content), "delete manager   id: " +manager_id, Snackbar.LENGTH_LONG).show();
                                            showManagers();
                                            fabDelete.setVisibility(View.INVISIBLE);
                                            fabEdit.setVisibility(View.INVISIBLE);

                                        } else {
                                            //Toast.makeText(getBaseContext(), "error insert car  id: " + id, Toast.LENGTH_LONG).show();
                                            Snackbar.make(findViewById(android.R.id.content), "ERROR deleting manager id: " + manager_id, Snackbar.LENGTH_LONG).show();

                                        }
                                    }
                                }.execute();
                            }
                        }).show();
            }
        });



        fabEdit.setOnClickListener(new View.OnClickListener() {



            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {



                Intent intent=new Intent(managerList.this,UpdateManager.class);
                intent.putExtra(AppContract.Manager.ID,manager_id);

                startActivityForResult(intent,1);

            }


        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2&&resultCode==1)
        {
            final FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fab);
            final FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
            fabDelete.setVisibility(View.INVISIBLE);
            fabEdit.setVisibility(View.INVISIBLE);
            showManagers();
            Snackbar.make(findViewById(android.R.id.content), " insert manager id " + data.getLongExtra(AppContract.CarModel.ID_CAR_MODEL,0), Snackbar.LENGTH_LONG).show();
        }
        if(requestCode==1&&resultCode==1)
        {
            final FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fab);
            final FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
            fabDelete.setVisibility(View.INVISIBLE);
            fabEdit.setVisibility(View.INVISIBLE);
            showManagers();
            Snackbar.make(findViewById(android.R.id.content), " update manager id " + data.getLongExtra(AppContract.CarModel.ID_CAR_MODEL,0), Snackbar.LENGTH_LONG).show();
        }
    }

    public void onClick(View view) {
        Intent intent=new Intent(this,addManager.class);
        startActivityForResult(intent,2);
    }
}



