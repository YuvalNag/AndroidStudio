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

    boolean hasClient(long client_id);

    long addClient(ContentValues values);
    long addCarModel(ContentValues values);
    long addCar(ContentValues values);

    Cursor getCarModels();
    Cursor getClients();
    Cursor getBranches();
    Cursor getCars();
    
}
