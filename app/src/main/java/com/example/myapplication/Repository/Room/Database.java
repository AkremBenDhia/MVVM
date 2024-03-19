package com.example.myapplication.Repository.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Model.User;
import com.example.myapplication.Repository.Room.Dao.UserDap;

@androidx.room.Database(entities = {User.class} ,exportSchema = false,version = 1)
public abstract  class Database extends RoomDatabase {

    public abstract UserDap userDao();

    public static Database getInstance(Context context){
        Database database = Room.databaseBuilder(context.getApplicationContext()
                , Database.class
                ,"MyDatabase").build();

        return database;
    }
}
