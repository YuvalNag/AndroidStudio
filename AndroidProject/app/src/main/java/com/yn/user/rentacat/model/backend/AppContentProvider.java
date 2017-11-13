package com.yn.user.rentacat.model.backend;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by USER on 07/11/2017.
 */

public class AppContentProvider extends ContentProvider {


    DB_manager manager;
    final String TAG = "AppContentProvider";

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
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

        }
        return null;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        Log.d(TAG, "delete " + uri.toString());
/*
        String listName = uri.getLastPathSegment();
        long id = ContentUris.parseId(uri);
        switch (listName) {
            case "students":   /// content://com.oshri.academy/students/23
                if (manager.removeStudent(id))
                    return 1;
                break;

            case "lecturers":
                if (manager.removeLecturer(id))
                    return 1;
                break;

            case "courses":
                if (manager.removeCourse(id))
                    return 1;
                break;
        }
        */
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
