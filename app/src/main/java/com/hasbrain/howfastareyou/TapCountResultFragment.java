package com.hasbrain.howfastareyou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hasbrain.howfastareyou.adapter.ListViewAdapter;
import com.hasbrain.howfastareyou.model.ListHighScore;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/14/15.
 */
public class TapCountResultFragment extends Fragment {
    private static final String TAG = "Fragment";
    ListView lvResult;
    ListHighScore lsvHightScore = null;
    ListViewAdapter ada = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); //
        Log.d(TAG, "onCreate: " + this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        Log.d(TAG, "onCreateView: " + this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Fragment:", this + " " + savedInstanceState);
//        if(savedInstanceState!=null){
//            Log.d(TAG, "onViewCreated: savedInstanceState!=null");
//            lsvHightScore = (ListHighScore) savedInstanceState.getSerializable(TapCountActivity.HIGHSCORE);
//        }else {
//            lsvHightScore = new ListHighScore();
//        }

        if (lsvHightScore == null) {
            lsvHightScore = new ListHighScore();
            ada = new ListViewAdapter(getActivity(), R.layout.item_listview, lsvHightScore.getListHighScore());
        }
        lvResult = (ListView) getView().findViewById(R.id.list_result);
        lvResult.setAdapter(ada);
        Log.d(TAG, "onViewCreated: " + this);
    }


    public void updateScore(int score) {
        lsvHightScore.addHighScore(score);
        ada.notifyDataSetChanged();
    }

    public void setResult(ListHighScore listHighScore) {
        this.lsvHightScore = listHighScore;
        ada.notifyDataSetChanged();
    }

    public ListHighScore getResult() {
        return this.lsvHightScore;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putSerializable(TapCountActivity.HIGHSCORE,lsvHightScore);
        Log.d(TAG, "onSaveInstanceState: " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this);
    }
}
