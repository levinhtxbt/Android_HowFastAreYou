package com.hasbrain.howfastareyou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.hasbrain.howfastareyou.R;
import com.hasbrain.howfastareyou.adapter.ListViewAdapter;
import com.hasbrain.howfastareyou.model.HighScore;
import com.hasbrain.howfastareyou.utils.ListHighScore;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/14/15.
 */
public class TapCountResultFragment extends Fragment {
    private static final String TAG = "Fragment";

    ListView lvResult;
    ListHighScore mListHighScore = null;
    ListViewAdapter ada = null;
    Button btnReset;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mListHighScore == null) {
            //read from database
            mListHighScore = ListHighScore.fromDatabase();
            ada = new ListViewAdapter(getActivity(), R.layout.item_listview, mListHighScore);
        }

        lvResult = (ListView) getView().findViewById(R.id.list_result);
        lvResult.setAdapter(ada);

        btnReset = (Button) getView().findViewById(R.id.bt_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHighScore.resetHighScore();
                ada.notifyDataSetChanged();
            }
        });
    }

    public void updateScore(int score, int timecount) {
        mListHighScore.addScore(new HighScore(score, timecount));
        ada.notifyDataSetChanged();
    }
}
