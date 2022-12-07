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

import java.util.Locale;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddPlayerPlay,btnBackToHome, btnContact1,btnContact2;
    private RadioButton radioBtn4Subs,radioBtn7Subs;
    private EditText etPlayer1,etPlayer2;
    private String player1,player2;
    private TextToSpeech textToSpeech;
    private static final int RESULTPICK=1;
    private int playerNum;

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

        btnContact2=(Button) findViewById(R.id.btnContact2);
        btnContact2.setOnClickListener(this);

        etPlayer1=(EditText) findViewById(R.id.etPlayer1);
        etPlayer2 = (EditText) findViewById(R.id.etPlayer2);

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

            if(this.playerNum==1)
                etPlayer1.setText(phoneName);
            else
                etPlayer2.setText(phoneName);
        }
    }



    @Override
    public void onClick(View view) {

        if(view.getId()==btnAddPlayerPlay.getId())
        {
            Intent intent=new Intent(this, MyBoardArrangement.class);

            String name1 = etPlayer1.getText().toString();
            String name2 =etPlayer2.getText().toString();
            int subNum;
            if(radioBtn4Subs.isChecked())
                subNum=4;
            else
                subNum=7;
            if (name1.length() ==0 || name2.length()==0|| name1.equals(name2)){
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


                textToSpeech.speak("Enjoy and Good Luck! " + name1 + " and " + name2, TextToSpeech.QUEUE_FLUSH, null);


                intent.putExtra("DATA1",name1);
                intent.putExtra("DATA2", name2);
                intent.putExtra("subNum",subNum);

                startActivity(intent);
            }
        }


        if(view.getId()==btnBackToHome.getId())
            {
            finish();
            }

        switch (view.getId())
        {
            case R.id.btnContact1:
                this.playerNum = 1;
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, RESULTPICK);
                break;
            case R.id.btnContact2:
                this.playerNum = 2;
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, RESULTPICK);
                break;
        }


    }

}