package com.liu.kinton.CharacterDance.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.liu.kinton.CharacterDance.R;
import com.liu.kinton.CharacterDance.adapter.FolderAdapter;
import com.liu.kinton.CharacterDance.base.ListItemListener;
import com.liu.kinton.CharacterDance.utils.AyscnUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FolderActivity extends AppCompatActivity implements ListItemListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refrash_layout)
    SwipeRefreshLayout layoutRefrash;
    @BindView(R.id.list_folder)
    RecyclerView filelist;

    private List<String> paths = new ArrayList<String>();
    private FolderAdapter mAdapter = null;
    private final  static String[] folderPaths = {Environment.getExternalStorageDirectory() + "/FunVideo_Pics"
                                                  ,Environment.getExternalStorageDirectory() + "/FunVideo_Video"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        ButterKnife.bind(this);
        initEvent();
        initList();
        initData();
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FolderActivity.this.finish();
            }
        });
        layoutRefrash.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initData() {
        layoutRefrash.setRefreshing(true);
        mAdapter.clearData();
        Observer<List<String>> observer  = new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<String> _paths) {
                mAdapter.addList(_paths);
                layoutRefrash.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        for(String path : folderPaths){
            AyscnUtils.getInstance().getAllFileListByPath(path,observer);
        }
    }

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        filelist.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        mAdapter = new FolderAdapter(this,paths,this);
        filelist.setAdapter(mAdapter);
        filelist.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,mAdapter.getData().get(position),Toast.LENGTH_SHORT).show();
    }
}
