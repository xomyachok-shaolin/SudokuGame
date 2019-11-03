package com.example.sudokuhw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int KEY_NEWGAME = 1;
    static final int KEY_CONTINUE = 2;
    static int KEY_MODE = KEY_NEWGAME;

    Button aboutBtn;
    Button exitBtn;
    Button newGameBtn;
    Button continueBtn;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUIItems();

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(KEY_NEWGAME);
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueGame();
            }
        });



        setFonts();
    }

    private void getUIItems() {
        aboutBtn = findViewById(R.id.btnAbout);
        exitBtn = findViewById(R.id.btnExit);
        newGameBtn = findViewById(R.id.btnNewGame);
        continueBtn = findViewById(R.id.btnContinue);

        title = findViewById(R.id.title);
    }

    public void continueGame() {
        startGame(KEY_CONTINUE);
    }
    private void startGame(int i) {
        Intent intent = new Intent(this, GameActivity.class);
        KEY_MODE = i;
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
