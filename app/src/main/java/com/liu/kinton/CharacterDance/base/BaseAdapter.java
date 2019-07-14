package com.liu.kinton.CharacterDance.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<T> data;
    private final List<BaseListener> listeners;

    public BaseAdapter(BaseListener... listeners) {
        this.data = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.listeners.addAll(Arrays.asList(listeners));
    }

    public void setData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    public List<BaseListener> getListeners() {
        return listeners;
    }

    @Override
    public int getItemViewType(int position) {
        T o = data.get(position);
        for (int i = 0; i < listeners.size(); i++) {
            BaseListener listener = listeners.get(i);
            if (listener.isMyItemViewType(position, o)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return listeners.get(viewType).getMyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Object o = data.get(position);
        holder.bindView(position, o);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
