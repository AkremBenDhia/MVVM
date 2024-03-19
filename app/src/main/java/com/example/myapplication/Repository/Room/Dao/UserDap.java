package com.example.myapplication.Repository.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.Model.User;

import java.util.List;

import retrofit2.http.GET;

@Dao
public interface UserDap {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);


    @Query("SELECT * FROM User")
    List<User> userList();

}
