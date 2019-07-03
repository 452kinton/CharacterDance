package com.liu.kinton.CharacterDance.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liu.kinton.CharacterDance.R;

public class RoundProgress extends View {

    private int progress = 0;
    private int startPosition = 0;
    private int width;
    private int height;

    private int progress_weight ;
    private int progress_color;
    private int text_size;
    private int text_color;
    private Paint mPaint;
    private RectF oval;
    LinearGradient linearGradient;
    private boolean isRun = false;

    Handler mhandler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    //重新绘制界面
                    invalidate();//告诉UI主线程重新绘制
                    mhandler.sendEmptyMessageDelayed(0x001, 15);
                    break;

                default:
                    break;
            }
        }
    };


        public RoundProgress(Context context) {
        super(context);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        progress_color = arr.getColor(R.styleable.RoundProgress_progress_color, Color.BLUE);
        progress_weight = arr.getDimensionPixelSize(R.styleable.RoundProgress_progress_line_weight,8);
        text_size= arr.getDimensionPixelSize(R.styleable.RoundProgress_progress_text_size,16);
        text_color = arr.getColor(R.styleable.RoundProgress_progress_text_color,Color.BLACK);

        arr.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mhandler.sendEmptyMessageDelayed(0x001, 15);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onDraw(Canvas canvas) {
        if(oval == null){
            int shorter = getWidth()>=getHeight()?getHeight():getWidth();
            oval = new RectF( (getWidth()-shorter+16)/2, (getHeight()-shorter+16)/2,
                    (getWidth()+shorter-16)/2, (getHeight()+shorter-16)/2);
        }
        mPaint.setColor(progress_color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progress_weight);
        canvas.drawArc(oval, startPosition,  120, false, mPaint);

        mPaint.setTextSize(text_size);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(text_color);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setLinearText(true);
        float left_add_rate =(float) (progress+"%").length()/2;
        canvas.drawText(progress + "%", (getWidth()-(float)text_size*left_add_rate) / 2,  (getHeight()+(float)text_size/2) / 2, mPaint);
        startPosition += 6;

    }


    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       // Log.i("onMeasure","start");

        width  = measureDimension(75,widthMeasureSpec);
        height  = measureDimension(75,heightMeasureSpec);

        setMeasuredDimension(width, height);
        //Log.i("onMeasure","end");
    }

    public void startProgress(){
        mhandler.sendEmptyMessageDelayed(0x001, 1000);
    }

    public int measureDimension(int defaultSize, int measureSpec){
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //Log.i("specMode",specMode+"");
        //Log.i("specSize",specSize+"");

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = defaultSize;   //UNSPECIFIED
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.max(result, specSize);
            }

        }
        //Log.i("result",result+"");
        return result;
    }

}
