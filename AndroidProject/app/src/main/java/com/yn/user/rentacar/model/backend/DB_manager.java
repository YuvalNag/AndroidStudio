package com.yn.user.rentacar.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by USER on 07/11/2017.
 */

public interface DB_manager {

    //TODO why here?
    boolean hasClient(long client_id);

    long addClient(ContentValues values);
    long addCarModel(ContentValues values);
    long addCar(ContentValues values);
    long addBranch(ContentValues values);

    boolean removeClient(long id);
    boolean removeCarModel(long id);
    boolean removeCar(long id);
    boolean removeBranch(long id);

    boolean updateClient(long id, ContentValues values);
    boolean updateCar(long id, ContentValues values);
    boolean updateCarModel(long id, ContentValues values);
    boolean updateBranch(long id, ContentValues values);


    Cursor getCarModels();
    Cursor getClients();
    Cursor getBranches();
    Cursor getCars();


}
