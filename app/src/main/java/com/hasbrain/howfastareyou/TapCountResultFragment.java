package com.hasbrain.howfastareyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!=null){
            lsvHightScore = (ListHighScore) savedInstanceState.getSerializable(TapCountActivity.HIGHSCORE);
            Log.d("restore listhighscore ", lsvHightScore.getListHighScore().size()+" ");
        }else {
            lsvHightScore = new ListHighScore();
            Log.d("create listhighscore ","this is message attention create list high score");
        }
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
        ada.notifyDataSetChanged();
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
