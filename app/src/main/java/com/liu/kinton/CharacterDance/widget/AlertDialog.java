package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;

public class AlertDialog extends Dialog {
    private Button btnEnsure;
    private Button btnRejust;
    private ImageView ivContent;
    private TextView tvAlertMessage;

    public AlertDialog(@NonNull Context context) {
        super(context);
    }

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public AlertDialog setLayout(int layout){
        setContentView(layout);
        ButterKnife.bind(this);
        return this;
    }

}
