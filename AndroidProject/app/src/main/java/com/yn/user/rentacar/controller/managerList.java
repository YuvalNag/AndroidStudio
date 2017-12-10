package com.yn.user.rentacar.controller;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.yn.user.rentacar.R;
import com.yn.user.rentacar.model.backend.AppContract;
import com.yn.user.rentacar.model.backend.SHA_256_Helper;

import static com.yn.user.rentacar.model.backend.DBManagerFactory.getManager;

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
            String password;
            @Override
            public void onClick(View view) {

                ShowAlertDialog();
                try {
                    if(isValidPassword(password)==true)
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            private void ShowAlertDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(managerList.this);
                builder.setTitle("Please enter your password:");
// Set up the input
                final EditText input = new EditText(managerList.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        password = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }

        });



        fabEdit.setOnClickListener(new View.OnClickListener() {

            String password;
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                ShowAlertDialog();
                try {
                    if(isValidPassword(password)==true) {
                        Intent intent = new Intent(managerList.this, UpdateManager.class);
                        intent.putExtra(AppContract.Manager.ID, manager_id);
                        startActivityForResult(intent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void ShowAlertDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(managerList.this);
                builder.setTitle("Please enter your password:");
// Set up the input
                final EditText input = new EditText(managerList.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        password = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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
            Snackbar.make(findViewById(android.R.id.content), " insert manager id " + data.getLongExtra(AppContract.Manager.ID,0), Snackbar.LENGTH_LONG).show();
        }
        if(requestCode==1&&resultCode==1)
        {
            final FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fab);
            final FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
            fabDelete.setVisibility(View.INVISIBLE);
            fabEdit.setVisibility(View.INVISIBLE);
            showManagers();
            Snackbar.make(findViewById(android.R.id.content), " update manager id " + data.getLongExtra(AppContract.Manager.ID,0), Snackbar.LENGTH_LONG).show();
        }
    }

    public void onClick(View view) {
        Intent intent=new Intent(this,addManager.class);
        startActivityForResult(intent,2);
    }


    private boolean isValidPassword(String password) throws Exception {
        Cursor cursorManager= getManager().getManager(manager_id);
        String managerPassword=cursorManager.getString(cursorManager.getColumnIndexOrThrow(AppContract.Manager.PASSWORD));
        long managerSalt=cursorManager.getLong(cursorManager.getColumnIndexOrThrow(AppContract.Manager.SALT));
        Cursor cursorBigManager= getManager().getManager(0);
        String bigManagerPassword=cursorBigManager.getString(cursorBigManager.getColumnIndexOrThrow(AppContract.Manager.PASSWORD));
        long bigManagerSalt=cursorBigManager.getLong(cursorBigManager.getColumnIndexOrThrow(AppContract.Manager.SALT));
        return (managerPassword.contentEquals(SHA_256_Helper.getHash256String(password,managerSalt)))||(bigManagerPassword.contentEquals(SHA_256_Helper.getHash256String(password,bigManagerSalt)));

    }
}



