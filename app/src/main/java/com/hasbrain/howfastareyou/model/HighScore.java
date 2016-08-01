package com.hasbrain.howfastareyou.model;

import java.io.Serializable;

/**
 * Created by levinh on 07/04/16.
 */
public class HighScore implements Serializable {

    private int mScore;
    private long mTime;
    private int mTimeCount;

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

    public HighScore(int score, int timecount) {
        this.mScore = score;
        this.mTimeCount = timecount;
        this.mTime = System.currentTimeMillis();
    }

    public HighScore(int score, int timecount, long time) {
        this.mTimeCount = timecount;
        this.mScore = score;
        this.mTime = time;
    }

    public int getTimeCount() {
        return mTimeCount;
    }

    public void setTimeCount(int mTimeCount) {
        this.mTimeCount = mTimeCount;
    }
}
