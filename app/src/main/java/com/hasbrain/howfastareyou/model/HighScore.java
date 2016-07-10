package com.hasbrain.howfastareyou.model;

import java.io.Serializable;

/**
 * Created by levinh on 07/04/16.
 */
public class HighScore implements Serializable {

    private int mScore;
    private long mTime;

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }

    public HighScore(int score) {
        this.mScore = score;
        this.mTime = System.currentTimeMillis();
    }

    public HighScore(int score,long time) {
        this.mScore = score;
        this.mTime = time;
    }
}
