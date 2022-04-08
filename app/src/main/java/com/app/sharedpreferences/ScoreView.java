package com.app.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreView extends AppCompatActivity {

    private TextView textViewResult;
    private TextView textViewTopResult;
    private Button buttonRestart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);

        textViewTopResult = findViewById(R.id.textViewTopResult);
        textViewResult = findViewById(R.id.textViewResult);
        buttonRestart = findViewById(R.id.buttonRestart);
        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);

        SharedPreferences scoreTop = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int topScore = scoreTop.getInt("Top", 0);
        textViewResult.setText("Ваш результат: " + Integer.toString(score));
        textViewTopResult.setText("Ваш лучший результат: " + Integer.toString(topScore));
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rest = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(rest);
            }
        });
    }
}