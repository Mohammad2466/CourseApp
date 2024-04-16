package com.example.courseapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvTimer, tvScore, tvQuestions;
    private Button btnScore, btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private CountDownTimer countDownTimer;
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        // Initialize views
        tvTimer = findViewById(R.id.tv_timer);
        tvScore = findViewById(R.id.tv_score);
        tvQuestions = findViewById(R.id.tv_questions);
        btnScore = findViewById(R.id.btn_score);
        btnAnswer1 = findViewById(R.id.btn_answer1);
        btnAnswer2 = findViewById(R.id.btn_answer2);
        btnAnswer3 = findViewById(R.id.btn_answer3);
        btnAnswer4 = findViewById(R.id.btn_answer4);

        // Initialize Quiz
        quiz = new Quiz();

        // Set onClickListener for answer buttons
        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(quiz.checkAnswer(getAnswerForButton(btnAnswer1)));
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(quiz.checkAnswer(getAnswerForButton(btnAnswer2)));
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(quiz.checkAnswer(getAnswerForButton(btnAnswer3)));
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(quiz.checkAnswer(getAnswerForButton(btnAnswer4)));
            }
        });

        // Start the quiz session
        startQuiz();
    }

    private void startQuiz() {
        // Start the timer
        startTimer();

        // Display the first question
        displayQuestion();
    }

    private void displayQuestion() {
        // Generate a new question
        quiz.makeQuestion();

        // Get the current question
        Question currentQuestion = quiz.getCurrentQuestions();

        // Display the question in the tv_questions TextView
        tvQuestions.setText(currentQuestion.getQuestionPhrases());

        // Set answer choices for buttons
        btnAnswer1.setText(String.valueOf(currentQuestion.getAnswerArray()[0]));
        btnAnswer2.setText(String.valueOf(currentQuestion.getAnswerArray()[1]));
        btnAnswer3.setText(String.valueOf(currentQuestion.getAnswerArray()[2]));
        btnAnswer4.setText(String.valueOf(currentQuestion.getAnswerArray()[3]));
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) { // Timer for 60 seconds
            @Override
            public void onTick(long millisUntilFinished) {
                // Update timer TextView with remaining time
                tvTimer.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Handle timer finish event (e.g., end of quiz)
                // You can add your logic here, such as showing a message or ending the quiz
                tvTimer.setText("Time's up!");
                // For example, you can call a method to end the quiz
                endQuiz();
            }
        }.start();
    }

    private void endQuiz() {
        // Calculate final score or perform any necessary cleanup
        int finalScore = calculateScore();

        // Navigate to the ScoreActivity and pass the final score as an extra
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("FINAL_SCORE", finalScore);
        startActivity(intent);
    }


    private int calculateScore() {
        int score = 0;
        for (Question question : quiz.getQuestions()) {
            if (question.isAnsweredCorrectly()) {
                score += 10; // Add 10 points for each correct answer
            } else {
                score -= 5; // Deduct 5 points for each incorrect answer
            }
        }
        return score;
    }
    private void handleAnswerClick(boolean isCorrect) {
        if (isCorrect) {
            // Implement logic for correct answer
            Toast.makeText(MainActivity2.this, "Correct!", Toast.LENGTH_SHORT).show();
            // Assuming you have a method to mark the question as answered correctly
            quiz.getCurrentQuestions().setAnsweredCorrectly(true);
        } else {
            // Implement logic for incorrect answer
            Toast.makeText(MainActivity2.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            // Assuming you have a method to mark the question as answered incorrectly
            quiz.getCurrentQuestions().setAnsweredCorrectly(false);
        }

        // Update the score TextView
        tvScore.setText("Score: " + quiz.getScore());

        // Display the next question
        displayQuestion();
    }


    private int getAnswerForButton(Button button) {
        return Integer.parseInt(button.getText().toString());
    }
}