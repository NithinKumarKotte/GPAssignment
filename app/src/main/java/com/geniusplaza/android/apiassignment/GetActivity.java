package com.geniusplaza.android.apiassignment;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.geniusplaza.android.apiassignment.Data.UserList;
import com.geniusplaza.android.apiassignment.Data.Users;
import com.geniusplaza.android.apiassignment.adapter.UserAdapter;
import com.geniusplaza.android.apiassignment.services.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetActivity extends AppCompatActivity {

    UserAdapter userAdapter;
    RecyclerView recyclerView;
    List<Users> listOfUsers = new ArrayList<>();
    int totalNumUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        recyclerView = findViewById(R.id.getlist);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void requestData() {
        final WebService webService = WebService.retrofit.create(WebService.class);
        Call<Users> callCount = webService.countPages();

        //Asynchronous call to retrieve total number pages and total number of users
        callCount.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Log.i("hello world", String.valueOf(response.body().count));

                //number of pages
                int count = response.body().count;

                //total number of users in all the pages
                totalNumUsers = response.body().totalUsers;
                for (int i = 1; i <= count; i++) {
                    Call<UserList> callPage = webService.userItems(i);
                    sendRequest(callPage);
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

    }

    private void sendRequest(Call<UserList> call) {
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList dataItems = response.body();

                for (Users users : dataItems.getUsers()) {
                    System.out.println(users.getFirstName());

                    //Arraylist to store all the users
                    listOfUsers.add(users);
                }

                if (listOfUsers.size() == totalNumUsers) {

                    Toast.makeText(GetActivity.this, "Received user details from service",
                            Toast.LENGTH_SHORT).show();
                    displayData();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.i("On Failure", t.getLocalizedMessage());
                Toast.makeText(GetActivity.this, "Error: Failed to receive items from service",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void displayData() {
        if (listOfUsers != null) {
            userAdapter = new UserAdapter(this, listOfUsers);
            recyclerView.setAdapter(userAdapter);
        }
    }
}
