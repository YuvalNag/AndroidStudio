package com.yn.user.rentacat.model.backend;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.yn.user.rentacat.model.backend.DBManagerFactory.getManager;

/**
 * Created by USER on 07/11/2017.
 */

public class AppContentProvider extends ContentProvider {


    DB_manager manager;
    final String TAG = "AppContentProvider";

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        manager=getManager();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.d(TAG, "query " + uri.toString());

        String listName = uri.getLastPathSegment();
        // String s = AcademyContract.Student.STUDENT_URI.getLastPathSegment();
        switch (listName) {
            case "Branch":
                return manager.getBranches();//
            case "Client":
                return manager.getClients();//
            case "Car":
                return manager.getCars();//

            case "CarModel":
                return manager.getCarModels();//

        }
        return null;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType " + uri.toString());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Log.d(TAG, "insert " + uri.toString());

        String listName = uri.getLastPathSegment();
        long id = -1;
        switch (listName) {
            case "Client":
                id = manager.addClient(contentValues);
                return ContentUris.withAppendedId(uri, id);

            case "Car":
                id = manager.addCar(contentValues);
                return ContentUris.withAppendedId(uri, id);

            case "CarModel":
                id = manager.addCarModel(contentValues);
                return ContentUris.withAppendedId(uri, id);

            case "Branch":
                id = manager.addBranch(contentValues);
                return ContentUris.withAppendedId(uri, id);

        }
        return null;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        Log.d(TAG, "delete " + uri.toString());

        String listName = uri.getLastPathSegment();
        long id = ContentUris.parseId(uri);
        switch (listName) {
            case "Client":
                if(manager.removeClient(id))
                    return 1;
                break;

            case "Car":
                if(manager.removeCar(id))
                    return 1;
                break;

            case "CarModel":
                if(manager.removeCarModel(id))
                    return 1;
                break;

            case "Branch":
                if(manager.removeBranch(id))
                    return 1;
                break;
        }

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update " + uri.toString());

        String listName = uri.getLastPathSegment();
        long id = ContentUris.parseId(uri);

        switch (listName) {
            case "Client":
                if(manager.updateClient(id,values))
                    return 1;
                break;

            case "Car":
                if(manager.updateCar(id,values))
                    return 1;
                break;

            case "CarModel":
                if(manager.updateCarModel(id,values))
                    return 1;
                break;

            case "Branch":
                if(manager.updateBranch(id,values))
                    return 1;
                break;
        }

        return 0;
    }
}
