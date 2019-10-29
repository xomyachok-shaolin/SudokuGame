package com.example.sudokuhw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        aboutBtn = (Button) findViewById(R.id.btnAbout);
        exitBtn = (Button) findViewById(R.id.btnExit);
        newGameBtn = (Button) findViewById(R.id.btnNewGame);
        continueBtn = (Button) findViewById(R.id.btnContinue);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        title = (TextView) findViewById(R.id.title);

        setFonts();
    }

    public void continueGame(View view) {

    }
    public void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
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
