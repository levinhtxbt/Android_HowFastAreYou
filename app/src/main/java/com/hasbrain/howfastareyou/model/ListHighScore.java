package com.hasbrain.howfastareyou.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levinh on 07/04/16.
 */
public class ListHighScore implements Serializable {

    List<HighScore> lstHighScore;

    public ListHighScore(List<HighScore> lstHighScore) {
        this.lstHighScore = lstHighScore;
    }

    public ListHighScore() {
        this.lstHighScore = new ArrayList<HighScore>();
    }

    public void addHighScore(HighScore highScore) {
        lstHighScore.add(highScore);
    }

    public void addHighScore(int score) {
        lstHighScore.add(new HighScore(score));
    }

    public  void addHighScore(int score, long time){
        lstHighScore.add(new HighScore(score,time));
    }

    public List<HighScore> getListHighScore() {
        return lstHighScore;
    }
}
