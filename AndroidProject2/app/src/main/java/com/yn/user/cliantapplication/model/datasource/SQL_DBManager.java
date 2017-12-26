package com.yn.user.cliantapplication.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.yn.user.cliantapplication.model.backend.AppContract;
import com.yn.user.cliantapplication.model.backend.DB_manager;
import com.yn.user.cliantapplication.model.entities.Branch;
import com.yn.user.cliantapplication.model.entities.Car;
import com.yn.user.cliantapplication.model.entities.CarModel;
import com.yn.user.cliantapplication.model.entities.Client;
import com.yn.user.cliantapplication.model.entities.Manager;
import com.yn.user.cliantapplication.model.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nissy34 on 10/12/2017.
 */

public class SQL_DBManager implements DB_manager {


    static List<Car> availableCars;
    static List<CarModel> carModels;
    static List<Client> clients;
    static List<Order> orders;
    static List<Branch> branches;

    static  final int  EVERY10SEC=10;

    private final String WEB_URL="http://nheifetz.vlab.jct.ac.il/TakeAndGo/";

    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }
    public void printLog(Exception message)
    {
        Log.d(this.getClass().getName(),"Exception-->\n"+message);
    }



    @Override
    public void updateCarModellist() {

        carModels = new ArrayList<>();
        try {
            ContentValues where=new ContentValues();
            String str = PHPtools.POST(WEB_URL + "carModels.php",where);
            JSONArray array = new JSONObject(str).getJSONArray("car_models");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                carModels.add(Tools.ContentValuesToCarModel(contentValues));

            }

        } catch (Exception e) {
            e.printStackTrace();     }
    }
    @Override
    public void updateOrderList() {
        orders = new ArrayList<>();

        try {
            String str = PHPtools.GET(WEB_URL + "orders.php");
            JSONArray array = new JSONObject(str).getJSONArray("orders");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                orders.add(Tools.ContentValuesToOrder(contentValues));

            }

        } catch (Exception e) {
            e.printStackTrace();     }
    }
    @Override
    public void updateAvailablecarList() {

        availableCars = new ArrayList<>();

        try {

            String str = PHPtools.GET(WEB_URL + "availableCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                availableCars.add(Tools.ContentValuesToCar(contentValues));
            }

        } catch (Exception e) {
            e.printStackTrace();     }

    }
    @Override
    public void updateBranchesList() {

        branches = new ArrayList<>();

        try {

            String str = PHPtools.GET(WEB_URL + "branches.php");
            JSONArray array = new JSONObject(str).getJSONArray("branches");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                branches.add(Tools.ContentValuesToBranch(contentValues));

            }
        } catch (Exception e) {
            e.printStackTrace();     }
   }
    @Override
    public void updateClientList() {
        clients = new ArrayList<>();

        try {

            String str = PHPtools.GET(WEB_URL + "clients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                clients.add(Tools.ContentValuesToClient(contentValues));

            }
            } catch (Exception e) {
            e.printStackTrace();     }
        }


    @Override
    public boolean hasClient(long client_id) {
        try {
            printLog("has client");
            return (getClient(client_id) != null);
        } catch (Exception e) {
            printLog(e);

        }
        return false;
    }

    @Override
    public long addClient(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "addClient.php", values);
            long id = Long.parseLong(result);
            printLog("addClient:\n" + result);
            return id;
        } catch (Exception e) {
            printLog("addClient Exception:\n" + e);
            return -1;
        }
    }

    @Override
    public long addOrder(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "addOrder.php", values);
            long id = Long.parseLong(result);
            printLog("addOrder:\n" + result);
            return id;
        } catch (Exception e) {
            printLog("addOrder Exception:\n" + e);
            return -1;
        }
    }

    @Override
    public boolean removeClient(long id) {
        try {
            ContentValues contentValues=new ContentValues();
            contentValues.put(AppContract.Client.ID,id);
            String result=PHPtools.POST(WEB_URL + "deleteclient.php", contentValues);
            printLog("removeClient:\n" + result);
            return true;
        } catch (Exception e) {
            printLog("removeClient Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean updateClient(long id, ContentValues values) {
        try {
            String result=PHPtools.POST(WEB_URL + "updateClient.php", values);
            printLog("updateClient:\n" + result);
            return true;
        } catch (Exception e) {
            printLog("updateClient Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean updateCar(long id, ContentValues values) {
        try {
            String result=PHPtools.POST(WEB_URL + "updateCar.php", values);
            printLog("updateCar:\n" + result);
            return true;
        } catch (Exception e) {
            printLog("updateCar Exception:\n" + e);
            return false;
        }
    }

    @Override
    public List<Car> getAvailableCars() {
        return availableCars;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public List<CarModel> getCarModels() {
        return carModels;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public List<Branch> getBranches() {
        return branches;
    }

    @Override
    public List<Car> getAvailableCarsByBranche(long branch_id) {
        return null;
    }

    @Override
    public List<Car> getAvailableCarsFromPlace(long distance) {
        return null;
    }

    @Override
    public List<Branch> getBrancheOfAvailableCarsByCarModel(long carModel_id) {
        return null;
    }

    @Override
    public Map<Long, List<Car>> mapCarsByBranch() {
        Map<Long, List<Car>> mapCarsByBranch = new HashMap<>();
        for (Branch branch : branches) {
            mapCarsByBranch.put(branch.getBranchID(), getAvailableCarsByBranche(branch.getBranchID()));
        }
        return mapCarsByBranch;

    }

    @Override
    public Map<Long, List<Branch>> mapBranchsByCarModel() {
        Map<Long, List<Branch>> mapBranchsByCarModel = new HashMap<>();
        for (CarModel carModel : carModels) {
            mapBranchsByCarModel.put(carModel.getIdCarModel(), getBrancheOfAvailableCarsByCarModel(carModel.getIdCarModel()));

        }
        return mapBranchsByCarModel;
    }

    /**
     * needs
     * return date
     * fouled? if yes also amount
     * total amount to pay
     * kilometars
     */
    @Override
    public boolean closeOrder(long id, ContentValues values) {
        try {
            String result=PHPtools.POST(WEB_URL + "CloseOrder.php", values);
            printLog("closeOrder:\n" + result);
            return true;
        } catch (Exception e) {
            printLog("closeOrder Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean orderClosedIn10sec() {

        try {
            ContentValues where=new ContentValues();
            where.put("interval",EVERY10SEC);
            String str = PHPtools.POST(WEB_URL + "orderChangedStatus.php",where);
            return (Integer.valueOf(str)) > 0;
        } catch (Exception e) {
            e.printStackTrace();     }
        return false;
    }

    @Override
    public Client getClient(long id) {
        for (Client client:clients) {
            if(client.getId()==id)
                return client;
        }
        return null;
    }


    @Override
    public Order getOrder(long id) {
        for (Order order:orders) {
            if(order.getIdOrderNum()==id)
                return order;
        }
        return null;
    }



    @Override
    public List<Order> getOpenOrders() {
        List<Order> openOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == true)
                openOrders.add(order);
        }
        return openOrders;
    }

}
