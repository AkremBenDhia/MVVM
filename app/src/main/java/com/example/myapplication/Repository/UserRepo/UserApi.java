package com.example.myapplication.Repository.UserRepo;

import androidx.room.Delete;

import com.example.myapplication.Model.Comment;
import com.example.myapplication.Model.Post;
import com.example.myapplication.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/posts")
    Call<List<User>> getUsers();

    @GET("/comments")
    Call<List<Comment>> getComments();

    @POST("/posts")
    Call<Object> postUsers(@Body List<User> userList);


    @DELETE("/posts/{id}")
    Call<Object> deletePostById(@Path("id") Integer id);


}
