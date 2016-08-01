package com.hasbrain.howfastareyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hasbrain.howfastareyou.R;
import com.hasbrain.howfastareyou.model.HighScore;
import com.hasbrain.howfastareyou.utils.ListHighScore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by levinh on 07/04/16.
 */
public class ListViewAdapter extends ArrayAdapter<HighScore> {
    List<HighScore> mList;
    Context mContext;
    int mLayout;

    public ListViewAdapter(Context context, int resource, ListHighScore objects) {
        super(context, resource, objects.getListHighScore());
        this.mContext = context;
        this.mLayout = resource;
        this.mList = objects.getListHighScore();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(mLayout, null);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
            viewHolder.tv_time_count = (TextView) convertView.findViewById(R.id.tv_time_count);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HighScore result = mList.get(position);
        Date date = new Date(result.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        viewHolder.tv_time.setText(sdf.format(date));
        viewHolder.tv_count.setText(String.valueOf(result.getScore()));
        viewHolder.tv_time_count.setText(String.valueOf(result.getTimeCount()));
        return convertView;
    }

    public class ViewHolder {
        public TextView tv_time, tv_count, tv_time_count;

    }
}
