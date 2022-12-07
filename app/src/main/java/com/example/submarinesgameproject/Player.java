package com.example.submarinesgameproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private String name; //שם השחקן
    private int score;   //ניקוד השחקן
    private int[][] myMat;  //הלוח של השחקן
    private int count;
    private  int [] mySubs;     //מערך שמכיל את כל הצוללות של השחקן שנותרו בחיים

    public Player(String name, int subNum, int size)
    {
        //
        this.name = name;
        this.score = 0;
        this.mySubs=new int[subNum];
        this.myMat=new int[size][size];
        restartGame();

    }

    public void restartGame()
    {
        //
        for (int i=0;i<this.myMat.length;i++)
        {
            for (int j=0;j<this.myMat[i].length;j++)
            {
                this.myMat[i][j]=0;
            }
        }
    }



}
