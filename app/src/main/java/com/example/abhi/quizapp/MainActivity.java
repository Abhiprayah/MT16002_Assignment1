package com.example.abhi.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TRUE = "True";
    private static final String FALSE = "False";
    private static final String QUIZ = "Quiz";
    Quiz quiz;
    private TextView questionTextView;
    private static final String TAG = "QuizApp";

    private View.OnClickListener nextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            questionTextView.setText(quiz.generateQuestion());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            quiz = new MathQuiz();
        }
        else{
            quiz = (MathQuiz)savedInstanceState.getSerializable("Quiz");
        }

        Button trueButton;
        Button falseButton;
        Button nextButton;
        trueButton = (Button)findViewById(R.id.trueButton);
        falseButton = (Button)findViewById(R.id.falseButton);
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(nextButtonListener);
        trueButton.setOnClickListener(new MyAnswerListener(TRUE,quiz,getApplicationContext()));
        falseButton.setOnClickListener(new MyAnswerListener(FALSE,quiz,getApplicationContext()));

        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");

        savedInstanceState.putSerializable(QUIZ,(MathQuiz)quiz);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
        questionTextView = (TextView)findViewById(R.id.Question);
        questionTextView.setText(quiz.generateQuestion());
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnREsume");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnSTop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}