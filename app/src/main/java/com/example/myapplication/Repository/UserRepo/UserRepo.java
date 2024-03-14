package com.example.myapplication.Repository.UserRepo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Repository.RetrofitClientInstance;
import com.example.myapplication.Repository.Room.Database;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {
    MutableLiveData<Boolean> getAllusersServerLiveData = new MutableLiveData<>();
    MutableLiveData<List<User>> getLocalusersLiveData = new MutableLiveData<>();


    public UserRepo() {}

    public MutableLiveData<Boolean> getAllUsersServer(Context context){
        UserApi userApi = RetrofitClientInstance.retrofitInstance().create(UserApi.class);
        Call<List<User>> call = userApi.getUsers();
        call.clone().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    Log.i("kkkkkkk", "onResponse: ");
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Database.getInstance(context).userDao().insertAll(response.body());
                                getAllUsersServer(context).postValue(true);
                            }catch (Exception e){
                            }
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                getAllUsersServer(context).postValue(false);

            }
        });
        return getAllusersServerLiveData;
    }



    public MutableLiveData<List<User>> getLocalUsers(Context context){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    getLocalusersLiveData.postValue(Database.getInstance(context).userDao().userList());
                }catch (Exception e){
                    getLocalusersLiveData.postValue(null);

                }
            }
        });
        return getLocalusersLiveData;
    }
}
