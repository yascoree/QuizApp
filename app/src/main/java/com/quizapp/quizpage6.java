package com.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class quizpage6 extends AppCompatActivity {
    Button bLogout, bTry;
    ProgressBar progressBar;
    TextView tvScore, tvFeedback;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage6);

        tvScore = findViewById(R.id.tvScore);
        tvFeedback = findViewById(R.id.tvFeedback);
        progressBar = findViewById(R.id.progressBar);
        bLogout = findViewById(R.id.bLogout);
        bTry = findViewById(R.id.bTry);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        int percentage = (score * 100) / 5;
        progressBar.setProgress(percentage);
        tvScore.setText(percentage + "%");

        if (percentage >= 80) {
            tvFeedback.setText("Excellent Job!");
        } else if (percentage >= 50) {
            tvFeedback.setText("Good Effort!");
        } else {
            tvFeedback.setText("Keep Practicing!");
        }

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Merci de votre Participation !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(quizpage6.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        bTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(quizpage6.this, quizpage1.class));
                finish();
            }
        });
    }
}