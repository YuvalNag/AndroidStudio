package com.yn.user.cliantapplication.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by USER on 07/11/2017.
 */

public interface DB_manager {

    //TODO why here?
    boolean hasClient(long client_id);
    long addClient(ContentValues values);
    long addOrder(ContentValues contentValues);
    boolean removeClient(long id);
    public boolean updateClient(long id, ContentValues values);

    boolean updateCar(long id, ContentValues values);
    boolean closeOrder(long id, ContentValues values);
    boolean orderClosedIn10sec();
    Cursor getClient(long id);
    Cursor getOrder(long id);

    Cursor getCarModels();
    Cursor getClients();
    Cursor getBranches();
    Cursor getAvailableCars();
    Cursor getAvailableCarsByBranche();
    Cursor getAvailableCarsFromPlace();
    Cursor getBrancheOfAvailableCarsByCarModel();
    Cursor getOpenOrders();



}
