package com.liu.kinton.CharacterDance.utils;

import android.content.Context;

import com.liu.kinton.CharacterDance.widget.AlertDialog;
import com.liu.kinton.CharacterDance.widget.ProgressDialog;
import com.liu.kinton.CharacterDance.widget.ShowPhotoDialog;
import com.liu.kinton.CharacterDance.widget.VideoDialog;

public class DialogUtils {
    public interface  OnDialogBtnClickListener{
        void OnBtnClick(int itemId);
    }

    public static AlertDialog createAlertDialogWithText(Context context,OnDialogBtnClickListener listener){
        AlertDialog alertDialog = new AlertDialog(context)
                .setOnBtnClickListener(listener);
        return alertDialog;
    }

    public static ProgressDialog createProgressDialogWithText(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
               // .setOnBtnClickListener(listener);
        return progressDialog;
    }

    public static ShowPhotoDialog createShowPhotoDialog(Context context){
        ShowPhotoDialog showPhotoDialog = new ShowPhotoDialog(context);
        return showPhotoDialog;
    }

    public static VideoDialog createVideoDialog(Context context,String path){
        VideoDialog videoDialog = new VideoDialog(context,path);
        return videoDialog;
    }




}
