package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.e.library.adapter.ESingleRecycleAdapter;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.entity.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lxy on 2016/7/14.
 */
public class NoteAdapter extends ESingleRecycleAdapter<Note> {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    public NoteAdapter(Context context, List<Note> datas) {
        super(context, datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_note;
    }

    @Override
    protected void onBindData(View convertView, Note data, int position) {
        TextView tvTitle = getViewById(convertView,R.id.tv_title);
        TextView tvDate = getViewById(convertView,R.id.tv_date);
        TextView tvContent = getViewById(convertView,R.id.tv_content);

        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContent());
        String date = format.format(new Date(data.getDate()));
        tvDate.setText(date);
    }
}
