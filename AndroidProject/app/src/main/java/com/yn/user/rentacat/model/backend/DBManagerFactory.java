package com.yn.user.rentacat.model.backend;

import com.yn.user.rentacat.model.datasource.List_DBManager;

/**
 * Created by USER on 12/11/2017.
 */

public class DBManagerFactory {

    static DB_manager manager =null;

    public static DB_manager getManager() {
        if (manager == null)
            manager = new List_DBManager();
        return manager;
    }
}
