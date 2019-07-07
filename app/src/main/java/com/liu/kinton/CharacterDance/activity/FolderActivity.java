package com.liu.kinton.CharacterDance.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.liu.kinton.CharacterDance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FolderActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refrash_layout)
    SwipeRefreshLayout layoutRefrash;
    @BindView(R.id.list_folder)
    RecyclerView filelist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        ButterKnife.bind(this);
        initList();
    }

    private void initList() {
    }
}
