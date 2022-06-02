package com.example.togengo.MainUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togengo.Dictionary.Dictionary_Activity;
import com.example.togengo.Grammer.EnglishMain;
import com.example.togengo.Quiz.QuizActivity;
import com.example.togengo.R;
import com.example.togengo.Youtube.YoutubeClickActivity;

public class Btn_eng extends AppCompatActivity {
    View backBtn;
    View btn_youtube;
    View grammar;
    View word;
    View quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_eng);


        ActionBar actionBar = getSupportActionBar();



        grammar = (View) findViewById(R.id.grammar);
        quiz  = (View) findViewById(R.id.quiz);
        word = (View) findViewById(R.id.word);
        backBtn = (View) findViewById(R.id.back1);
        btn_youtube = (View) findViewById(R.id.btn_youtube);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_eng.this , Dictionary_Activity.class);
                startActivity(intent);
            }
        });

        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_eng.this , EnglishMain.class);
                startActivity(intent);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_eng.this , QuizActivity.class);
                intent.putExtra("lang","englishquiz");
                startActivity(intent);
            }
        });
        btn_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_eng.this , YoutubeClickActivity.class);
                startActivity(intent);
            }
        });




    }

}