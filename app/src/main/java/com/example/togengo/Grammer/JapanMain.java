package com.example.togengo.Grammer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togengo.R;

public class JapanMain extends AppCompatActivity {//일본어 문법 리스트
    TextView Jlist1curNum, Jlist2curNum, Jlist3curNum, Jlist4curNum, Jlist5curNum, Jlist6curNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.japanlist_main);

        setTitle("일본어 문법 리스트");



        ViewGroup List1 = (ViewGroup) findViewById(R.id.list1);
        ViewGroup List2 = (ViewGroup) findViewById(R.id.list2);
        ViewGroup List3 = (ViewGroup) findViewById(R.id.list3);
        ViewGroup List4 = (ViewGroup) findViewById(R.id.list4);
        ViewGroup List5 = (ViewGroup) findViewById(R.id.list5);
        ViewGroup List6 = (ViewGroup) findViewById(R.id.list6);

        Jlist1curNum = (TextView) findViewById(R.id.JlistNum1);
        Jlist2curNum = (TextView) findViewById(R.id.JlistNum2);
        Jlist3curNum = (TextView) findViewById(R.id.JlistNum3);
        Jlist4curNum = (TextView) findViewById(R.id.JlistNum4);
        Jlist5curNum = (TextView) findViewById(R.id.JlistNum5);
        Jlist6curNum = (TextView) findViewById(R.id.JlistNum6);

        // 뒤 화면에서 현재 페이지 값을 받는다.
        Intent intent = getIntent();


        List1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(JapanMain.this,
                        //실행할 activity
                        //manifests->AndroidMainfest.xml에 클래스 추가
                        JapDay1.class);
                intent.putExtra("Jnumber1", Jpage(Jlist1curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 107);
            }
        });

        List2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(JapanMain.this,
                        JapDay2.class);
                intent.putExtra("Jnumber2", Jpage(Jlist2curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 108);
            }
        });

        List3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(JapanMain.this,
                        JapDay3.class);
                intent.putExtra("Jnumber3", Jpage(Jlist3curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 109);
            }
        });

        List4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(JapanMain.this,
                        JapDay4.class);
                intent.putExtra("Jnumber4", Jpage(Jlist4curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 110);
            }
        });

        List5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(JapanMain.this,
                        JapDay5.class);
                intent.putExtra("Jnumber5", Jpage(Jlist5curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 111);
            }
        });

        List6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지금 현재 activity
                Intent intent = new Intent(JapanMain.this,
                        JapDay6.class);
                intent.putExtra("Jnumber6", Jpage(Jlist6curNum));
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 112);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 107) {
            int Jpage = data.getIntExtra("Jnumber1", 0);
            Jlist1curNum.setText(String.valueOf(Jpage + 1));
        } else if (requestCode == 108) {
            int Jpage = data.getIntExtra("Jnumber2", 0);
            Jlist2curNum.setText(String.valueOf(Jpage + 1));
        } else if (requestCode == 109) {
            int Jpage = data.getIntExtra("Jnumber3", 0);
            Jlist3curNum.setText(String.valueOf(Jpage + 1));
        } else if (requestCode == 110) {
            int Jpage = data.getIntExtra("Jnumber4", 0);
            Jlist4curNum.setText(String.valueOf(Jpage + 1));
        } else if (requestCode == 111) {
            int Jpage = data.getIntExtra("Jnumber5", 0);
            Jlist5curNum.setText(String.valueOf(Jpage + 1));
        } else if (requestCode == 112) {
            int Jpage = data.getIntExtra("Jnumber6", 0);
            Jlist6curNum.setText(String.valueOf(Jpage + 1));
        }

    }

    private int Jpage(TextView txt) {
        return Integer.parseInt(txt.getText().toString());
    }
}