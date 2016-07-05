package com.hasbrain.howfastareyou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hasbrain.howfastareyou.adapter.ListViewAdapter;
import com.hasbrain.howfastareyou.model.HighScore;
import com.hasbrain.howfastareyou.model.ListHighScore;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/14/15.
 */
public class TapCountResultFragment extends Fragment {
    ListView lvResult;
    ListHighScore lsvHightScore;
    ListViewAdapter ada;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            lsvHightScore = (ListHighScore)savedInstanceState.getSerializable(TapCountActivity.HIGHSCORE);
            ada.notifyDataSetChanged();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lsvHightScore = new ListHighScore();
        lvResult = (ListView) getView().findViewById(R.id.list_result);
        ada = new ListViewAdapter(getActivity(), R.layout.item_listview, lsvHightScore.getListHighScore());
        lvResult.setAdapter(ada);
    }

    public void updateScore(int score) {
        lsvHightScore.addHighScore(score);
        ada.notifyDataSetChanged();
    }

    public void setResult(ListHighScore listHighScore) {
        this.lsvHightScore = listHighScore;
    }

    public ListHighScore getResult() {
        return this.lsvHightScore;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TapCountActivity.HIGHSCORE,lsvHightScore);
    }
}
