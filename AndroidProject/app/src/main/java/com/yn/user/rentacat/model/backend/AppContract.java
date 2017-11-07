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

}
