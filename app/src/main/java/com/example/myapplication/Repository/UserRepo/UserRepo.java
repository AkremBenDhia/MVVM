package com.example.myapplication.Repository.UserRepo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Comment;
import com.example.myapplication.Model.Post;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Repository.RetrofitClientInstance;
import com.example.myapplication.Repository.Room.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;

public class UserRepo {
    MutableLiveData<Boolean> getAllusersServerLiveData = new MutableLiveData<>();
    MutableLiveData<List<User>> getLocalusersLiveData = new MutableLiveData<>();
    MutableLiveData<List<Comment>> getRemoteCommentsLiveData = new MutableLiveData<>();

    MutableLiveData<String> responsePostLiveData = new MutableLiveData<>();
    UserApi userApi;

    public UserRepo() {
         userApi = RetrofitClientInstance.retrofitInstance().create(UserApi.class);

    }
    public MutableLiveData<Boolean> getAllUsersServer(Context context){
        Call<List<User>> call = userApi.getUsers();
        call.clone().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){

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

    public MutableLiveData<List<Comment>> getServerComments(Context context){
        Call<List<Comment>> callComments = userApi.getComments();
        callComments.clone().enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    getRemoteCommentsLiveData.postValue(response.body());
                }else {
                    getRemoteCommentsLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                getRemoteCommentsLiveData.postValue(null);
            }
        });
        return getRemoteCommentsLiveData;
    }

    public MutableLiveData<String> postUsers(Context context, List<User> userList) {

        UserApi userApi = RetrofitClientInstance.retrofitInstance().create(UserApi.class);

        userApi.postUsers(userList).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()){
                    responsePostLiveData.postValue(String.valueOf(response.code()));
                    Log.i("ResponSeBody", String.valueOf(response.body()));
                }else
                    responsePostLiveData.postValue(String.valueOf(response.code()));

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                responsePostLiveData.postValue(String.valueOf(t.getMessage()));

            }
        });

        return responsePostLiveData;

    }

    public void deleteUserById(Context context, Integer id){
        userApi.deletePostById(id).clone().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "SUCCESS DELETE", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
