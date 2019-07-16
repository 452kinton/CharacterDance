package com.liu.kinton.CharacterDance.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T,D extends BaseViewHolder> extends RecyclerView.Adapter<D> {
    private List<T> data;
    private Context context;
    private ListItemListener listener;

    public BaseAdapter(Context context, List<T> data,ListItemListener listener){
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    public ListItemListener getListener() {
        return listener;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void clearData(){
        this.data.clear();
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void addData(T item){
        this.data.add(item);
        notifyDataSetChanged();
    }

    public  void addList(List<T> items){
        this.data.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public D onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = View.inflate(viewGroup.getContext(),getLayoutByType(itemType),null);
        return getViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull D viewHolder, int i) {
        viewHolder.setData(data.get(i));
    }

    public abstract int getLayoutByType(int itemType);

    public abstract D getViewHodler(View view);


    @Override
    public int getItemCount() {
        return data.size();
    }
}
