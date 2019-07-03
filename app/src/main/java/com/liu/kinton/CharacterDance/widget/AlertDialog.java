package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
<<<<<<< HEAD
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
=======
import android.graphics.Bitmap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

>>>>>>> e9aba17cbd59d4d445c03439e88646327c7d1d7b
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.kinton.CharacterDance.R;
<<<<<<< HEAD
=======
import com.liu.kinton.CharacterDance.utils.DialogUtils;
>>>>>>> e9aba17cbd59d4d445c03439e88646327c7d1d7b

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AlertDialog extends Dialog {
<<<<<<< HEAD
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
=======
    @BindView(R.id.iv_alert_choosePic)
    ImageView ivContent;
    @BindView(R.id.tv_alert_message)
    TextView tvAlertMessage;

    private Unbinder unbinder;

    private Context context;

    DialogUtils.OnDialogBtnClickListener listener;

    public AlertDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        this.context = context;
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view  = getLayoutInflater().inflate(R.layout.dialog_alert,null);
        setContentView(view);
        unbinder = ButterKnife.bind(this,view);
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
>>>>>>> e9aba17cbd59d4d445c03439e88646327c7d1d7b
    }

    @Override
    protected void onStart() {

    }
}
