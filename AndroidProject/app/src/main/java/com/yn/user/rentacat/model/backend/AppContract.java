package com.yn.user.rentacat.model.backend;

import android.net.Uri;

/**
 * Created by USER on 07/11/2017.
 */

public class AppContract {

    /**
     * The authority for the contacts provider
     */
    public static final String AUTHORITY = "com.Nissy_Yuval.App";
    /**
     * A content:// style uri to the authority for the contacts provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static class Branch {

        public static final String BRANCH_ID = "_id";
        public static final String NUMBER_OF_PARKING_SPACES = "number_of_parking_spaces";
        public static final String ADDRESS = "address";
        /**
         * The content:// style URI for this table
         */
        public static final Uri BRANCH_URI = Uri.withAppendedPath(AUTHORITY_URI, "branch");
    }

    public static class Address {
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUMBER = "number";
        /**
         * The content:// style URI for this table
         */
        public static final Uri ADDRESS_URI = Uri.withAppendedPath(AUTHORITY_URI, "address");
    }

    public static class Car {

        public static final String ID_CAR_NUMBER = "_id";
        public static final String KILOMETRERS = "kilometers";
        public static final String CAR_MODEL_ID = "carModelID";

        /**
         * The content:// style URI for this table
         */
        public static final Uri CAR_URI = Uri.withAppendedPath(AUTHORITY_URI, "Car");
    }

    public static class CarModel {

        public static final String ID_CAR_NUMBER = "_id";
        public static final String COMPENY_NAME = "compenyName";
        public static final String MODEL_NAME = "modelName";
        public static final String ENGINE_COPACITY = "engineCapacity";
        /**
         * The content:// style URI for this table
         */
        public static final Uri CAR_MODEL_URI = Uri.withAppendedPath(AUTHORITY_URI, "CarModel");
    }

    public static class Client {

        public static final String ID = "_id";
        public static final String LAST_NAME = "lastName";
        public static final String FIRST_NAME = "firstName";
        public static final String EMAIL_ADDR = "emailAdrs";
        public static final String PHONE_NUMBER = "phoneNum";
        public static final String CRADIT_NUMBER = "engineCapacity";
        /**
         * The content:// style URI for this table
         */
        public static final Uri CLIENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "Client");
    }
}
