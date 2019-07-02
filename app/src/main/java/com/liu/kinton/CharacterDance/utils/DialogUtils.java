package com.liu.kinton.CharacterDance.utils;

import android.content.Context;

import com.liu.kinton.CharacterDance.widget.AlertDialog;

public class DialogUtils {
    public interface  OnDialogBtnClickListener{
        void OnBtnClick(int itemId);
    }

    public static AlertDialog createAlertDialogWithText(Context context,OnDialogBtnClickListener listener){
        AlertDialog alertDialog = new AlertDialog(context)
                .setOnBtnClickListener(listener);
        return alertDialog;
    };

}
