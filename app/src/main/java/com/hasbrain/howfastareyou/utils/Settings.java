package com.hasbrain.howfastareyou.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by levinh on 07/10/16.
 */
public class Settings {
    private static final String FILE_NAME = "SharedReferencesUtil";
    private static final String TIME_LIMIT = "timelimit";
    private static final String RECORD_HIGHSCORE = "recordhighscore";
    public static final int DEFAULT_TIME_LIMIT = 10;

    private Context context;
    private int timeLimit;
    private boolean recordHighScore;
    private SharedPreferences sharedPreferences;

    public Settings(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);

        if (sharedPreferences != null) {
            this.timeLimit = sharedPreferences.getInt(TIME_LIMIT, DEFAULT_TIME_LIMIT);
            this.recordHighScore = sharedPreferences.getBoolean(RECORD_HIGHSCORE, false);
        } else {
            this.timeLimit = 10;
            this.recordHighScore = true;
        }
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isRecordHighScore() {
        return recordHighScore;
    }

    public void setRecordHighScore(boolean recordHighScore) {
        this.recordHighScore = recordHighScore;
    }

//    public void getSetting() {
//        if (sharedPreferences != null) {
//            this.timeLimit = sharedPreferences.getInt(TIME_LIMIT, DEFAULT_TIME_LIMIT);
//            this.recordHighScore = sharedPreferences.getBoolean(RECORD_HIGHSCORE, false);
//        }else {
//            this.timeLimit = 10;
//            this.recordHighScore = true;
//        }
//    }

    public void saveSetting() {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(TIME_LIMIT, this.timeLimit);
            editor.putBoolean(RECORD_HIGHSCORE, this.recordHighScore);
            editor.commit();
        }

    }
}
