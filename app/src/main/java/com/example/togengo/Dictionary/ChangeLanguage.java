package com.example.togengo.Dictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.togengo.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public class ChangeLanguage extends Activity {
    private TextToSpeech tts;
    EditText wantText;
    Button translationBtn;
    ImageView changeImage, ttsound;
    TextView stateText, transText;
    String result;

    String wnatlanguage[] = {"ko","en","ja","aa"};
    String statelist[] = {"현재 한글에서 번역됨.", "현재 영어에서 번역됨.", "현재 일본어에서 번역됨.", "aa"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papago_activity);
        Intent intent = getIntent();
        boolean languageCheck = ((Intent) intent).getBooleanExtra("language", true);
        System.out.println(languageCheck);
        wantText = (EditText) findViewById(R.id.wantText);
        translationBtn = (Button) findViewById(R.id.searchtrans);
        changeImage = (ImageView) findViewById(R.id.getimage);
        stateText = (TextView) findViewById(R.id.getState);
        transText = (TextView) findViewById(R.id.transText);
        ttsound = (ImageView) findViewById(R.id.ttsSound);
        if(languageCheck== false){
            wnatlanguage[3] = wnatlanguage[1];
            wnatlanguage[1] = wnatlanguage[2];
            wnatlanguage[2] = wnatlanguage[3];
            statelist[3] = statelist[1];
            statelist[1] = statelist[2];
            statelist[2] = statelist[3];
        }
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(languageCheck == false) {
                    tts.setLanguage(Locale.JAPAN);
                } else if(languageCheck == true) {
                    tts.setLanguage(Locale.ENGLISH);
                }

            }
        });
        ttsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wnatlanguage[1].equals("ko")){
                    tts.setLanguage(Locale.KOREA);
                } else if(wnatlanguage[1].equals("ko")){

                }
                tts.setPitch(1.0f);         // 음성 톤은 기본 설정
                tts.setSpeechRate(1.0f);    // 읽는 속도를 0.5빠르기로 설정
                // editText에 있는 문장을 읽는다.
                tts.speak(transText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        changeImage.setOnClickListener(new View.OnClickListener() {
            boolean test = true;
            @Override
            public void onClick(View v) {
                System.out.println(test);
                if(test== true){
                    stateText.setText(statelist[1]);
                    wnatlanguage[3] = wnatlanguage[0];
                    wnatlanguage[0] = wnatlanguage[1];
                    wnatlanguage[1] = wnatlanguage[3];
                    test = false;
                } else {
                    stateText.setText(statelist[0]);
                    wnatlanguage[3] = wnatlanguage[0];
                    wnatlanguage[0] = wnatlanguage[1];
                    wnatlanguage[1] = wnatlanguage[3];
                    test = true;
                }
            }
        });
        translationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Translate translate = new Translate();
                translate.execute();
            }
        });
    }
    class Translate extends AsyncTask<String ,Void, String > {   //ASYNCTASK를 사용


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override

        protected String doInBackground(String... strings) {

            //////네이버 API

            String clientId = "r0E73mwcqlMZ7iMjBspB";     //애플리케이션 클라이언트 아이디값";
            String clientSecret = "1ynGVdQsBT";      //애플리케이션 클라이언트 시크릿값";
            try {
                String text = URLEncoder.encode(wantText.getText().toString(), "UTF-8");  /// 번역할 문장 Edittext  입력

                String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                String postParams = "source="+wnatlanguage[0]+"&target="+wnatlanguage[1]+"&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                //        textView.setText(response.toString());
                result = response.toString();

                String papago[] = result.split("\"");   //스플릿으로 번역된 결과값만 가져오기
                transText.setText(papago[15]); //  텍스트뷰에  SET해주기




            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }
}
