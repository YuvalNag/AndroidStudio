package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;

import com.yn.user.rentacat.model.entities.Address;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;

/**
 * Created by nissy34 on 07/11/2017.
 */

public class Tools {
    public static ContentValues AddressToContentValues(Address lecturer) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(AcademyContract.Lecturer.ID, lecturer.getId());
        contentValues.put(AcademyContract.Lecturer.NAME, lecturer.getName());
        contentValues.put(AcademyContract.Lecturer.PHONE, lecturer.getPhone());
        contentValues.put(AcademyContract.Lecturer.SENIORITY, lecturer.getSeniority());

        return contentValues;
    }
    public static ContentValues BranchToContentValues(Branch branch) {}
    public static ContentValues CarToContentValues(Car car) {}
    public static ContentValues CarModelToContentValues(Branch branch) {}



    public static Address ContentValuesToAddress(ContentValues contentValues) {}
    public static Branch ContentValuesToBranch(ContentValues contentValues) {}
    public static Car ContentValuesToCar(ContentValues contentValues) {}
    public static CarModel ContentValuesToCarModel(ContentValues contentValues) {}
    public static Client ContentValuesToClient(ContentValues contentValues) {}

}
