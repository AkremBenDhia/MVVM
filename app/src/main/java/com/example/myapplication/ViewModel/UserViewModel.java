package com.example.myapplication.ViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.Comment;
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

    public MutableLiveData<List<Comment>> getRemoteComments(Context context){
        return userRepo.getServerComments(context);
    }
    public MutableLiveData<String> postUsers(Context context,List<User> userList){
        return userRepo.postUsers(context,userList);
    }

    public void deleteUser(Context context,Integer id){
         userRepo.deleteUserById(context,id);
    }
}
