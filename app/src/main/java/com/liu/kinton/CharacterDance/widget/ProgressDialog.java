package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.liu.kinton.CharacterDance.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgressDialog extends Dialog {
    @BindView(R.id.progress_view)
    RoundProgress roundProgress;
    private Unbinder unbinder;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        View view = getLayoutInflater().inflate(R.layout.dialog_progress,null);
        setContentView(view);
        this.setCanceledOnTouchOutside(false);
    }

    public void setProgress(int progress){
        roundProgress.setProgress(progress);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        unbinder.unbind();
    }
}
