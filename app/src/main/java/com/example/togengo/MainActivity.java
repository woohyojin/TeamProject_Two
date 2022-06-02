package com.example.togengo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togengo.Lock.LockMainActivity;
import com.example.togengo.MainUI.Btn_eng;
import com.example.togengo.MainUI.Btn_jp;

public class MainActivity extends AppCompatActivity {

    View btn_eng;
    View  btn_jp;
    Button btn_Option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();


        btn_eng = findViewById(R.id.btn_eng);
        btn_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Btn_eng.class);
                startActivity(intent);
            }
        });

        btn_jp = findViewById(R.id.btn_jp);
        btn_jp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Btn_jp.class);
                startActivity(intent);
            }
        });

        btn_Option = (Button) findViewById(R.id.button10);
        btn_Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , LockMainActivity.class);
                startActivity(intent);
            }
        });


    }




}