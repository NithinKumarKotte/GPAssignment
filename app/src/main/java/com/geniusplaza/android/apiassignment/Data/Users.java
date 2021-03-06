package com.geniusplaza.android.apiassignment.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/*
* Serialized POJO class for serializing JSONArray data
* */
public class Users implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String Image;
    @SerializedName("total_pages")
    public int count;
    @SerializedName("total")
    public int totalUsers;
    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String job;
    @SerializedName("createdAt")
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }



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