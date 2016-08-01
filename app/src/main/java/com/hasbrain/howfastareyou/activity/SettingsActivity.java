package com.hasbrain.howfastareyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.hasbrain.howfastareyou.R;
import com.hasbrain.howfastareyou.utils.Settings;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/14/15.
 */
public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        SwitchCompat.OnCheckedChangeListener {
    private static final int MIN_TIME = 5;

    @Bind(R.id.tv_time_limit)
    AppCompatTextView tvTimeLimit;
    @Bind(R.id.sb_time_limit)
    SeekBar sbTimeLimit;
    @Bind(R.id.sc_record_high_score)
    SwitchCompat scRecordHighScore;

    Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle(R.string.settings_text);
        ButterKnife.bind(this);

        // Restore SharedReferences
        settings = new Settings();

        tvTimeLimit.setText(settings.getTimeLimit() + " sec");
        sbTimeLimit.setProgress(settings.getTimeLimit() - 5);
        scRecordHighScore.setChecked(settings.isRecordHighScore());

        sbTimeLimit.setOnSeekBarChangeListener(this);
        scRecordHighScore.setOnCheckedChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        settings.setTimeLimit(progress + MIN_TIME);
        tvTimeLimit.setText(settings.getTimeLimit() + " sec");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        settings.setRecordHighScore(isChecked);
    }

    @Override
    protected void onPause() {
        super.onPause();
        settings.saveSetting();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, TapCountActivity.class);
        startActivity(intent);
        finish();
    }
}
