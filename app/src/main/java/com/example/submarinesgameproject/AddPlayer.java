package com.example.submarinesgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddPlayerPlay,btnBackToHome, btnContact1;
    private  Intent intent;
    private RadioButton radioBtn4Subs,radioBtn7Subs;
    private EditText etPlayer1;
    private TextView tvPlayer2; //computer
    private String player1,player2;
    private TextToSpeech textToSpeech;
    private static final int RESULTPICK=1;
    private int playerNum;

    private int size=9; //גודל הלוח

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);


        btnAddPlayerPlay = (Button) findViewById(R.id.btnAddPlayerPlay);
        btnAddPlayerPlay.setOnClickListener(this);


        btnBackToHome= (Button) findViewById(R.id.btnBackToHome);
        btnBackToHome.setOnClickListener(this);

        btnContact1=(Button) findViewById(R.id.btnContact1);
        btnContact1.setOnClickListener(this);

    //    btnContact2=(Button) findViewById(R.id.btnContact2);
     //   btnContact2.setOnClickListener(this);

        etPlayer1=(EditText) findViewById(R.id.etPlayer1);
        tvPlayer2 = (TextView) findViewById(R.id.tvPlayer2);

        tvPlayer2.setText("Pola");

        radioBtn4Subs=(RadioButton) findViewById(R.id.radioBtn4Subs);
        radioBtn4Subs.setOnClickListener(this);

        radioBtn7Subs=(RadioButton) findViewById(R.id.radioBtn7Subs);
        radioBtn7Subs.setOnClickListener(this);

        textToSpeech = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i){
                if(i== TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }


    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {
            Uri uri = data.getData();

            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int phoneIndexName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            String phoneName = cursor.getString(phoneIndexName);


            etPlayer1.setText(phoneName);

        }
    }



    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnAddPlayerPlay:
                intent=new Intent(this, MyBoardArrangement.class);

                String name1 = etPlayer1.getText().toString().trim();


                String name2 = tvPlayer2.getText().toString().trim();
                int subNum;
                if(radioBtn4Subs.isChecked())
                    subNum=4;
                else
                    subNum=7;
                if (name1.length() ==0 || name1.equals(name2)){
                    new AlertDialog.Builder(this)
                            .setTitle("ERORR")
                            .setMessage("not a good name")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setIcon(R.drawable.error)
                            .show();
                }
                else{


                    textToSpeech.speak("Enjoy and Good Luck! " + name1 , TextToSpeech.QUEUE_FLUSH, null);


                    intent.putExtra("DATA1",name1);

                    intent.putExtra("SUBNUM",subNum);

                    startActivity(intent);
                }

                break;
            case R.id.btnBackToHome:
                 finish();
                 break;
            case R.id.btnContact1:
                this.playerNum = 1;
                 intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, RESULTPICK);
                break;
        }



    }

}