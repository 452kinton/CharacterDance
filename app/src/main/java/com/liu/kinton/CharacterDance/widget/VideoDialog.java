package com.liu.kinton.CharacterDance.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.liu.kinton.CharacterDance.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VideoDialog extends Dialog {
    private Unbinder unbinder;
    @BindView(R.id.vv_show)
    VideoView vvShow;
    @BindView(R.id.iv_video_status)
    ImageView ivStatus;
    private Context context;
    private MediaController mediaController;
    private boolean initSuccess = false;
    private boolean isPlaying = false;

    private int currentPosition = -1;

    public VideoDialog(@NonNull Context context,String path) {
        super(context, R.style.dialog);
        View view = getLayoutInflater().inflate(R.layout.dialog_video,null);
        setContentView(view);
        unbinder = ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.context = context;
        Log.i("VideoDialog","init player");
        initSuccess = initVideo(path);
    }
    @OnClick(R.id.vv_show)
    void onVideoClick(){
        if(isPlaying){
            currentPosition = vvShow.getCurrentPosition();
            vvShow.pause();
            ivStatus.setVisibility(View.VISIBLE);
            isPlaying = false;
        }else {
            if(currentPosition <0) currentPosition =0 ;
            vvShow.seekTo(currentPosition);
            vvShow.start();
            ivStatus.setVisibility(View.GONE);
            isPlaying = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean initVideo(String path){
        File file = new File(path);
       // Log.i("video path",path);
        vvShow.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ivStatus.setVisibility(View.VISIBLE);
                isPlaying = false;
                currentPosition =-1;
            }
        });
        if(file.exists()){
          //  Log.i("video exists","exists!");
            Uri uri = Uri.fromFile(file);
            mediaController = new MediaController(context);
            vvShow.setMediaController(mediaController);
            vvShow.setVideoURI(uri);
            return true;
        }else{
           // Log.i("video exists"," not exists!");
            return false;
        }
    }

    @OnClick(R.id.iv_back)
    void onBackbtnClick(){
        this.dismiss();
    }

    @Override
    public void show() {
        super.show();
    }

    public void startVideo(){
        if(initSuccess){
            Log.i("start video","start");
            vvShow.start();
        }
        isPlaying = true;
    }

    public void stopVideo(){
        if(initSuccess){
            vvShow.stopPlayback();
        }
    }

}
