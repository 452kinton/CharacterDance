package com.liu.kinton.CharacterDance.ffmpegUtils;

public interface OnVideoProcessListener {

    public void onProcessStart();

    public void onProcessProgress(float progress);

    public void onProcessSuccess();

    public void onProcessFailure() ;
}
