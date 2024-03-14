package com.example.myapplication.ViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.User;
import com.example.myapplication.Repository.UserRepo.UserRepo;

import java.io.Closeable;
import java.util.List;

public class UserViewModel extends ViewModel {

    UserRepo userRepo ;
    public UserViewModel() {
        userRepo = new UserRepo();
    }

    public MutableLiveData<Boolean> getUsersServer(Context context){
        return userRepo.getAllUsersServer(context);
    }

    public MutableLiveData<List<User>> getLocalUsers(Context context){
        return userRepo.getLocalUsers(context);
    }
}
