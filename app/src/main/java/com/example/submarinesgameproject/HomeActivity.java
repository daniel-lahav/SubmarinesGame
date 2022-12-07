package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlay,btnMusic,btnSettings,btnLeaderboard,btnQuit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlay=(Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnMusic=(Button) findViewById(R.id.btnMusic);
        btnMusic.setOnClickListener(this);

        btnSettings=(Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(this);

        btnLeaderboard=(Button) findViewById(R.id.btnLeaderboard);
        btnLeaderboard.setOnClickListener(this);

        btnQuit=(Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==btnPlay.getId())
        {
            Intent intent =new Intent(this,AddPlayer.class);

            startActivity(intent);
        }


        if(view.getId()==btnQuit.getId())
        {
            finish();
            System.exit(0);
        }

    }
}