package com.hasbrain.howfastareyou.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hasbrain.howfastareyou.MainApplication;
import com.hasbrain.howfastareyou.model.HighScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levinh on 01/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "score.db";
    public static final String TB_SCORE = "tblScore";
    ///Column for table Score
    public static final String COL_TIME = "Time";
    public static final String COL_SCORE = "Score";
    public static final String COL_TIME_COUNT = "Time_count";

    static SQLiteDatabase database;
    static DatabaseHelper mInstance;
    private static int mCouter = 0;

    public DatabaseHelper() {
        super(MainApplication.mSharedContext, DB_NAME, null, 1);
    }

    public static DatabaseHelper getInstance() {
        if (mInstance == null)
            mInstance = new DatabaseHelper();
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Do Create
        String sql = "CREATE TABLE " + TB_SCORE + " (" +
                COL_TIME + " INTEGER, " +
                COL_TIME_COUNT + " INTEGER, " +
                COL_SCORE + " INTEGER)";
        db.execSQL(sql);
    }

    public static synchronized SQLiteDatabase openDatabase() {
        mCouter++;
        if (mCouter == 1) {
            database = getInstance().getWritableDatabase();
        }
        return database;
    }

    public static synchronized void closeDatabase() {
        mCouter--;
        if (mCouter == 0) {
            database.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing
    }

    public List<HighScore> getListHighScore() {

        ArrayList<HighScore> mListHighScore = new ArrayList<HighScore>();
        SQLiteDatabase sqLiteDatabase = openDatabase();
        Cursor cursor = sqLiteDatabase.query(TB_SCORE, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            mListHighScore.add(new HighScore(cursor.getInt(2), cursor.getInt(1), cursor.getLong(0)));
            cursor.moveToNext();
        }
        cursor.close();
        return mListHighScore;
    }
}
