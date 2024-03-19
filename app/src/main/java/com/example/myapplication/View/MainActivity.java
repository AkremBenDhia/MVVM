package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Model.Comment;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.UserViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<String> list ;
    UserViewModel viewModel ;
    MaterialButton commentsBtn,userBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        commentsBtn = findViewById(R.id.comments);
        userBtn = findViewById(R.id.users);

        viewModel = new UserViewModel();
        list = new ArrayList<>();

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1,0,list);

                viewModel.getUsersServer(MainActivity.this).observe(MainActivity.this,
                        new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {

                        if(aBoolean){



                        }else {

                        }
                    }
                });


                viewModel.getLocalUsers(MainActivity.this).observe(MainActivity.this,
                        new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        if(users==null){
                            Toast.makeText(MainActivity.this, "NULL", Toast.LENGTH_SHORT).show();
                        }else {
                            viewModel.postUsers(MainActivity.this,users).observe(MainActivity.this, new Observer<String>() {
                                @Override
                                public void onChanged(String s) {

                                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                                }
                            });

                            lv.setAdapter(null);

                            for(User user : users){
                                list.add(user.getBody());
                            }
                            lv.setAdapter(arrayAdapter);
                        }
                    }
                });

            }
        });
        commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewModel.getRemoteComments(MainActivity.this).observe(MainActivity.this, new Observer<List<Comment>>() {
                    @Override
                    public void onChanged(List<Comment> comments) {
                        if(comments==null){
                            Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                            lv.setAdapter(null);
                        }else
                        {


                            lv.setAdapter(null);
                            List<String> list1 = new ArrayList<>();
                            for(Comment comment  : comments){
                                list1.add(comment.toString());
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                                   0,list1 );
                            lv.setAdapter(arrayAdapter);

                        }
                    }
                });

            }
        });

        viewModel.deleteUser(MainActivity.this,1);


    }




}