package com.yn.user.cliantapplication.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.yn.user.cliantapplication.model.entities.Branch;
import com.yn.user.cliantapplication.model.entities.Car;
import com.yn.user.cliantapplication.model.entities.CarModel;
import com.yn.user.cliantapplication.model.entities.Client;
import com.yn.user.cliantapplication.model.entities.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by USER on 07/11/2017.
 */

public interface DB_manager {

    //update lists
//    void updateCarlist();
    void updateCarModellist();
    void updateOrderList();
    void updateAvailablecarList();
    void updateBranchesList();
    void updateClientList();

    //client
    boolean hasClient(long client_id);
    long addClient(ContentValues values);
    boolean removeClient(long id);
    boolean updateClient(long id, ContentValues values);

    //Cursor getClient(long id);
    Client getClient(long id);


    //order
    long addOrder(ContentValues contentValues);
    boolean closeOrder(long id, ContentValues values);
    boolean orderClosedIn10sec();
    //Cursor getOrder(long id);
    //Cursor getOrders();
    //Cursor getOpenOrders();
   Order getOrder(long id);
   List<Order> getOrders();
   List<Order> getOpenOrders();

    //car
    boolean updateCar(long id, ContentValues values);

    //Cursor getAvailableCars();
    //Cursor getCars();

    List<Car> getAvailableCars();

/*

    Cursor getCarModels();
    Cursor getClients();
    Cursor getBranches();

    Cursor getAvailableCarsByBranche(long branch_id);
    Cursor getAvailableCarsFromPlace(long distance);

    //should we return map?
    Cursor getBrancheOfAvailableCarsByCarModel();
*/

    List<CarModel> getCarModels();
    List<Client> getClients();
    List<Branch> getBranches();

    List<Car> getAvailableCarsByBranche(long branch_id);
    List<Car> getAvailableCarsFromPlace(long distance);
    List<Branch> getBrancheOfAvailableCarsByCarModel(long carModel_id);
    Map<Long,List<Car>> mapCarsByBranch();
    Map<Long,List<Branch>> mapBranchsByCarModel();




}
