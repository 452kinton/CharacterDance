package com.liu.kinton.CharacterDance.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.View;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public View itemView;
    private ListItemListener listener = null;

    public ListItemListener getListener() {
        return listener;
    }

    public BaseViewHolder(@NonNull View itemView, ListItemListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getListener() != null){
                    getListener().onItemClick(BaseViewHolder.this.getAdapterPosition());
                }
            }
        });
    }

    public View getViewById(int viewId){
      return   itemView.findViewById(viewId);
    }

    abstract public  void setData(T data);

    abstract public void clearAll();

}
