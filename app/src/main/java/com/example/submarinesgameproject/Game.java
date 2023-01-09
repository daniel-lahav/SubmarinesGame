package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private Button btnBackToMyBoard, btnRestart, btnMyBoard, btnDialogBack;
    private TextView tvPlayer1, tvPlayer2, tvScore1, tvScore2;
    private String name1, name2;
    private ImageButton[][] board;
    private int[][] playerBoard;
    private ImageView[][] popupMat;
    private GameManager gManager,playerManager;
    public Game gameActivity;
    private int subNum;
    private Player player1;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameManager playerManager =new GameManager(gameActivity,subNum);
        Intent in = getIntent();
        if (in != null) {
            Bundle xtras = in.getExtras();

            name1 = xtras.getString("DATA1");
            name2 = xtras.getString("DATA2");
            subNum = (int) xtras.getInt("subNum");
            playerManager= (GameManager) xtras.get("PlayerManager");
        }


        playerManager.setSubNum(subNum);
        Toast.makeText(getApplicationContext(),playerManager.show(),Toast.LENGTH_LONG).show();

        btnBackToMyBoard = (Button) findViewById(R.id.btnBackToMyBoard);
        btnBackToMyBoard.setOnClickListener(this);

        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(this);

        btnMyBoard = (Button) findViewById(R.id.btnMyBoard);
        btnMyBoard.setOnClickListener(this);

        tvPlayer1 = (TextView) findViewById(R.id.tvPlayer1);
        tvPlayer2 = (TextView) findViewById(R.id.tvPlayer2);
        tvScore1 = (TextView) findViewById(R.id.tvScore1);
        tvScore2 = (TextView) findViewById(R.id.tvScore2);

        gManager = new GameManager(this, subNum);
        gManager.randomBoard();

        board = new ImageButton[gManager.getSize()][gManager.getSize()];

        popupMat= new ImageView[gManager.getSize()][gManager.getSize()];

        tvPlayer1.setText(name1 + " (you)");
        tvPlayer2.setText(name2 + " (opponent)");
        player1 = new Player(name1, subNum, gManager.getSize());
        tvScore1.setText("0");
        tvScore2.setText("0");

        String str = "";
        int resId;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                str = "btn" + i + j;
                resId = getResources().getIdentifier(str, "id", getPackageName());
                board[i][j] = (ImageButton) findViewById(resId);
                board[i][j].setOnClickListener(this);
            }
        }


//        str = "";
//        resId = 0;
//        for (int i = 0; i < playerBoard.length; i++) {
//            for (int j = 0; j < playerBoard.length; j++) {
//                str = "img" + i + j;
//                resId = getResources().getIdentifier(str, "id", getPackageName());
//                popupMat[i][j] = (ImageView) findViewById(resId);
//
//            }
//        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnBackToMyBoard.getId()) {
            finish();

        }

        if (view.getId() == btnRestart.getId()) {
            for (int i = 0; i < board.length; i++) {

                for (int j = 0; j < board.length; j++) {
                    board[i][j].setImageResource(R.drawable.emptysubmarinesquare);
                    board[i][j].setClickable(true);
                    gManager.randomBoard();
                }
            }
        }

//        if(view.getId()==btnMyBoard.getId())
//        {
//            createPopup();
//        }

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board.length; j++) {

                if (view.getId() == board[i][j].getId()) {

                    if (gManager.getNum(i, j) > 0) {
                        board[i][j].setImageResource(R.drawable.hitsubmarinesquare);
                        Toast.makeText(getApplicationContext(), " HIT!!! ", Toast.LENGTH_SHORT).show();
                    } else {
                        board[i][j].setImageResource(R.drawable.misssubmarinesquare);
                        Toast.makeText(getApplicationContext(), "MISS :( ", Toast.LENGTH_SHORT).show();
                    }

                    board[i][j].setClickable(false);
                }
            }
        }

    }


//    public void createPopup()
//    {
//        dialog = new Dialog(this);
//        dialog.setContentView(R.layout.custom_layout);
//
//        dialog.setCancelable(true);
//
//        btnDialogBack = (Button) dialog.findViewById(R.id.btnDialogBack);
//        btnDialogBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                dialog.cancel();
//            }
//        });
//
//        for(int i=0; i<playerBoard.length;i++)
//        {
//            for(int j=0; j<playerBoard.length; j++)
//            {
//                if(playerBoard[i][j]>0)
//                {
//                    popupMat[i][j].setImageResource(R.drawable.greensquare);
//                }
//            }
//        }
//
//        dialog.show();
//  }
}