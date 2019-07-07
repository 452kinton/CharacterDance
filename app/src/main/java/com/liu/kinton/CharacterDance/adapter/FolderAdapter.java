package com.liu.kinton.CharacterDance.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {
    public static class FolderViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvTitle = null;
        public final ImageView ivPic = null;

        public FolderViewHolder(View v) {
            super(v);
        }
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder folderViewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
