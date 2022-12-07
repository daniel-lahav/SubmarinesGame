package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MyBoardArrangement extends AppCompatActivity implements View.OnClickListener {
    private Button btnMyBoardPlay, btnBackToAddPlayer;
    private TextView myBoardTitle;
    private ImageButton[][] myBoard;
    private String name1, name2;
    private int subNum;
    private GameManager playerManager;
    public Game myBoardActivity;

    private int subSize = 3;
    private int tempi, tempj;
    private int clickCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_board_arrangement);


        this.clickCount = 0;
        btnMyBoardPlay = (Button) findViewById(R.id.btnMyBoardPlay);
        btnMyBoardPlay.setOnClickListener(this);

        btnBackToAddPlayer = (Button) findViewById(R.id.btnBackToAddPlayer);
        btnBackToAddPlayer.setOnClickListener(this);

        myBoardTitle = (TextView) findViewById(R.id.MyBoardTitle);


        Intent in = getIntent();
        if (in != null) {
            Bundle xtras = in.getExtras();

            name1 = xtras.getString("DATA1");
            name2 = xtras.getString("DATA2");
            subNum = (int) xtras.getInt("subNum");

        }

        myBoardTitle.setText(name1 + "'s Board");
        playerManager = new GameManager(myBoardActivity, subNum);
        myBoard = new ImageButton[playerManager.getSize()][playerManager.getSize()];
        String str = "";
        int resId;
        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard.length; j++) {
                str = "btn" + i + j;
                resId = getResources().getIdentifier(str, "id", getPackageName());
                myBoard[i][j] = (ImageButton) findViewById(resId);
                myBoard[i][j].setOnClickListener(this);
            }
        }


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnMyBoardPlay:
                Intent intent = new Intent(this, Game.class);
                intent.putExtra("DATA1", name1);
                intent.putExtra("DATA2", name2);
                intent.putExtra("subNum", subNum);
                intent.putExtra("PlayerBoard", playerManager.getMat());
                startActivity(intent);
//                    this.finish();
                break;
            case R.id.btnBackToAddPlayer:
                finish();
                break;
            default:
                if (subSize >= 0) {
                    if (clickCount == 0) {
                        for (int i = 0; i < myBoard.length; i++) {
                            for (int j = 0; j < myBoard.length; j++) {

                                if (view.getId() == myBoard[i][j].getId()) {
                                    tempi = i;
                                    tempj = j;
                                    if (playerManager.canPlaceHorizontal(i, j, subSize)) {
                                        myBoard[i][j].setImageResource(R.drawable.greensquare);
                                        clickCount = 1;
                                        myBoard[i][j + subSize].setImageResource(R.drawable.greensquare);

                                    }

                                    if (playerManager.canPlaceVertical(i, j, subSize)) {
                                        myBoard[i][j].setImageResource(R.drawable.greensquare);
                                        clickCount = 1;
                                        myBoard[i + subSize][j].setImageResource(R.drawable.greensquare);

                                    }
                                }
                            }
                        }
                    } else {
                        if (view.getId() == myBoard[tempi][tempj + subSize].getId()) {
                            for (int h = 0; h < subSize; h++) {
                                myBoard[tempi][tempj + h].setImageResource(R.drawable.greensquare);
                            }
                            playerManager.placeSub(new Position(tempi, tempj, false), subSize + 1);
                            if (tempi + subSize < 9) {
                                myBoard[tempi + subSize][tempj].setImageResource(R.drawable.emptysubmarinesquare);
                            }
                            subSize--;
                            clickCount = 0;

                        }
                        if (view.getId() == myBoard[tempi + subSize][tempj].getId()) {
                            for (int h = 0; h < subSize; h++) {
                                myBoard[tempi + h][tempj].setImageResource(R.drawable.greensquare);
                            }
                            playerManager.placeSub(new Position(tempi, tempj, true), subSize + 1);
                            if (tempj + subSize < 9) {
                                myBoard[tempi][tempj + subSize].setImageResource(R.drawable.emptysubmarinesquare);
                            }
                            subSize--;
                            clickCount = 0;

                        }
//                        Toast.makeText(getApplicationContext(),playerManager.show().toString(),Toast.LENGTH_LONG).show();

                    }


                }
                break;
        }
    }
}


