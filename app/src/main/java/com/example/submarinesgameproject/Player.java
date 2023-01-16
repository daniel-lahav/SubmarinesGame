package com.example.submarinesgameproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private String name; //שם השחקן
    private int score;   //ניקוד השחקן
    private int[][] playerBoard;  //הלוח של השחקן
    private int count;
    private  int [] playerSubs;     //מערך שמכיל את כל הצוללות של השחקן שנותרו בחיים

    public Player(String name, int subNum, int size)
    {
        //
        this.name = name;
        this.score = 0;
        this.playerSubs=new int[subNum];
        this.playerBoard=new int[size][size];
        restartGame();

    }

    public void restartGame()
    {
        //
        for (int i=0;i<this.playerBoard.length;i++)
        {
            for (int j=0;j<this.playerBoard[i].length;j++)
            {
                this.playerBoard[i][j]=0;
            }
        }
    }

    public int[][] getPlayerBoard()
    {
        return  this.playerBoard;
    }

    public int getNum(int i,int j)
    {
        return this.playerBoard[i][j];
    }



}
