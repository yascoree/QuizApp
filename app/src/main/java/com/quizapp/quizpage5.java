package com.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class quizpage5 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    ImageView ivQuestion;
    int score;
    String RepCorrect = "Non";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage5);

        ivQuestion = findViewById(R.id.ivQuestion);
        rg = findViewById(R.id.rg);
        bNext = findViewById(R.id.bNext);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        // Load image from Supabase Storage
        String imageUrl = SupabaseClient.getImageUrl("quiz-images", "q5.jpg");
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.q5)
                .into(ivQuestion);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Merci de choisir une réponse S.V.P !", Toast.LENGTH_SHORT).show();
                } else {
                    rb = findViewById(rg.getCheckedRadioButtonId());
                    if (rb.getText().toString().equals(RepCorrect)) {
                        score += 1;
                    }
                    Intent intent = new Intent(quizpage5.this, quizpage6.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}