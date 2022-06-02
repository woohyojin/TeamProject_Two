package com.example.togengo.MainUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togengo.Dictionary.Dictionary_Activity;
import com.example.togengo.Grammer.JapanMain;
import com.example.togengo.Quiz.QuizActivity;
import com.example.togengo.R;
import com.example.togengo.Youtube.YoutubeClickActivity;

public class Btn_jp extends AppCompatActivity {
    View backBtn;
    View jword;
    View jquiz;
    View jgrammer;
    View jyoutube;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_jp);


        backBtn = (View) findViewById(R.id.back1);
        jword = (View) findViewById(R.id.jword);
        jquiz = (View) findViewById(R.id.jquiz);
        jgrammer = (View) findViewById(R.id.jgrammer);
        jyoutube = (View) findViewById(R.id.btn_youtube);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        jword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_jp.this , Dictionary_Activity.class);
                intent.putExtra("jp",false);
                startActivity(intent);
            }
        });
        jquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_jp.this , QuizActivity.class);
                intent.putExtra("lang","japanesequiz");
                startActivity(intent);
            }
        });

        jgrammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_jp.this , JapanMain.class);
                startActivity(intent);

            }
        });

        jyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Btn_jp.this, YoutubeClickActivity.class);
                startActivity(intent);
            }
        });


    }
}