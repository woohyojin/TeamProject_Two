package com.example.togengo.Quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.togengo.Lock.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private String TABLE_NAME = "";
    private String DB_NAME = "";
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context,String tableName, String dbName)
    {
        this.mContext = context;
        this.TABLE_NAME = tableName;
        this.DB_NAME = dbName;

        mDbHelper = new DataBaseHelper(mContext,DB_NAME);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public List getTableData()
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            List quizList = new ArrayList();

            // TODO : 모델 선언
            Quiz quiz = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                      quiz = new Quiz(null,null,null,null);

                      quiz.setQuestion(mCur.getString(0));
                      quiz.setProblem(mCur.getString(1));
                      quiz.setExample(mCur.getString(2));
                      quiz.setAnswer(mCur.getString(3));

                    quizList.add(quiz);
                }

            }
            return quizList;
        }

        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }


    public List getwordTableData()
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;
            // 모델 넣을 리스트 생성
            List wordlist = new ArrayList();
            // TODO : 모델 선언
            Word word = null;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {
                    // TODO : 커스텀 모델 생성
                    word = new Word();
                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    word.setIdx(mCur.getString(0));
                    word.setDivision(mCur.getString(1));
                    word.setWord(mCur.getString(2));
                    word.setMeaning(mCur.getString(3));
                    // 리스트에 넣기
                    wordlist.add(word);
                }
            }
            return wordlist;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getWordData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
