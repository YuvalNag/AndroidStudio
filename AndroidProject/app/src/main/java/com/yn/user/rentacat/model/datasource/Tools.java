package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;
import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.Address;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;
import com.yn.user.rentacat.model.entities.TransmissionType;

import java.util.concurrent.Phaser;


/**
 * Created by nissy34 on 07/11/2017.
 */

public class Tools {

    public static ContentValues AddressToContentValues(Address address) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Address.CITY, address.getCity());
        contentValues.put(AppContract.Address.STREET, address.getStreet());
        contentValues.put(AppContract.Address.NUMBER, address.getNumber());
        return contentValues;
    }

    public static Address ContentValuesToAddress(ContentValues contentValues)
    {
        Integer number = contentValues.getAsInteger(AppContract.Address.NUMBER);
        if (number == null || number <= 0 )
            throw new IllegalArgumentException();
        return new Address(
               contentValues.getAsString(AppContract.Address.CITY),
               contentValues.getAsString(AppContract.Address.STREET),
               number);
    }


    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Branch.BRANCH_ID,branch.getBranchID()) ;
        contentValues.put(AppContract.Branch.NUMBER_OF_PARKING_SPACES, branch.getNumberOfParkingSpaces());
        AddressToContentValues(branch.getBranchAddress());
        return contentValues;
    }
    public static ContentValues CarToContentValues(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Car.ID_CAR_NUMBER, car.getIdCarNumber());
        contentValues.put(AppContract.Car.CAR_MODEL_ID, car.getCarModelID());
        contentValues.put(AppContract.Car.BRANCH_NUM, car.getBranchNum());
        contentValues.put(AppContract.Car.KILOMETRERS, car.getKilometers());
        return contentValues;
    }

    public static ContentValues CarModelToContentValues(CarModel carModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.CarModel.ID_CAR_MODEL, carModel.getIdCarModel());
        contentValues.put(AppContract.CarModel.COMPENY_NAME, carModel.getCompenyName());
        contentValues.put(AppContract.CarModel.ENGINE_COPACITY, carModel.getEngineCapacity());
        contentValues.put(AppContract.CarModel.MODEL_NAME, carModel.getModelName());
        contentValues.put(AppContract.CarModel.TRANSMISSION_TYPE, carModel.getTransmissionType().toString());
        contentValues.put(AppContract.CarModel.NUM_OF_SEATS, carModel.getNumOfSeats());

        return contentValues;
    }

    public static ContentValues ClientToContentValues(Client client) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Client.ID, client.getId());
        contentValues.put(AppContract.Client.FIRST_NAME,   client.getFirstName());
        contentValues.put(AppContract.Client.LAST_NAME,    client.getLastName());
        contentValues.put(AppContract.Client.EMAIL_ADDR,   client.getEmailAdrs());
        contentValues.put(AppContract.Client.PHONE_NUMBER, client.getPhoneNum());
        contentValues.put(AppContract.Client.CRADIT_NUMBER,client.getCraditNumber());

        return contentValues;
    }
    //TODO public static ContentValues OrderToContentValues(Order order) {}


    public static Branch ContentValuesToBranch(ContentValues contentValues)
    {
        Long id = contentValues.getAsLong(AppContract.Branch.BRANCH_ID);
        Integer numberOfParkingSpaces = contentValues.getAsInteger(AppContract.Branch.NUMBER_OF_PARKING_SPACES);
        if(id <= 0 || id == null || numberOfParkingSpaces < 0 )
            throw new IllegalArgumentException();

        return new Branch(id, numberOfParkingSpaces.intValue(),//TODO check if null.intValue() == 0
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



