package com.example.sudokuhw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "Sudoku";
    public static final String KEY_DIFFICULTY =
            "org.example.sudoku.difficulty";

    protected final String easyPuzzle =
            "360000000004230800000004200" +
                    "070460003820000014500013020" +
                    "001900000007048300000000045";

    public static final int DIFFICULTY_EASY = 10;
    protected static final int DIFFICULTY_CONTINUE = -1;

    protected static final String PREF_PUZZLE = "puzzle" ;
    protected static final String PREF_TIME= "puzzle" ;

    private GridView mGridField;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private String selectedButton = "n1";
    private Game mGame;
    public  Chronometer chronometer;
    TextView title;
    //protected long startTime;

    public static final Integer mRows = 9, mCols = 9;

    public static int numberArray[][] = new int[mRows][mCols];

    // массив незаблокированных позиций
    public static int  unblockPositions[] = new int[mRows*mCols];
    public static int helperArray[][];

    public static ArrayList<String> arrPict;
    public static long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);





        mGridField = (GridView) findViewById(R.id.field);
        mGridField.setNumColumns(9);
        mGridField.setEnabled(true);

        title = (TextView) findViewById(R.id.gameTitle);

        int diff = getIntent().getIntExtra(KEY_DIFFICULTY,
                DIFFICULTY_EASY);

        mGame = new Game(this, diff);

        String puz;
        puz = getPreferences(MODE_PRIVATE).getString(PREF_PUZZLE,
                easyPuzzle);

        numberArray = Game.fromPuzzleString(puz);

        mGame.createField(diff);

        mGridField.setAdapter(mGame);



        mGridField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mGame.setNumber(position, selectedButton);

                if(mGame.checkWinner()) showWinnerDialod();
            }
        });

        //mGame = (Game)getLastNonConfigurationInstance();

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        startTime = SystemClock.elapsedRealtime();
        chronometer.setBase(startTime);
        chronometer.start();

        b1 = (Button) findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.btn2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.btn3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.btn4);
        b4.setOnClickListener(this);
        b5 = (Button) findViewById(R.id.btn5);
        b5.setOnClickListener(this);
        b6 = (Button) findViewById(R.id.btn6);
        b6.setOnClickListener(this);
        b7 = (Button) findViewById(R.id.btn7);
        b7.setOnClickListener(this);
        b8 = (Button) findViewById(R.id.btn8);
        b8.setOnClickListener(this);
        b9 = (Button) findViewById(R.id.btn9);
        b9.setOnClickListener(this);

        Typeface type = Typeface.createFromAsset(getAssets(), "font1.otf");
        title.setTypeface(type);
        chronometer.setTypeface(type);
        chronometer.setTextSize(35);

        if (diff == DIFFICULTY_CONTINUE)
            chronometer.setBase(Long.parseLong(PREF_TIME));

        // If the activity is restarted, do a continue next time
        getIntent().putExtra(KEY_DIFFICULTY, DIFFICULTY_CONTINUE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Sudoku", "onPause");

        // Save the current numbers
        getPreferences(MODE_PRIVATE).edit().putString(PREF_PUZZLE,
                Game.toPuzzleString(numberArray)).commit();

        getPreferences(MODE_PRIVATE).edit().putString(PREF_TIME,
                chronometer.getText().toString()).commit();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();

        getPreferences(MODE_PRIVATE).edit().putString(PREF_PUZZLE,
                Game.toPuzzleString(numberArray)).commit();
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
