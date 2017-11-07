package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;

import com.yn.user.rentacat.model.entities.Client;
import com.yn.user.rentacat.model.entities.Order;

/**
 * Created by nissy34 on 07/11/2017.
 */

public class Tools {
    public static ContentValues LecturerToContentValues(Lecturer lecturer) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(AcademyContract.Lecturer.ID, lecturer.getId());
        contentValues.put(AcademyContract.Lecturer.NAME, lecturer.getName());
        contentValues.put(AcademyContract.Lecturer.PHONE, lecturer.getPhone());
        contentValues.put(AcademyContract.Lecturer.SENIORITY, lecturer.getSeniority());

        return contentValues;
    }







    public static ContentValues ClientToContentValues(Client client) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AcademyContract.Lecturer.ID, lecturer.getId());
        contentValues.put(AcademyContract.Lecturer.NAME, lecturer.getName());
        contentValues.put(AcademyContract.Lecturer.PHONE, lecturer.getPhone());
        contentValues.put(AcademyContract.Lecturer.SENIORITY, lecturer.getSeniority());
        return contentValues;
    }
    public static ContentValues OrderToContentValues(Order order) {}


    }
