package com.hasbrain.howfastareyou;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hasbrain.howfastareyou.model.ListHighScore;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TapCountActivity extends AppCompatActivity {

    public static final int TIME_COUNT = 10000; //10s
    public static final String STATE = "State";
    public static final String START_TIME = "startTime";
    public static final String CURRENT_TIME = "currentTime";
    public static final String SCORE = "score";
    public static final String HIGHSCORE = "highscore";
    public static final int STATE_PLAY = 1;
    public static final int STATE_STOP = 2;
    public static final int STATE_PAUSE = 3;
    public static final int STATE_RESUME = 4;

    @Bind(R.id.bt_tap)
    Button btTap;
    @Bind(R.id.bt_start)
    Button btStart;
    @Bind(R.id.tv_time)
    Chronometer tvTime;
    @Bind(R.id.tvTabCount)
    TextView tvTabCount;

    private long mStartTime;
    private long mCurrentTime;
    private int mState = STATE_STOP;
    private int mScore;
    TapCountResultFragment fragment;
    ListHighScore mListHighScore;
    Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        ButterKnife.bind(this);
        mListHighScore = new ListHighScore();

        if (savedInstanceState != null) {

            mState = savedInstanceState.getInt(STATE);
            mStartTime = savedInstanceState.getLong(START_TIME, 0);
            mCurrentTime = savedInstanceState.getLong(CURRENT_TIME, 0);
            mScore = savedInstanceState.getInt(SCORE, 0);
            mListHighScore = (ListHighScore) savedInstanceState.getSerializable(HIGHSCORE);
            tvTabCount.setText(String.valueOf(mScore));
            tvTime.setBase(SystemClock.elapsedRealtime() - mCurrentTime + mStartTime);
            if (mState == STATE_PAUSE) {
                setStateGame(STATE_PAUSE);
            }
        }

        tvTime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (checkTimeOut(mStartTime)) {
                    stopTapping();
                }
            }
        });

        fragment = new TapCountResultFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_result_fragment, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        TapCountResultFragment resultFragment = (TapCountResultFragment)
//                getSupportFragmentManager().findFragmentById(R.id.fl_result_fragment);
//        if(resultFragment!=null){
//            resultFragment.setResult(mListHighScore);
//            Log.d("set result",mListHighScore.getListHighScore().size()+" ");
//        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putLong(CURRENT_TIME, mCurrentTime);
        outState.putLong(START_TIME, mStartTime);
        outState.putInt(SCORE, mScore);
        TapCountResultFragment resultFragment = (TapCountResultFragment)
                getSupportFragmentManager().findFragmentById(R.id.fl_result_fragment);
        if (resultFragment != null) {
            mListHighScore = resultFragment.getResult();
        }
        outState.putSerializable(HIGHSCORE, mListHighScore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTapping();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent showSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(showSettingsActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.bt_start)
    public void onStartBtnClicked(View v) {
        if (mState == STATE_PAUSE) {
            resumeTapping();
        } else if (mState == STATE_STOP) {
            startTapping();
        }
    }

    @OnClick(R.id.bt_tap)
    public void onTapBtnClicked(View v) {
        mScore++;
        tvTabCount.setText(String.valueOf(mScore));
    }

    private void startTapping() {
        mStartTime = SystemClock.elapsedRealtime();
        tvTime.setBase(mStartTime);
        mScore = 0;
        tvTabCount.setText("0");
        setStateGame(STATE_PLAY);
    }

    private void pauseTapping() {
        if (mState == STATE_PLAY) {
            mCurrentTime = SystemClock.elapsedRealtime();
            setStateGame(STATE_PAUSE);
        }
    }

    private void stopTapping() {

        mCurrentTime = SystemClock.elapsedRealtime();
        setStateGame(STATE_STOP);
        if (fragment != null) {
            fragment.updateScore(mScore);
            Log.d("update score",mScore+"");
        }
    }

    private void resumeTapping() {
        mStartTime = SystemClock.elapsedRealtime() - (mCurrentTime - mStartTime);
        tvTime.setBase(mStartTime);
        setStateGame(STATE_RESUME);
    }

    public void setStateGame(int state) {
        if (state == STATE_PLAY) {
            tvTime.start();
            mState = STATE_PLAY;
            btStart.setText("Start");
            btTap.setEnabled(true);
            btStart.setEnabled(false);

        } else if (state == STATE_STOP) {
            tvTime.stop();
            mState = STATE_STOP;
            btStart.setText("Start");
            btTap.setEnabled(false);
            btStart.setEnabled(true);

        } else if (state == STATE_PAUSE) {
            tvTime.stop();
            mState = STATE_PAUSE;
            btStart.setText("Resume");
            btTap.setEnabled(false);
            btStart.setEnabled(true);

        } else if (state == STATE_RESUME) {
            tvTime.start();
            mState = STATE_PLAY;
            btStart.setText("Start");
            btTap.setEnabled(true);
            btStart.setEnabled(false);
        }
    }

    public boolean checkTimeOut(long startTime) {
        return (SystemClock.elapsedRealtime() - startTime >= TIME_COUNT) ? true : false;
    }
}
