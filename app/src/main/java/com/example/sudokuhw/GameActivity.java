package com.example.sudokuhw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements View.OnClickListener {

    private GridView mGridField;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private String selectedButton = "n1";
    private Game mGame;
    public  Chronometer chronometer;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mGridField = (GridView) findViewById(R.id.field);
        mGridField.setNumColumns(9);
        mGridField.setEnabled(true);

        title = (TextView) findViewById(R.id.gameTitle);

        mGame = new Game(this);
        mGridField.setAdapter(mGame);

        mGridField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mGame.setNumber(position, selectedButton);

                if(mGame.checkWinner()) showWinnerDialod();
            }
        });

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        long startTime = SystemClock.elapsedRealtime();
        chronometer.setBase(startTime);

        chronometer.start();

        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b5 = (Button) findViewById(R.id.btn5);
        b6 = (Button) findViewById(R.id.btn6);
        b7 = (Button) findViewById(R.id.btn7);
        b8 = (Button) findViewById(R.id.btn8);
        b9 = (Button) findViewById(R.id.btn9);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        Typeface type = Typeface.createFromAsset(getAssets(), "font1.otf");
        title.setTypeface(type);
        chronometer.setTypeface(type);
        chronometer.setTextSize(35);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1: selectedButton = "n1";
                break;
            case R.id.btn2: selectedButton = "n2";
                break;
            case R.id.btn3: selectedButton = "n3";
                break;
            case R.id.btn4: selectedButton = "n4";
                break;
            case R.id.btn5: selectedButton = "n5";
                break;
            case R.id.btn6: selectedButton = "n6";
                break;
            case R.id.btn7: selectedButton = "n7";
                break;
            case R.id.btn8: selectedButton = "n8";
                break;
            case R.id.btn9: selectedButton = "n9";
                break;
        }
    }

    private void showWinnerDialod() {
        final AlertDialog.Builder alertBox = new AlertDialog.Builder(this);

        String time = (String)chronometer.getText();
        chronometer.stop();

        alertBox.setTitle("Congratulations");

        String TextToast = "YOU ARE WIN\nYour time: " + time;
        alertBox.setMessage(TextToast);

        alertBox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alertBox.show();
    }
}
