package com.example.lxy.androiddemo.activity.greendao;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.e.library.widget.ETitleBar;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.dao.DBHelper;
import com.example.lxy.androiddemo.dao.NoteDao;
import com.example.lxy.androiddemo.entity.Note;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/14.
 */
public class AddNoteActivity extends BaseSwipeBackActivity {

    @BindView(R.id.titlebar)
    ETitleBar titleBar;
    @BindView(R.id.et_titile)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;

    private Note note;
    private NoteDao dao;
    private boolean isUpdate = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_note;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
    }

    @Override
    protected void OnInitListener() {
        super.OnInitListener();
        titleBar.setOnForwardListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdate) {
                    update();
                } else {
                    insert();
                }
            }
        });
    }

    @Override
    protected void onInitData() {

        dao = DBHelper.getIns(this).getDaoSession().getNoteDao();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isUpdate = true;
            titleBar.setTitle("修改");
            note = bundle.getParcelable("note");
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        } else {
            isUpdate = false;
            titleBar.setTitle("写一写");
        }
    }

    private boolean isVaild() {
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            shortToast("请输入标题");
            return false;
        }

        if (TextUtils.isEmpty(content)) {
            shortToast("请输入内容");
            return false;
        }

        return true;
    }

    private void insert() {

        if (!isVaild()) {
            return;
        }

        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        showLoading();
        Note note = new Note();
        note.setId(null);
        note.setTitle(title);
        note.setContent(content);
        note.setDate(System.currentTimeMillis());
        dao.insertOrReplace(note);
        etTitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                defaultFinish();
            }
        }, 1000);
    }

    private void update() {
        if (!isVaild()) {
            return;
        }
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        showLoading();
        note.setTitle(title);
        note.setContent(content);
        note.setDate(System.currentTimeMillis());
        dao.update(note);
        etTitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                defaultFinish();
            }
        }, 1000);


    }
}
