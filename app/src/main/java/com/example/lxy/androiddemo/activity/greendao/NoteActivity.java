package com.example.lxy.androiddemo.activity.greendao;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e.library.callback.EListener;
import com.e.library.dialog.ESingleChoseDialog;
import com.e.library.widget.ETitleBar;
import com.e.library.widget.decorator.EDividerDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.NoteAdapter;
import com.example.lxy.androiddemo.dao.DBHelper;
import com.example.lxy.androiddemo.dao.NoteDao;
import com.example.lxy.androiddemo.entity.Note;
import com.example.lxy.androiddemo.utils.RecycleViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/14.
 */
public class NoteActivity extends BaseSwipeBackActivity {


    @BindView(R.id.titlebar)
    ETitleBar titleBar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    private List<Note> notes = new ArrayList<>();
    private NoteAdapter adapter;
    private ESingleChoseDialog<String> dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycelerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        titleBar.setTitle(R.string.activity_note);
        titleBar.setForwardText("写一写");
        titleBar.showForwordTv();

        recyclerView.setHasFixedSize(false);
        RecycleViewUtils.setVerticalLayoutManager(this, recyclerView);
        adapter = new NoteAdapter(this, notes);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void OnInitListener() {
        super.OnInitListener();

        titleBar.setOnForwardListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AddNoteActivity.class);
            }
        });

        adapter.setOnItemClickListener(new EListener<Note>() {
            @Override
            public void onInvoked(View view, Note data, int position) {
                showDialog(data);
            }
        });
    }


    private void showDialog(final Note note) {
        if (dialog == null) {
            List<String> list = new ArrayList<>();
            list.add("删除");
            list.add("修改");
            dialog = ESingleChoseDialog.singleChoseListDialog(this, list, new EListener<String>() {
                @Override
                public void onInvoked(View view, String data, int position) {
                    if (position == 0) {
                        delete(note);
                    } else if (position == 1) {
                        toUpdate(note);
                    }
                }
            });
        }

        dialog.show();
    }

    @Override
    protected void onInitData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        NoteDao dao = DBHelper.getIns(this).getDaoSession().getNoteDao();
        List<Note> list = dao.loadAll();
        adapter.refreshDatas(list);
    }

    private void delete(Note note) {
        NoteDao dao = DBHelper.getIns(this).getDaoSession().getNoteDao();
        dao.delete(note);
        adapter.remove(note);
    }

    private void toUpdate(Note note) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("note",note);
        gotoActivity(AddNoteActivity.class,bundle);
    }

}
