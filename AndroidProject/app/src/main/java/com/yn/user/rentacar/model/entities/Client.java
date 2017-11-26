package com.yn.user.rentacar.model.entities;

import android.util.Patterns;

import com.yn.user.rentacar.model.backend.SHA_256_Helper;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by USER on 07/11/2017.
 */

    public class Client extends User {

        private String lastName;
        private String firstName;
        private String emailAdrs;

        private long id;
        private final long salt;
        private String password;

        private String phoneNum;

        private long craditNumber;

        public Client(String lastName, String firstName, String emailAdrs, long id, String phoneNum, long craditNumber,String password) throws Exception {
            Random random =new Random();
            salt = random.nextLong();
            setLastName(lastName);
            setFirstName(firstName);
            setEmailAdrs(emailAdrs);
            setId(id);
            setPhoneNum(phoneNum);
            setCraditNumber(craditNumber);
            setPassword(password);
        }

        public String getLastName() {
            return lastName;
        }

    public long getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {

            if(password.isEmpty())
                password="";
        this.password = SHA_256_Helper.getHash256String(password,salt);
    }

    public void setLastName(String lastName) {
            if(!lastName.isEmpty())
                this.lastName = lastName;
            else
                this.lastName = "";



        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            if(!firstName.isEmpty())
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
