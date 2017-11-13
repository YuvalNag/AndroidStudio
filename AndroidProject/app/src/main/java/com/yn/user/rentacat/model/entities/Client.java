package com.yn.user.rentacat.model.entities;

import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

    public class Client implements Serializable {

        private String lastName;
        private String firstName;
        private String emailAdrs;

        private long id;


        private String phoneNum;

        private long craditNumber;

     /*   private ContactsContract.CommonDataKinds.Email EmailAdress;
        private ContactsContract.CommonDataKinds.Phone PhoneNumber;
        private ContactsContract.CommonDataKinds.StructuredName Name;
        private ContactsContract.CommonDataKinds.Identity ID;


            public Client(long craditNumber, ContactsContract.CommonDataKinds.Email emailAdress, ContactsContract.CommonDataKinds.Phone phoneNumber,
                  ContactsContract.CommonDataKinds.StructuredName name, ContactsContract.CommonDataKinds.Identity ID) {

                this.craditNumber = craditNumber;
                EmailAdress = emailAdress;
                PhoneNumber = phoneNumber;
                Name = name;
                this.ID = ID;
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
*/
        public Client(String lastName, String firstName, String emailAdrs, long id, String phoneNum, long craditNumber) {
            setLastName(lastName);
             setFirstName(firstName);
             setEmailAdrs(emailAdrs);
            setId(id);
            setPhoneNum(phoneNum);
            setCraditNumber(craditNumber);
            }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            if(lastName.isEmpty())
                this.lastName = lastName;
            else
                this.lastName = "";



        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            if(firstName.isEmpty())
            this.firstName = firstName;
            else
                this.firstName="";
        }

        public String getEmailAdrs() {
            return emailAdrs;
        }

        public void setEmailAdrs(String emailAdrs) {
            if(Patterns.EMAIL_ADDRESS.matcher(emailAdrs).matches())
                this.emailAdrs = emailAdrs;
            else
                throw new IllegalArgumentException("email address");
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPhoneNum() {

            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            if(Patterns.PHONE.matcher(phoneNum).matches())
                    this.phoneNum = phoneNum;
            else
                throw new IllegalArgumentException("phone number");
        }

        public long getCraditNumber() {
            return craditNumber;
        }

        public void setCraditNumber(long craditNumber) {
            if(craditNumber>0)
             this.craditNumber = craditNumber;
            else
                throw new IllegalArgumentException("Cradit card Number");

        }


}
