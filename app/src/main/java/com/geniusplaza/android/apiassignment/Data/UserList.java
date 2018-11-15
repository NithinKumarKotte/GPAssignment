package com.geniusplaza.android.apiassignment.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*UseList to Serialize JSON objects received from REST WebServer*/
public class UserList {
    @SerializedName("data")
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
