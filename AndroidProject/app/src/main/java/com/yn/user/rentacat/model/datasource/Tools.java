package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;


import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.Address;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;

import com.yn.user.rentacat.model.entities.Client;
import com.yn.user.rentacat.model.entities.Order;
import com.yn.user.rentacat.model.entities.TransmissionType;


/**
 * Created by nissy34 on 07/11/2017.
 */

public class Tools {


    public static Address ContentValuesToAddress(ContentValues contentValues)
    {
       return new Address(
               contentValues.getAsString(AppContract.Address.CITY),
               contentValues.getAsString(AppContract.Address.STREET),
               contentValues.getAsInteger(AppContract.Address.NUMBER));
    }

    public static Branch ContentValuesToBranch(ContentValues contentValues)
    {
        return new Branch(
                          contentValues.getAsLong(AppContract.Branch.BRANCH_ID),
                          contentValues.getAsInteger(AppContract.Branch.NUMBER_OF_PARKING_SPACES),
                          ContentValuesToAddress(contentValues));
    }
    public static Car ContentValuesToCar(ContentValues contentValues)
    {
        return new Car(
                contentValues.getAsLong(AppContract.Car.BRANCH_NUM),
                contentValues.getAsLong(AppContract.Car.CAR_MODEL_ID),
                contentValues.getAsLong(AppContract.Car.KILOMETRERS),
                contentValues.getAsLong(AppContract.Car.ID_CAR_NUMBER)

        );
    }
    public static CarModel ContentValuesToCarModel(ContentValues contentValues)
    {
        return new CarModel(
                contentValues.getAsLong(AppContract.CarModel.ID_CAR_MODEL),
                contentValues.getAsString(AppContract.CarModel.COMPENY_NAME),
                contentValues.getAsString(AppContract.CarModel.MODEL_NAME),
                contentValues.getAsLong(AppContract.CarModel.ENGINE_COPACITY),
                TransmissionType.valueOf(contentValues.getAsString(AppContract.CarModel.TRANSMISSION_TYPE)),
                contentValues.getAsLong(AppContract.CarModel.NUM_OF_SEATS)

                );
    }
    public static Client ContentValuesToClient(ContentValues contentValues)
    {
        return  new Client(
                contentValues.getAsString(AppContract.Client.LAST_NAME),
                contentValues.getAsString(AppContract.Client.FIRST_NAME),
                contentValues.getAsString(AppContract.Client.EMAIL_ADDR),
                contentValues.getAsLong(AppContract.Client.ID),
                contentValues.getAsLong(AppContract.Client.PHONE_NUMBER),
                contentValues.getAsLong(AppContract.Client.CRADIT_NUMBER)

                );
    }

}



