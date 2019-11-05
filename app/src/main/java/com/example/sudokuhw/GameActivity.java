package com.example.sudokuhw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends Activity implements View.OnClickListener {

    private TextView title;
    private GridView mGridField;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private String selectedButton = "n1";
    private Game mGame;

    public static Chronometer chronometer;
    public static long startTime = 0;

    static final Integer mRows = 9, mCols = 9;
    public static int numberArray[][] = new int[mRows][mCols];
    // массив незаблокированных позиций
    public static int  unblockPositions[] = new int[mRows*mCols];
    public static int helperArray[][];

    public static ArrayList<String> arrPict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getUIItems();

        mGame = new Game(this);

        mGridField.setAdapter(mGame);
        mGridField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mGame.setNumber(position, selectedButton);

                if(mGame.checkWinner()) {
                    showWinnerDialod();
                    MainActivity.KEY_MODE = MainActivity.KEY_NEWGAME;
                }
            }
        });

        setFonts();

        MainActivity.KEY_MODE = MainActivity.KEY_CONTINUE;
    }

    private void getUIItems() {
        mGridField = findViewById(R.id.field);
        mGridField.setNumColumns(9);
        mGridField.setEnabled(true);

        title = findViewById(R.id.gameTitle);

        chronometer = findViewById(R.id.chronometer);

        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn7);
        b7.setOnClickListener(this);
        b8 = findViewById(R.id.btn8);
        b8.setOnClickListener(this);
        b9 = findViewById(R.id.btn9);
        b9.setOnClickListener(this);
    }

    private void setFonts() {
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
        Toast toast = Toast.makeText(getApplicationContext(),
                "Вы выбрали " + selectedButton.split("n")[1], Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showWinnerDialod() {
        final AlertDialog.Builder alertBox = new AlertDialog.Builder(this);

        String time = (String)chronometer.getText();
        chronometer.stop();

        alertBox.setTitle("Поздравляем");

        String TextToast = "Вы победили!\nВаше время: " + time;
        alertBox.setMessage(TextToast);

        alertBox.setNeutralButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alertBox.show();
    }
}
