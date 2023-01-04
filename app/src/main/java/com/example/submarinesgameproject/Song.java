package com.example.submarinesgameproject;

public class Song {

    private long ID;
    private String title;

    public Song (long ID, String title)
    {
        this.ID=ID;
        this.title=title;
    }

    public long getID()
    {return this.ID;}

    public String getTitle()
    {return this.title;}

    public String toString()
    {return  (this.ID + ", " + this.title);}
}
