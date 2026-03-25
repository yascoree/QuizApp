package com.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class quizpage1 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    ImageView ivQuestion;
    int score = 0;
    String RepCorrect = "Non";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Anti-fraude : Empêcher les captures d'écran
        AntiFraudManager.preventScreenshots(this);
        
        setContentView(R.layout.activity_quizpage1);

        // Anti-fraude : Vérifier les permissions Caméra/Micro
        if (!AntiFraudManager.hasPermissions(this)) {
            AntiFraudManager.requestPermissions(this);
        }

        ivQuestion = findViewById(R.id.ivQuestion);
        rg = findViewById(R.id.rg);
        bNext = findViewById(R.id.bNext);

        String imageUrl = SupabaseClient.getImageUrl("quiz-images", "q1.jpg");
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.q1)
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
                    Intent intent = new Intent(quizpage1.this, quizpage2.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Anti-fraude : Détecter si l'utilisateur quitte l'application
        AntiFraudManager.detectExit(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AntiFraudManager.REQUEST_CODE_PERMISSIONS) {
            if (!AntiFraudManager.hasPermissions(this)) {
                Toast.makeText(this, "Les permissions sont nécessaires pour passer le quiz.", Toast.LENGTH_LONG).show();
                finish(); // Fermer si l'utilisateur refuse (politique anti-fraude)
            }
        }
    }
}