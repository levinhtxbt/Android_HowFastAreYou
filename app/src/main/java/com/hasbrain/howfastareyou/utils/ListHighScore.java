package com.hasbrain.howfastareyou.utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hasbrain.howfastareyou.model.HighScore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by levinh on 07/04/16.
 */
public class ListHighScore implements Serializable {

    static List<HighScore> lstHighScore;

    public ListHighScore(List<HighScore> lstHighScore) {
        this.lstHighScore = lstHighScore;
    }

    public void addScore(HighScore score) {
        lstHighScore.add(score);
        SaveToDatabase(score);
    }

    public List<HighScore> getListHighScore() {
        return lstHighScore;
    }

    public static ListHighScore fromDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        lstHighScore = databaseHelper.getListHighScore();
        return new ListHighScore(lstHighScore);
    }

    public void resetHighScore() {
        lstHighScore.clear();
        SQLiteDatabase db = DatabaseHelper.openDatabase();
        db.delete(DatabaseHelper.TB_SCORE, null, null);
        DatabaseHelper.closeDatabase();
    }

    public void SaveToDatabase(HighScore highscore) {
        SQLiteDatabase db = DatabaseHelper.openDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TIME, highscore.getTime());
        values.put(DatabaseHelper.COL_TIME_COUNT, highscore.getTimeCount());
        values.put(DatabaseHelper.COL_SCORE, highscore.getScore());
        db.insert(DatabaseHelper.TB_SCORE, null, values);

//        final SQLiteStatement statement = db.compileStatement(
//                "INSERT INTO " + DatabaseHelper.TB_SCORE + " VALUES( ? , ?)");
//        db.beginTransaction();
//            try {
//                statement.clearBindings();
//                statement.bindLong(1,highscore.getTime());
//                statement.bindLong(2,highscore.getScore());
//                statement.executeInsert();
//                db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
        DatabaseHelper.closeDatabase();
    }
}
