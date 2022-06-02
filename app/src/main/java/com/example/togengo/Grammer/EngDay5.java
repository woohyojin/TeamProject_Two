package com.example.togengo.Grammer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.togengo.R;

public class EngDay5 extends Activity {
    Button btn1, btn2, btnBack;
    TextView currentText;
    ViewFlipper vFlipper;
    int vfCount, curNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engstudy5);

        Intent intent = getIntent();
        int num = intent.getIntExtra("number5", 0);

        btnBack = (Button) findViewById(R.id.btnReturn);//돌아가기
        btn1 = (Button) findViewById(R.id.prevBtn);//이전
        btn2 = (Button) findViewById(R.id.nextBtn);//다음
        currentText = (TextView) findViewById(R.id.tvNumber);// 0/0
        vFlipper = (ViewFlipper) findViewById(R.id.vewF1);

        //현재 페이지 수
        vfCount = vFlipper.getChildCount();

        // MainActivity에서 받은 getIntExtra에 맞춰서 page를 넘기는 부분
        for (int i = 0; i < num - 1; i++) {
            vFlipper.showNext();
        }

        curNum = num - 1;// 페이지가 꼬이지 않게
        currentText.setText(num + "/" + vfCount);// 초기 페이지 설정

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), EnglishMain.class);
                outIntent.putExtra("number5", curNum);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vFlipper.showPrevious();
                if (curNum <= 0) {
                    curNum = vfCount - 1;
                } else {
                    curNum--;
                }
                currentText.setText((curNum + 1) + "/" + vfCount);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vFlipper.showNext();
                if (curNum >= vfCount - 1) {
                    curNum = 0;
                } else {
                    curNum++;
                }
                currentText.setText((curNum + 1) + "/" + vfCount);
            }
        });

    }

}
