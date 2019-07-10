package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.graphics.Bitmap;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.kinton.CharacterDance.R;
import com.liu.kinton.CharacterDance.utils.DialogUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AlertDialog extends Dialog {
    @BindView(R.id.iv_alert_choosePic)
    ImageView ivContent;
    @BindView(R.id.tv_alert_message)
    TextView tvAlertMessage;
    private Context context;
    private Unbinder unbinder;

    public AlertDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        setLayout(context);
        this.context = context;
        View view = getLayoutInflater().inflate(R.layout.dialog_alert, null);
        setContentView(view);
    }

    public AlertDialog setLayout(Context context) {
        return this;
    }


    @OnClick(R.id.btn_cancel)
    void onCancelClick() {
        this.dismiss();
    }

    DialogUtils.OnDialogBtnClickListener listener;


    @Override
    protected void onStart() {
        super.onStart();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public AlertDialog setOnBtnClickListener(DialogUtils.OnDialogBtnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @OnClick(R.id.btn_cancel)
    void cancelDialog() {
        this.dismiss();
    }

    public void onListenerDettach() {
        this.listener = null;
    }

    @OnClick(R.id.btn_ensure)
    void ensureClick(View view) {
        if (listener == null) {
            Log.i("ensureClick", "the dialog button clicked listener is not setted !!");
            return;
        }
        listener.OnBtnClick(view.getId());
    }

    public AlertDialog setMessage(String message) {
        tvAlertMessage.setText(message);
        return this;
    }

    public AlertDialog setPic(Bitmap bitmap) {
        ivContent.setImageBitmap(bitmap);
        return this;
    }

}
