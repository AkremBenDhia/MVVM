package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<String> list ;
    UserViewModel viewModel ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new UserViewModel();
        lv = findViewById(R.id.lv);
        list = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,0,list);


        viewModel.getUsersServer(this).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if(aBoolean){


                    Toast.makeText(MainActivity.this, "Get Server Ok And Insertion Local Ok", Toast.LENGTH_SHORT).show();

                }else {

                }
            }
        });


        viewModel.getLocalUsers(MainActivity.this).observe(MainActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users==null){
                    Toast.makeText(MainActivity.this, "NULL", Toast.LENGTH_SHORT).show();
                }else {
                    for(User user : users){
                        list.add(user.getBody());
                    }
                    lv.setAdapter(arrayAdapter);
                }
            }
        });
    }




}