package com.liu.kinton.CharacterDance.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.liu.kinton.CharacterDance.ffmpegUtils.CmdList;
import com.liu.kinton.CharacterDance.ffmpegUtils.FFmpegCmd;
import com.liu.kinton.CharacterDance.ffmpegUtils.FFmpegUtil;
import com.liu.kinton.CharacterDance.ffmpegUtils.OnVideoProcessListener;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AyscnConvertUtils {

    private static class SingletonClassInstance {
        private static final AyscnConvertUtils instance = new AyscnConvertUtils();
    }

    private AyscnConvertUtils() {
    }

    public interface Progresslistener {
        void onProgress(Integer progress);

        void onCompelete();
    }

    public static AyscnConvertUtils getInstance() {
        return SingletonClassInstance.instance;
    }

    public void startConvertVideo(final Context context, final String path, final Progresslistener listener) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(0);
                VideoUtils.VideoInfo vi = VideoUtils.getVideoInfo(context, path);
                Log.i("startConvertVideo", "width:" + vi.width + " height:" + vi.height + " time:" + vi.time);

                emitter.onNext(10);
                String framePath = FileUtils.createDri("FunVideo_CachePic_Source");

                emitter.onNext(30);


                if (VideoUtils.initFrameFromVideoBySecond(context, framePath, path, vi.width, vi.height, vi.time)) {
                    emitter.onNext(50);
                    VideoUtils.convertVideoBySourcePics(context, framePath);
                    emitter.onComplete();
                    FileUtils.deleteFile(new File(framePath));
                } else {

                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        listener.onProgress(integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        listener.onCompelete();
                    }
                });

    }

    public void startConvertPic(final Context context, final Uri picUri, final Progresslistener listener) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(0);
                Bitmap bitmap = BitmapUtils.getBitmapByUri(context, picUri);
                float scale = (float) bitmap.getWidth() / bitmap.getHeight();
                float xScale = (float) 200 / bitmap.getWidth();
                Log.i("scale", "x:" + xScale + "  y:" + scale);
                bitmap = BitmapUtils.compressBitmap(bitmap, xScale, xScale);
                //emitter.onNext(bitmap);
                emitter.onNext(20);
                int[][] garyDatas = BitmapUtils.getBitmap2GaryArray(bitmap);
                emitter.onNext(60);
                bitmap = BitmapUtils.array2Bitmap(garyDatas, bitmap.getWidth(), bitmap.getHeight());
                emitter.onNext(80);
                BitmapUtils.addGraphToGallery(context, bitmap, "FunVideo_Pics",true);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        listener.onProgress(integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        listener.onCompelete();
                    }
                });
    }

}

