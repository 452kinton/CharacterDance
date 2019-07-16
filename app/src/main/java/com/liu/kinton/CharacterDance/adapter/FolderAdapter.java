package com.liu.kinton.CharacterDance.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.liu.kinton.CharacterDance.R;
import com.liu.kinton.CharacterDance.base.BaseAdapter;
import com.liu.kinton.CharacterDance.base.ListItemListener;


import java.util.List;

public class FolderAdapter extends BaseAdapter<String,FolderHolder> {

    public FolderAdapter(Context context, List<String> data, ListItemListener listener) {
        super(context, data,listener);
    }

    @Override
    public int getLayoutByType(int itemType) {
        return R.layout.layout_item_file;
    }

    @Override
    public FolderHolder getViewHodler(View view) {
        FolderHolder folderHolder = new FolderHolder(view,getContext(),getListener());
        return folderHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder folderHolder, int position) {
        folderHolder.clearAll();
        folderHolder.setData(getData().get(position));
    }
}
