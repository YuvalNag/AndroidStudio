package com.yn.user.rentacat.model.entities;

import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Client implements Serializable {
private String LastName,
                FirstName,EmailAdrs;
private int Id,
            PhoneAnInt,
            CraditNumber;

private ContactsContract.CommonDataKinds.Email EmailAdress;
private ContactsContract.CommonDataKinds.Phone PhoneNumber;
    private ContactsContract.CommonDataKinds.StructuredName Name;
    private ContactsContract.CommonDataKinds.Identity ID;

    public Client(String lastName, String firstName, String emailAdrs, int id, int phoneAnInt, int craditNumber, ContactsContract.CommonDataKinds.Email emailAdress, ContactsContract.CommonDataKinds.Phone phoneNumber, ContactsContract.CommonDataKinds.StructuredName name, ContactsContract.CommonDataKinds.Identity ID) {
        LastName = lastName;
        FirstName = firstName;
        EmailAdrs = emailAdrs;
        Id = id;
        PhoneAnInt = phoneAnInt;
        CraditNumber = craditNumber;
        EmailAdress = emailAdress;
        PhoneNumber = phoneNumber;
        Name = name;
        this.ID = ID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmailAdrs() {
        return EmailAdrs;
    }

    public void setEmailAdrs(String emailAdrs) {
        EmailAdrs = emailAdrs;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPhoneAnInt() {
        return PhoneAnInt;
    }

    public void setPhoneAnInt(int phoneAnInt) {
        PhoneAnInt = phoneAnInt;
    }

    public int getCraditNumber() {
        return CraditNumber;
    }

    public void setCraditNumber(int craditNumber) {
        CraditNumber = craditNumber;
    }

    public ContactsContract.CommonDataKinds.Email getEmailAdress() {
        return EmailAdress;
    }

    public void setEmailAdress(ContactsContract.CommonDataKinds.Email emailAdress) {
        EmailAdress = emailAdress;


    }

    public ContactsContract.CommonDataKinds.Phone getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(ContactsContract.CommonDataKinds.Phone phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public ContactsContract.CommonDataKinds.StructuredName getName() {
        return Name;
    }

    public void setName(ContactsContract.CommonDataKinds.StructuredName name) {
        Name = name;
    }

    public ContactsContract.CommonDataKinds.Identity getID() {
        return ID;
    }

    public void setID(ContactsContract.CommonDataKinds.Identity ID) {
        this.ID = ID;
    }
}
