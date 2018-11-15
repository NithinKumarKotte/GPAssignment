package com.geniusplaza.android.apiassignment.services;

import com.geniusplaza.android.apiassignment.Data.UserList;
import com.geniusplaza.android.apiassignment.Data.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {

    String BASE_URL = "https://reqres.in/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // Abstract method to retrieve total count of pages and total number of users
    @GET("api/users/?page=1")
    Call<Users> countPages();

    // Abstract method to retrieve "data" JSON Array from each page
    @GET("api/users/")
    Call<UserList> userItems(@Query("page") int pageNumber);

    @FormUrlEncoded
    @POST("/api/users/")
    Call<Users> insertUser(
            @Field("name") String name,
            @Field("job") String job);

}