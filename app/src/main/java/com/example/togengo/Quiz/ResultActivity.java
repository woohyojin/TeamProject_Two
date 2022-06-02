package com.example.togengo.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togengo.MainUI.Btn_eng;
import com.example.togengo.MainUI.Btn_jp;
import com.example.togengo.R;

public class ResultActivity extends AppCompatActivity {

    Button but;
    TextView problem_Count;
    TextView wrong_Count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO [layout >> xml 파일 지정]
        setContentView(R.layout.content_result);

        Intent intent = getIntent();

        but = findViewById(R.id.button5);
        problem_Count = findViewById(R.id.pro_Count);
        wrong_Count = findViewById(R.id.wrong_count);

        this.setTitle("");

        problem_Count.setText(intent.getStringExtra("problem"));
        wrong_Count.setText(intent.getStringExtra("wrong"));



        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intent.getStringExtra("lang").equals("englishquiz")) {
                    Intent intent = new Intent(ResultActivity.this, Btn_eng.class);
                    startActivity(intent);
                }
                else if(intent.getStringExtra("lang").equals("japanesequiz")){
                    Intent intent = new Intent(ResultActivity.this, Btn_jp.class);
                    startActivity(intent);
                }
            }
        });
    }

}