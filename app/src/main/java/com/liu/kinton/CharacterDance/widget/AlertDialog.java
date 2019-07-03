package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.kinton.CharacterDance.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AlertDialog extends Dialog {
    private Button btnEnsure;
    private Button btnRejust;
    private ImageView ivContent;
    @BindView(R.id.tv_alert_message) TextView tvAlertMessage;
    private Context context;
    private Unbinder unbinder;

    public AlertDialog(@NonNull Context context) {
        super(context,R.style.dialog);
        setLayout(context);
        View view = getLayoutInflater().inflate(R.layout.dialog_alert,null);
        setContentView(view);
        ButterKnife.bind(this);
        //tvAlertMessage.setText("您确定选择这张图片吗？？");
    }

    public AlertDialog setLayout(Context context){
        this.context = context;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick(){
        this.dismiss();
    }

    @Override
    protected void onStart() {

    }
}
