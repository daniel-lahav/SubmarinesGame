package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyBoardArrangement extends AppCompatActivity implements View.OnClickListener {
    private Button btnMyBoardPlay, btnBackToAddPlayer;
    private TextView myBoardTitle;
    private ImageButton[][] myBoard;
    private String name1, name2;
    private int subNum;

    public Game myBoardActivity;

    private int subSize = 4;
    private int tempi, tempj;
    private int clickCount;


    private Player player1;

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

            subNum = (int) xtras.getInt("SUBNUM");

        }
        player1 = new Player(name1,subNum, UpClass.Size);
        myBoardTitle.setText(name1 + "'s Board");

        myBoard = new ImageButton[UpClass.Size][UpClass.Size];
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

                intent.putExtra("SUBNUM", subNum);

                startActivity(intent);
                    this.finish();
                break;
            case R.id.btnBackToAddPlayer:
                finish();
                break;
            default:
                if (subSize > 0) {
                    if (clickCount == 0) {
                        for (int i = 0; i < myBoard.length; i++) {
                            for (int j = 0; j < myBoard.length; j++) {

                                if (view.getId() == myBoard[i][j].getId()) {
                                    tempi = i;
                                    tempj = j;
                                    if (UpClass.canPlaceHorizontal(player1.getPlayerBoard(),i, j, subSize)) {
                                        myBoard[i][j].setImageResource(R.drawable.yellowsquare);
                                        clickCount = 1;
                                        myBoard[i][j + subSize-1].setImageResource(R.drawable.yellowsquare);

                                    }

                                    if (UpClass.canPlaceVertical(player1.getPlayerBoard(),i, j, subSize)) {
                                        myBoard[i][j].setImageResource(R.drawable.yellowsquare);
                                        clickCount = 1;
                                        myBoard[i + subSize-1][j].setImageResource(R.drawable.yellowsquare);

                                    }
                                }
                            }
                        }
                    } else {
                        if (tempj + subSize-1 <9 && view.getId() == myBoard[tempi][tempj + subSize-1].getId()) {
                            for (int h = 0; h < subSize; h++) {
                                myBoard[tempi][tempj + h].setImageResource(R.drawable.greensquare);
                            }
                            UpClass.placeSub(player1.getPlayerBoard(),(new Position(tempi, tempj, false)), subSize);
                            if ((tempi + subSize-1) < 9 && player1.getNum(tempi+subSize-1, tempj)<=0)
                            {
                                myBoard[tempi + subSize-1][tempj].setImageResource(R.drawable.emptysubmarinesquare);
                            }
                            subSize--;
                            clickCount = 0;

                        }
                        if (tempi + subSize-1 < 9 && view.getId() == myBoard[tempi + subSize-1][tempj].getId()) {
                            for (int h = 0; h < subSize; h++) {
                                myBoard[tempi + h][tempj].setImageResource(R.drawable.greensquare);
                            }
                            UpClass.placeSub(player1.getPlayerBoard(),new Position(tempi, tempj, true), subSize );
                            if ((tempj + subSize-1) < 9 && player1.getNum(tempi, tempj + subSize-1)<=0)
                            {
                                myBoard[tempi][tempj + subSize-1].setImageResource(R.drawable.emptysubmarinesquare);
                            }
                            subSize--;
                            clickCount = 0;

                        }
//                       Toast.makeText(getApplicationContext(),playerManager.show().toString(),Toast.LENGTH_LONG).show();

                    }


                }
                break;
        }
    }
}


