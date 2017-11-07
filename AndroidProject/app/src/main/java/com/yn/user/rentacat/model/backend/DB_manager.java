package com.yn.user.rentacat.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;

/**
 * Created by USER on 07/11/2017.
 */

public interface DB_manager {
    boolean hasClient(Client client);
    long addClient(ContentValues values);
    long addModel(ContentValues values);;
    long addCar(ContentValues values);;

    long addCourse(ContentValues values);
    boolean removeCourse(long id);
    boolean updateCourse(long id, ContentValues values);
    Cursor getCourses();

    long addStudentCourse(ContentValues values);

    boolean isUpdatet();
}
