package com.geniusplaza.android.apiassignment.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/*
* Serialized POJO class for serializing JSONArray data
* */
public class Users implements Serializable {

    @SerializedName("total")
    public int totalUsers;

    @SerializedName("total_pages")
    public int count;
    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String Image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}