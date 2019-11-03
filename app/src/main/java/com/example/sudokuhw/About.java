package com.example.sudokuhw;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView title;
    TextView inf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getUIItems();
        setFonts();
    }

    private void getUIItems() {
        title = findViewById(R.id.abooutTitle);
        inf = findViewById(R.id.aboutTV);
    }
    private void setFonts() {
        Typeface type = Typeface.createFromAsset(getAssets(), "font1.otf");
        title.setTypeface(type);
        inf.setTypeface(type);
    }
}
