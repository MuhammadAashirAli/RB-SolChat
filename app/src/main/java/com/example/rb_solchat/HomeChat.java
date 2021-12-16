package com.example.rb_solchat;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeChat extends AppCompatActivity {

    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chat);


        // get the reference of RecyclerView for ONLINE USERS
        RecyclerView onlineUsersRecyclerView = (RecyclerView) findViewById(R.id.onlineUsers);
        // set a LinearLayoutManager with horizontal orientation
        LinearLayoutManager onlineUsersLayoutManager = new LinearLayoutManager(getApplicationContext());
        onlineUsersLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        onlineUsersRecyclerView.setLayoutManager(onlineUsersLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
//        CustomAdapter onlineUserAdapter = new CustomAdapter(HomeChat.this, personNames);
//        onlineUsersRecyclerView.setAdapter(onlineUserAdapter);


    }
}
