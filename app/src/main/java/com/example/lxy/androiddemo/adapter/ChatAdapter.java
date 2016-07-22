package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.e.library.adapter.EMultiRecyclerAdapter;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.entity.Message;

import java.util.List;

/**
 * Created by lxy on 2016/7/22.
 */
public class ChatAdapter extends EMultiRecyclerAdapter<Message> {

    public ChatAdapter(Context context, List<Message> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemData(position).getType();
    }

    @Override
    protected int getLayoutId(int viewType) {
        if(viewType == Message.TYPE_TO){
            return R.layout.item_chat_to;
        }else if (viewType == Message.TYPE_FROM){
            return R.layout.item_chat_from;
        }
        return invalid;
    }

    @Override
    protected void onBindData(View convertView, Message data, int position, int viewType) {
        if (viewType == Message.TYPE_TO){
            handleChatTo(convertView,data);
        }else if (viewType == Message.TYPE_FROM){
            handleChatFrom(convertView,data);
        }
    }

    private void handleChatTo(View convertView, Message data){
        TextView tvName = getViewById(convertView,R.id.tv_name);
        TextView tvMsg = getViewById(convertView,R.id.tv_msg);
        tvName.setText(data.getName());
        tvMsg.setText(data.getMsg());
    }

    private void handleChatFrom(View convertView, Message data){
        TextView tvName = getViewById(convertView,R.id.tv_name);
        TextView tvMsg = getViewById(convertView,R.id.tv_msg);
        tvName.setText(data.getName());
        tvMsg.setText(data.getMsg());
    }
}
