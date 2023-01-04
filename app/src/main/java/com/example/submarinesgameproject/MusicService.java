package com.example.submarinesgameproject;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener
{

    private static MediaPlayer mediaPlayer;
    private ArrayList<Song> valuesList;
    private int songPos, positionPausedInSong;
    private final IBinder musicBind = new MusicBinder();
    private boolean isStopped;
    private Intent playIntent;

    public void onCreate()
    {
        super.onCreate();
        songPos=0;
        mediaPlayer=new MediaPlayer();
        valuesList=new ArrayList<>();

        mediaPlayer = MediaPlayer.create(this, R.raw.topgunanthem);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void pauseMusic()
    {
        mediaPlayer.pause();
        isStopped=true;
        positionPausedInSong= mediaPlayer.getCurrentPosition();
    }

    public void getSongs()
    {
        ContentResolver cr= getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songs = cr.query(songUri,null,null,null,null);
        if(songs != null && songs.moveToFirst())
        {
            int songTitle = songs.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songID = songs.getColumnIndex(MediaStore.Audio.Media._ID);

            Song song;

            while (songs.moveToNext())
            {
                long longSongID =songs.getLong(songID);
                String currentTitle = songs.getString(songTitle);
                song = new Song (longSongID, currentTitle);
                valuesList.add(song);
            }
        }
    }

    public void setSongPos(int pos)
    {this.songPos=pos;}

    public IBinder onBind(Intent intent)
    {return musicBind;}

    public boolean onUnBind(Intent intent)
    {return false;}

    public static void stopPlayMusic()
    {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public ArrayList<Song> getValuesList()
    {return this.valuesList;}

    public void playSong()
    {
        if(mediaPlayer != null)
        {mediaPlayer.reset();}

        Song songToPlay = valuesList.get(songPos);
        long songID = songToPlay.getID();

        Uri trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,songID);

        try
        {
            mediaPlayer.setDataSource(getApplicationContext(),trackUri);
        }
        catch (Exception e)
        {
            Log.e("MUSIC DERVICE", "Error setting data source",e);
        }

        mediaPlayer.prepareAsync();

        mediaPlayer.setOnPreparedListener(((mediaPlayer1) ->
        {
            mediaPlayer1.setLooping(true);
            mediaPlayer1.start();
        } ));
        isStopped=false;
    }

    public void setList(ArrayList<Song> theSongs)
    {
        valuesList = theSongs;
    }

    public class MusicBinder extends Binder implements IBinder
    {
        MusicService getService(){return  MusicService.this;}
    }

    public void setSong(int songIndex)
    {
        songPos=songIndex;
    }

    public void pause()
    {
        if(mediaPlayer!= null)
            {mediaPlayer.pause();}
    }

    public boolean isPlaying()
    {
        return mediaPlayer.isPlaying();
    }

    public void resume()
    {
        if(mediaPlayer != null)
        {mediaPlayer.start();}
    }

    @Override
    public void onPrepared(MediaPlayer mp)
    {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {

    }

    @Override
    public boolean onError(MediaPlayer mp,int what, int extra)
    {
        return false;
    }

}
