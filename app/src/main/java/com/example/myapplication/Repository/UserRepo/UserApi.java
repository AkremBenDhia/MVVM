package com.example.myapplication.Repository.UserRepo;

import com.example.myapplication.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("/posts")
    Call<List<User>> getUsers();
}
