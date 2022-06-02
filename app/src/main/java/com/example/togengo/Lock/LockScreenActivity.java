package com.example.togengo.Lock;

import static android.content.ContentValues.TAG;
import static android.util.Log.ERROR;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.togengo.Quiz.DataAdapter;
import com.example.togengo.R;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class LockScreenActivity extends Activity {
    TextView textView;
    TextView wordView;
    TextView meaningview;
    ImageView img;
    ImageView wordimageview;
    ImageButton leftBtn;
    ImageButton rightBtn;
    ImageView speakerBtn;
    ImageButton cameraBtn;
    Integer[] wordimage = {R.drawable.lock_like, R.drawable.lock_love, R.drawable.lock_liberty, R.drawable.lock_experience, R.drawable.lock_effect, R.drawable.lock_educate, R.drawable.lock_source, R.drawable.lock_triumph, R.drawable.lock_object, R.drawable.lock_revolut, R.drawable.lock_system, R.drawable.lock_respect, R.drawable.lock_community, R.drawable.lock_impact, R.drawable.lock_price, R.drawable.lock_art, R.drawable.lock_gitft, R.drawable.lock_teachar, R.drawable.lock_bit, R.drawable.lock_nice, R.drawable.lock_balance, R.drawable.lock_power, R.drawable.lock_start, R.drawable.lock_send, R.drawable.lock_can, R.drawable.lock_operation, R.drawable.lock_soft, R.drawable.lock_lock, R.drawable.lock_nurse, R.drawable.lock_office, R.drawable.lock_business};
    File file;
    private TextToSpeech tts;


    int curNum = 0;
    int index = 0;
    public List<Word> wordList;
    boolean aa;


    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen); // activity_lock_screen.xml 액티비티를 실행함.
        aa = false;


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        // 안드로이드 잠금화면보다 위에 액티비티를 띄우게 해주는 코드

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE},MODE_PRIVATE);

        initLoadDB();

        leftBtn = (ImageButton) findViewById(R.id.leftImg);
        speakerBtn = (ImageView)findViewById(R.id.speakerImg);
        rightBtn = (ImageButton)findViewById(R.id.rightImg);
        cameraBtn = (ImageButton)findViewById(R.id.cameraImg);

        wordimageview = (ImageView) findViewById(R.id.wordimageview);


        wordView = findViewById(R.id.wordview);
        meaningview = findViewById(R.id.meaningview);

        wordView.setText(wordList.get(index).getWord());
        meaningview.setText(wordList.get(index).getMeaning());

        wordimageview.setImageResource(R.drawable.lock_like);





        //텍스트뷰 받아와서
        textView = findViewById(R.id.Timeclock);
        //쓰레드를 이용해서 시계표시
        Thread thread = new Thread(){
            @Override
            public void run(){
                while (!isInterrupted()){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            Calendar calendar = Calendar.getInstance(); //칼렌다 변수
                            int year = calendar.get(Calendar.YEAR); //년
                            int month = calendar.get(Calendar.MONTH); //월
                            int day = calendar.get(Calendar.DAY_OF_MONTH); //일
                            textView.setText(year + "년" + month + "월" + day + "일\n");
                        if(aa == true) return;
                        }
                    });
                    try{
                        Thread.sleep(1000); // 1000ms = 1초
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        int DBCount = wordList.size() - 1;





        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index <= 0) {
                    index = DBCount;
                    curNum = DBCount;
                    wordView.setText(wordList.get(index).getWord());
                    meaningview.setText(wordList.get(index).getMeaning());
                    wordimageview.setImageResource(wordimage[index]);
                } else {
                    index--;
                    curNum--;
                    wordView.setText(wordList.get(index).getWord());
                    meaningview.setText(wordList.get(index).getMeaning());
                    wordimageview.setImageResource(wordimage[index]);
                }
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index >= DBCount) {
                    index = 0;
                    curNum = 0;
                    wordView.setText(wordList.get(index).getWord());
                    meaningview.setText(wordList.get(index).getMeaning());
                    wordimageview.setImageResource(wordimage[index]);
                } else {
                    index++;
                    curNum++;
                    wordView.setText(wordList.get(index).getWord());
                    meaningview.setText(wordList.get(index).getMeaning());
                    wordimageview.setImageResource(wordimage[index]);
                }
            }
        });


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        speakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tts.speak(wordView.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
    }




    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext(),"test","englishword.db");
        mDbHelper.createDatabase();
        mDbHelper.open();
        // db에 있는 값들을 model을 적용해서 넣는다.
        wordList = mDbHelper.getwordTableData();
        // db 닫기
        mDbHelper.close();
    }
    @Override
    protected void onStop() {
        super.onStop();
        aa = true;
    }


    @Override
    protected void onDestroy() {
        //Close the Text to Speech Library
        if(tts != null) {

            tts.stop();
            tts.shutdown();
            Log.d(TAG, "TTS Destroyed");
        }
        super.onDestroy();
    }





}
