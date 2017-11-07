package com.yn.user.rentacat.model.entities;

import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Client implements Serializable {
private String LastName,
                FirstName;
private int Id,
            PhoneAnInt,
            CraditNumber;

ContactsContract.CommonDataKinds.Email EmailAdress;
ContactsContract.CommonDataKinds.Phone PhoneNumber;
ContactsContract.CommonDataKinds.StructuredName Name;
ContactsContract.CommonDataKinds.Identity ID;
}
