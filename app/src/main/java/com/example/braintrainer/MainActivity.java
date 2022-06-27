package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView questionTextView;
    TextView trueRatioTextView;
    TextView aTextView;
    TextView bTextView;
    TextView cTextView;
    TextView dTextView;
    TextView warningTextView;
    TextView goTextView;
    Button resetButton;
    CountDownTimer countDownTimer;

    int trueAnswer;
    int trueAnswerCounter = 0;
    int questionCounter = 0;

    public void startGame(View view) {
        goTextView.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        trueRatioTextView.setVisibility(View.VISIBLE);
        aTextView.setVisibility(View.VISIBLE);
        bTextView.setVisibility(View.VISIBLE);
        cTextView.setVisibility(View.VISIBLE);
        dTextView.setVisibility(View.VISIBLE);
        warningTextView.setVisibility(View.VISIBLE);

        countDownTimer();
        questionGenerator();
        answerGenerator();
    }

    public void click(View view) {

        rightOrWrong((TextView) view);

    }

    public void playAgain(View view) {

        trueAnswerCounter = 0;
        questionCounter = 0;
        resetButton.setVisibility(View.INVISIBLE);
        aTextView.setClickable(true);
        bTextView.setClickable(true);
        cTextView.setClickable(true);
        dTextView.setClickable(true);
        warningTextView.setText("");
        countDownTimer();
        questionGenerator();
        answerGenerator();

    }

    public void questionGenerator() {

        Random random = new Random();

        int randomNumber = random.nextInt(49) + 1;
        int randomNumber2 = random.nextInt(49) + 1;

        questionCounter++;

        trueAnswer = randomNumber + randomNumber2;
        questionTextView.setText(randomNumber + " + " + randomNumber2);

    }

    public void answerGenerator() {
        Random random = new Random();

        int answerBox = random.nextInt(3) + 1;
        Log.i("Value", "Random = " + Integer.toString(answerBox));

        aTextView.setText(Integer.toString(random.nextInt(99) + 1));
        bTextView.setText(Integer.toString(random.nextInt(99) + 1));
        cTextView.setText(Integer.toString(random.nextInt(99) + 1));
        dTextView.setText(Integer.toString(random.nextInt(99) + 1));

        if (Integer.toString(answerBox).equals(aTextView.getTag().toString())) {

            aTextView.setText(Integer.toString(trueAnswer));
            Log.i("Value", "Tag " + aTextView.getTag());

        } else if (Integer.toString(answerBox).equals(bTextView.getTag().toString())) {

            bTextView.setText(Integer.toString(trueAnswer));
            Log.i("Value", "Tag " + bTextView.getTag());

        } else if (Integer.toString(answerBox).equals(cTextView.getTag().toString())) {

            cTextView.setText(Integer.toString(trueAnswer));
            Log.i("Value", "Tag " + cTextView.getTag());

        } else if (Integer.toString(answerBox).equals(dTextView.getTag().toString())) {

            dTextView.setText(Integer.toString(trueAnswer));
            Log.i("Value", "Tag " + dTextView.getTag());

        }

    }

    public void countDownTimer() {

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText((int) millisUntilFinished / 1000 + "s");

            }

            @Override
            public void onFinish() {

                resetButton.setVisibility(View.VISIBLE);
                aTextView.setClickable(false);
                bTextView.setClickable(false);
                cTextView.setClickable(false);
                dTextView.setClickable(false);

                warningTextView.setText("Done!");
            }
        }.start();

    }

    public void rightOrWrong(TextView textView) {
        if(textView.getText().toString().equals(Integer.toString(trueAnswer))) {
            trueAnswerCounter++;
            warningTextView.setText("Right");
            calculateTrueRatio();
            questionGenerator();
            answerGenerator();
        } else {
            warningTextView.setText("Wrong");
            calculateTrueRatio();
            questionGenerator();
            answerGenerator();
        }
    }

    public void calculateTrueRatio() {
        trueRatioTextView.setText(trueAnswerCounter + "/" + questionCounter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        trueRatioTextView = findViewById(R.id.trueRatioTextView);
        aTextView = findViewById(R.id.aTextView);
        bTextView = findViewById(R.id.bTextView);
        cTextView = findViewById(R.id.cTextView);
        dTextView = findViewById(R.id.dTextView);
        warningTextView = findViewById(R.id.warningTextView);
        resetButton = findViewById(R.id.resetButton);
        goTextView = findViewById(R.id.goTextView);

    }
}