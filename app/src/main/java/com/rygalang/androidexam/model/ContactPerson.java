package com.rygalang.androidexam.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class ContactPerson {

    @SerializedName("phone_number")
    private String phoneNumber;
    private String name;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
