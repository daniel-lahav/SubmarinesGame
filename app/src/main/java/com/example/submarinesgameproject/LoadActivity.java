package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Handler handler=new Handler();
        Intent intent=new Intent(this,HomeActivity.class);


        handler.postDelayed(()->startActivity(intent), 3000);
    }
}