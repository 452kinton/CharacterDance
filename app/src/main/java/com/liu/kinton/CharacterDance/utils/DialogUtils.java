package com.liu.kinton.CharacterDance.utils;

import android.content.Context;

import com.liu.kinton.CharacterDance.widget.AlertDialog;
import com.liu.kinton.CharacterDance.widget.ProgressDialog;

public class DialogUtils {
    public interface  OnDialogBtnClickListener{
        void OnBtnClick(int itemId);
    }

    public static AlertDialog createAlertDialogWithText(Context context,OnDialogBtnClickListener listener){
        AlertDialog alertDialog = new AlertDialog(context)
                .setOnBtnClickListener(listener);
        return alertDialog;
    };

    public static ProgressDialog createProgressDialogWithText(Context context){
        ProgressDialog alertDialog = new ProgressDialog(context);
               // .setOnBtnClickListener(listener);
        return alertDialog;
    };

}
