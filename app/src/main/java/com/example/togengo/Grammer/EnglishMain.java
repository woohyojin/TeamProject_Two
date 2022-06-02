package com.example.togengo.Grammer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.TextView;

import com.example.togengo.R;

public class EnglishMain extends AppCompatActivity {//영어 문법 리스트
    TextView list1curNum, list2curNum, list3curNum, list4curNum, list5curNum, list6curNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.englishlist_main);
        setTitle("영어 문법 리스트");

        ViewGroup List1 = (ViewGroup) findViewById(R.id.list1);
        ViewGroup List2 = (ViewGroup) findViewById(R.id.list2);
        ViewGroup List3 = (ViewGroup) findViewById(R.id.list3);
        ViewGroup List4 = (ViewGroup) findViewById(R.id.list4);
        ViewGroup List5 = (ViewGroup) findViewById(R.id.list5);
        ViewGroup List6 = (ViewGroup) findViewById(R.id.list6);

        list1curNum = (TextView) findViewById(R.id.listNum1);
        list2curNum = (TextView) findViewById(R.id.listNum2);
        list3curNum = (TextView) findViewById(R.id.listNum3);
        list4curNum = (TextView) findViewById(R.id.listNum4);
        list5curNum = (TextView) findViewById(R.id.listNum5);
        list6curNum = (TextView) findViewById(R.id.listNum6);

        // 뒤 화면에서 현재 페이지 값을 받는다.
        Intent intent = getIntent();


        List1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(EnglishMain.this,
                        //실행할 activity
                        //manifests->AndroidMainfest.xml에 클래스 추가
                        EngDay1.class);
                intent.putExtra("number1", page(list1curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 101);
            }
        });

        List2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(EnglishMain.this,
                        EngDay2.class);
                intent.putExtra("number2", page(list2curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 102);
            }
        });

        List3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(EnglishMain.this,
                        EngDay3.class);
                intent.putExtra("number3", page(list3curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 103);
            }
        });

        List4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(EnglishMain.this,
                        EngDay4.class);
                intent.putExtra("number4", page(list4curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 104);
            }
        });

        List5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(EnglishMain.this,
                        EngDay5.class);
                intent.putExtra("number5", page(list5curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 105);
            }
        });

        List6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(EnglishMain.this,
                        EngDay6.class);
                intent.putExtra("number6", page(list6curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 106);
            }
        });
    }
    //onActivityResult - main액티비티에서 sub액티비티를 호출하여 넘어갔다가,
    // 다시 main 액티비티로 돌아올때 사용되는 기본 메소드 이다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            int page = data.getIntExtra("number1", 0);
            list1curNum.setText(String.valueOf(page + 1));
        } else if (requestCode == 102) {
            int page = data.getIntExtra("number2", 0);
            list2curNum.setText(String.valueOf(page + 1));
        } else if (requestCode == 103) {
            int page = data.getIntExtra("number3", 0);
            list3curNum.setText(String.valueOf(page + 1));
        } else if (requestCode == 104) {
            int page = data.getIntExtra("number4", 0);
            list4curNum.setText(String.valueOf(page + 1));
        } else if (requestCode == 105) {
            int page = data.getIntExtra("number5", 0);
            list5curNum.setText(String.valueOf(page + 1));
        } else if (requestCode == 106) {
            int page = data.getIntExtra("number6", 0);
            list6curNum.setText(String.valueOf(page + 1));
        }

    }
    //Int로 함수값 설정
    private int page(TextView txt) {
        return Integer.parseInt(txt.getText().toString());
    }
}