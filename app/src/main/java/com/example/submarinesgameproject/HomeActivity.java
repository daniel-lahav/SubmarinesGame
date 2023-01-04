package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlay,btnMusic,btnSettings,btnLeaderboard,btnQuit;
    private boolean music;
    private Switch switchMusic;
    public static MusicService musicService;
    private Intent playIntent;

    public static boolean isPlaying = true;
    private boolean musicBound;
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








        switchMusic=(Switch) findViewById(R.id.switchMusic);
        switchMusic.setOnClickListener(this);



        musicService=new MusicService();
        musicBound=false;
        if(playIntent==null)
        {
            playIntent= new Intent(this,MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }


    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicBound = false;
        }
    };

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