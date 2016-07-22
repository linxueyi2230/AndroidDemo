package com.example.lxy.androiddemo.activity.chat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.e.library.widget.ETitleBar;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.ChatAdapter;
import com.example.lxy.androiddemo.entity.Message;
import com.example.lxy.androiddemo.utils.RecycleViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/22.
 */
public class ChatActivity extends BaseSwipeBackActivity {

    @BindView(R.id.titlebar)
    ETitleBar titleBar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private ChatAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycelerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        titleBar.setTitle("chat");
        recyclerView.setHasFixedSize(false);
        RecycleViewUtils.setVerticalLayoutManager(this, recyclerView);
    }

    @Override
    protected void onInitData() {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Message message = new Message();
            message.setMsg(String.format("hello item %s",i));
            int type = i % 2;
            message.setType(type);
            if (type == 0) {
                message.setName("chat to");
            } else {
                message.setName("chat from");
            }
            messages.add(message);
        }

        adapter = new ChatAdapter(this, messages);
        recyclerView.setAdapter(adapter);

    }
}
