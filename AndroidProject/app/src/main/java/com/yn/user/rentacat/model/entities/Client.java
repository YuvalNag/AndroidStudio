package com.yn.user.rentacat.model.entities;

import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

    public class Client implements Serializable {

        private String lastName;
        private String firstName;
        private String emailAdrs;

        private long id;
        private long phoneNum;
        private long craditNumber;

        private ContactsContract.CommonDataKinds.Email EmailAdress;
        private ContactsContract.CommonDataKinds.Phone PhoneNumber;
        private ContactsContract.CommonDataKinds.StructuredName Name;
        private ContactsContract.CommonDataKinds.Identity ID;

        public Client(String lastName, String firstName, String emailAdrs, long id, long phoneNum, long craditNumber) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.emailAdrs = emailAdrs;
            this.id = id;
            this.phoneNum = phoneNum;
            this.craditNumber = craditNumber;
            }

            public Client(long craditNumber, ContactsContract.CommonDataKinds.Email emailAdress, ContactsContract.CommonDataKinds.Phone phoneNumber,
                  ContactsContract.CommonDataKinds.StructuredName name, ContactsContract.CommonDataKinds.Identity ID) {

                this.craditNumber = craditNumber;
                EmailAdress = emailAdress;
                PhoneNumber = phoneNumber;
                Name = name;
                this.ID = ID;
            }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getEmailAdrs() {
            return emailAdrs;
        }

        public void setEmailAdrs(String emailAdrs) {
            this.emailAdrs = emailAdrs;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(long phoneNum) {
            this.phoneNum = phoneNum;
        }

        public long getCraditNumber() {
            return craditNumber;
        }

        public void setCraditNumber(long craditNumber) {
            this.craditNumber = craditNumber;
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
