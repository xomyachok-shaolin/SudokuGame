package com.example.sudokuhw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.sudokuhw.GameActivity.KEY_DIFFICULTY;

public class MainActivity extends AppCompatActivity {

    Button aboutBtn;
    Button exitBtn;
    Button newGameBtn;
    Button continueBtn;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutBtn = findViewById(R.id.btnAbout);
        exitBtn = findViewById(R.id.btnExit);
        newGameBtn = findViewById(R.id.btnNewGame);
        continueBtn = findViewById(R.id.btnContinue);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(GameActivity.DIFFICULTY_EASY);
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueGame();
            }
        });

        title = findViewById(R.id.title);

        setFonts();
    }

    public void continueGame() {
        startGame(GameActivity.DIFFICULTY_CONTINUE);
    }
    private void startGame(int i) {
        Log.d("Sudoku", "clicked on " + i);
        Intent intent = new Intent(this, GameActivity.class);
        KEY_DIFFICULTY = i;
        startActivity(intent);

    }
    public void aboutGame(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
    public void exitGame(View view) {
        finish();
    }
    private void setFonts() {
        Typeface type = Typeface.createFromAsset(getAssets(), "font1.otf");

        title.setTypeface(type);
        aboutBtn.setTypeface(type);
        exitBtn.setTypeface(type);
        newGameBtn.setTypeface(type);
        continueBtn.setTypeface(type);
    }
}
