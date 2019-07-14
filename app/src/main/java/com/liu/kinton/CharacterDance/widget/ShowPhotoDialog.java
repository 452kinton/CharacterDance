package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.liu.kinton.CharacterDance.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShowPhotoDialog extends Dialog {
    @BindView(R.id.iv_show)
    ImageView ivShow;

    private Unbinder unbinder;

    public ShowPhotoDialog(@NonNull Context context) {
        super(context);
        View view = getLayoutInflater().inflate(R.layout.dialog_photo,null);
        setContentView(view);
        unbinder = ButterKnife.bind(this);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    public void setPiscByBitmap(Bitmap bitmap){
        ivShow.setImageBitmap(bitmap);
    }

    @OnClick(R.id.iv_back)
    void onBackClick(View view){
        this.dismiss();
    }


}
