package com.rygalang.androidexam.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Computer3 on 12/28/2017.
 */
@Entity(tableName = "persons")
public class Person {
    @PrimaryKey
    public long _id;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    public String birthday;
    public String email;
    @SerializedName("mobile_number")
    public String mobileNumber;
    public String address;
    @Embedded
    @SerializedName("contact_person")
    public ContactPerson contactPerson;
}
