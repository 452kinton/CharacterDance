package com.liu.kinton.CharacterDance.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.kinton.CharacterDance.R;
import com.liu.kinton.CharacterDance.base.BaseViewHolder;
import com.liu.kinton.CharacterDance.base.ListItemListener;
import com.liu.kinton.CharacterDance.utils.AyscnUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FolderHolder extends BaseViewHolder<String> {
    private ImageView imageView;
    private TextView textView;
    private Context context;

    public FolderHolder(@NonNull View itemView, Context context, ListItemListener listener) {
        super(itemView,listener);
        this.context = context;
        this.imageView = (ImageView) this.getViewById(R.id.iv_listitem_file_pic);
        this.textView = (TextView) this.getViewById(R.id.tv_listitem_path);
    }

    @Override
    public void setData(String data) {
        String[] paths = data.split("/");
        textView.setText(paths[paths.length-1]);
        AyscnUtils.getInstance().getBitmapByPath(context,data,new Observer<Bitmap>(){
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void clearAll() {
        imageView.setImageDrawable(null);
        textView.setText("");
    }
}
