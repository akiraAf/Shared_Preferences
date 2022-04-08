package com.app.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTimer;
    private TextView textViewScore;
    private TextView textViewQuestion;
    private TextView textViewOption0;
    private TextView textViewOption1;
    private TextView textViewOption2;
    private TextView textViewOption3;
    private String question;
    private int rightAnswer;
    private int option;
    private int score = 0;
    private ArrayList<TextView> textViews;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.timer);
        textViewScore = findViewById(R.id.score);
        textViewQuestion = findViewById(R.id.question);
        textViewOption0 = findViewById(R.id.option0);
        textViewOption1 = findViewById(R.id.option1);
        textViewOption2 = findViewById(R.id.option2);
        textViewOption3 = findViewById(R.id.option3);
        textViews = new ArrayList<>();
        textViews.add(textViewOption0);
        textViews.add(textViewOption1);
        textViews.add(textViewOption2);
        textViews.add(textViewOption3);
        setRandom();
        timer();
        textViewScore.setText("Score: 0 ");

    }


    @SuppressLint("SetTextI18n")
    private void setRandom(){
        int a, b, plusOrMinus;
        a = (int) (Math.random() * 100);
        b = (int) (Math.random() * 100);

        option = (int) (Math.random() * 4);

        plusOrMinus = (int)(Math.random() * 2);

        if(plusOrMinus == 1){
            rightAnswer = a + b;
            question = Integer.toString(a) + " + " + Integer.toString(b);
        }

        else {
            rightAnswer = a - b;
            question = Integer.toString(a) + " - " + Integer.toString(b);
        }

        textViewQuestion.setText(question);

        for (int i = 0; i < 4; i++)
            textViews.get(i).setText(Integer.toString((int) (Math.random() * 100)));

        textViews.get(option).setText(Integer.toString(rightAnswer));
    }

    private void timer(){
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(getTime(millisUntilFinished));
                if(millisUntilFinished < 10000){
                    textViewTimer.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("0");
                Toast.makeText(MainActivity.this, "Таймер закончен!", Toast.LENGTH_SHORT).show();
                SharedPreferences topScore =   PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int max = topScore.getInt("Top", 0);

                if(max <= score) {
                    topScore.edit().putInt("Top", score).apply();
                    max = topScore.getInt("Top", 0);
                }
                Intent intent = new Intent(getApplicationContext(), ScoreView.class);
                intent.putExtra("Score", score);
                startActivity(intent);
            }
        };
        countDownTimer.start();
    }

    private String getTime(long millies){
        int second, minute;
        second = (int) (millies / 1000);
        minute = second / 60;
        second = second % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minute, second);
    }
    @SuppressLint("SetTextI18n")
    public void onClickAnswer0(View view) {
        if (textViewOption0.getText().toString().equals(Integer.toString(rightAnswer))){
            ++score;
        }
        textViewScore.setText( "Score: " + Integer.toString(score));
        setRandom();
    }

    @SuppressLint("SetTextI18n")
    public void onClickAnswer3(View view) {
        if (textViewOption3.getText().toString().equals(Integer.toString(rightAnswer))){
            ++score;
        }
        textViewScore.setText( "Score: " + Integer.toString(score));
        setRandom();
    }

    @SuppressLint("SetTextI18n")
    public void onClickAnswer2(View view) {
        if (textViewOption2.getText().toString().equals(Integer.toString(rightAnswer))){
            ++score;

        }
        textViewScore.setText( "Score: " + Integer.toString(score));
        setRandom();
    }
    @SuppressLint("SetTextI18n")
    public void onClickAnswer1(View view) {
        if (textViewOption1.getText().toString().equals(Integer.toString(rightAnswer))){
            ++score;
        }
        textViewScore.setText( "Score: " + Integer.toString(score));
        setRandom();
    }
}