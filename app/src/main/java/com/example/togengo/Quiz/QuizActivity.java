package com.example.togengo.Quiz;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togengo.MainUI.Btn_jp;
import com.example.togengo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuizActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> resultLauncher;

    List<Quiz> quizList; // 퀴즈들이 들어가는 리스트
    Button[] buts = new Button[4]; // 예시 버튼 4개
    TextView Problem; // 문제
    TextView ProgressCounter; // 현재 진행중인 문제 수를 표시
    DataAdapter mDbHelper; // DB불러오기
    th1handler han; // UI 갱신용 핸들러
    String Answer = "none"; // 정답
    Bundle data = new Bundle();
    ProgressBar progressBar;
    int problem_Count = 0;
    int Wrong_Count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent intent = result.getData();
                            int CallType = intent.getIntExtra("CallType", 0);
                            if(CallType == 0){
                                //실행될 코드
                            }else if(CallType == 1){
                                //실행될 코드
                            }else if(CallType == 2){
                                //실행될 코드
                            }
                        }
                    }
                });
        
        buts[0] = (Button)findViewById(R.id.button1);
        buts[1] = (Button)findViewById(R.id.button2);
        buts[2] = (Button)findViewById(R.id.button3);
        buts[3] = (Button)findViewById(R.id.button4);

        Problem = (TextView)findViewById(R.id.problem);
        ProgressCounter = (TextView)findViewById(R.id.textView2);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        try {
            UpdateQuiz(); //DB로드
            SetListener(); // 입력리스너 로드
            SettingQuiz(0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        progressBar.setMax(quizList.size());
        QuizSequence quizth = new QuizSequence();//진행바,UI 변경
        han = new th1handler();
        Thread th1 = new Thread(quizth);
        th1.start();
    }


    public void SetListener(){

        buts[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer = "1";
                quizResultCheck();
            }
        });
        buts[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Answer = "2";
               quizResultCheck();
            }
        });
        buts[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Answer = "3";
               quizResultCheck();
            }
        });
        buts[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer ="4";
                quizResultCheck();
            }
        });
    }

    public void SettingQuiz(int num){  // 퀴즈 세팅

        if(quizList.size()!=problem_Count) {
            String[] buts_Examples = quizList.get(num).getExample().split("\\n");

            Problem.setText(quizList.get(num).getProblem());

            for (int i = 0; i < buts_Examples.length; i++) {
                buts[i].setText(buts_Examples[i]);
            }
        }

    }

    public void UpdateQuiz() throws ExecutionException, InterruptedException {

        Intent intent = getIntent();
        String url = "http://10.0.2.2/connect.php";
        DataBaseHelper.NetworkTask networkTask = new DataBaseHelper.NetworkTask(url,null,intent.getStringExtra("lang"));
        String jsonFile = networkTask.execute().get();


        quizList = new ArrayList();


        try {
           if(jsonFile !=null) {
               JSONObject jsonObject = new JSONObject(jsonFile);
               JSONArray jsonArray = jsonObject.getJSONArray(intent.getStringExtra("lang"));

               for (int i = 0; i < jsonArray.length(); i++) {
                   JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                   Quiz quiz = new Quiz(null, null, null, null);

                   quiz.setQuestion(jsonObject1.getString("question"));
                   quiz.setProblem(jsonObject1.getString("problem"));
                   quiz.setExample(jsonObject1.getString("example"));
                   quiz.setAnswer(jsonObject1.getString("answer"));

                   quizList.add(quiz);
               }
           }else if(jsonFile == null){
                initLoadDB();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void initLoadDB(){ // DB 로드

        Intent intent = getIntent();
        mDbHelper = new DataAdapter(getApplicationContext(),intent.getStringExtra("lang"),"problem.db");
        mDbHelper.createDatabase();
        mDbHelper.open();
        quizList = mDbHelper.getTableData();

    }
    public void settingProgress(Bundle data){
        Message msg = han.obtainMessage();
        data.putString("data",String.valueOf(problem_Count));
        data.putString("data2",String.valueOf(quizList.size()));
        msg.setData(data);
        han.sendMessage(msg);
    }


    public void quizResultCheck(){

        Intent intent_this = getIntent();
        String lang = intent_this.getStringExtra("lang");
        if(quizList.size() != problem_Count) {
            if (quizList.get(problem_Count).getAnswer().equals(Answer)) {
                System.out.println("정답입니다" +problem_Count);

                problem_Count++;

                Answer = " ";
                SettingQuiz(problem_Count);

                if(problem_Count == quizList.size()){

                    System.out.println("끝" + problem_Count);
                    //팝업창
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("wrong", Integer.toString(Wrong_Count));
                    intent.putExtra("problem",Integer.toString(problem_Count));
                    intent.putExtra("lang",lang);
                    resultLauncher.launch(intent);
                }

            } else {
                Wrong_Count++;
                System.out.println("오답입니다" +Wrong_Count);
            }
        }else if(quizList.size() == problem_Count){
            Intent intent = new Intent(QuizActivity.this , Btn_jp.class);
            startActivity(intent);
        }
    }
    class QuizSequence implements Runnable { //퀴즈 시퀀스
        int problem_Count = 0;
        @Override
        public void run() {

            while (true) {

                if(problem_Count == quizList.size()){
                    problem_Count = 0;
                    System.out.println(problem_Count);
                    break;
                }else {
                    settingProgress(data);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    class th1handler extends Handler{
        @Override
        public void handleMessage(Message msg){
            if(msg.what==0){
                super.handleMessage(msg);
                String progressbar_Num = msg.getData().getString("data");
                String progress_Count = msg.getData().getString("data2");

                ProgressCounter.setText(progressbar_Num + "/" + progress_Count);
                progressBar.setProgress(Integer.parseInt(progressbar_Num));
            }
        }
    };


    public static class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        public String json_file;
        public JSONArray jsonArray;


        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            json_file = s;

            try {
                if(s != null) {
                    JSONObject jsonObject = new JSONObject(s);
                    jsonArray = jsonObject.getJSONArray("Tree");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}