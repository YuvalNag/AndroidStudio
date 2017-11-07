package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;

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
}
