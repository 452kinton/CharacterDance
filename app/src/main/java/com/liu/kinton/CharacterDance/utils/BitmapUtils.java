package com.liu.kinton.CharacterDance.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class BitmapUtils {

    static String[] arr = {"餮", "淼", "圆", "困", "品", "回", "田", "凸", "口", "王", "天", "干", "工", "十", "一"};

    static public Bitmap getBitmapByUri(Context context, Uri uri) {
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
            Log.i("utils_getBitmapByUri", "" + bit.getWidth() + "," + bit.getHeight());
        } catch (Exception ex) {
            Log.i("utils", "" + ex.getMessage());
        }
        return bit;
    }

    static public Bitmap compressBitmap(Bitmap bitmap, float sx, float sy) {
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy);
        Log.i("utils_compressBitmap", "" + sx + "," + sy);
        Bitmap bit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);

        Log.i("utils_compressBitmap", "" + bit.getWidth() + "," + bit.getHeight());
        bitmap.recycle();
        return bit;
    }

    static public int[][] getBitmap2GaryArray(Bitmap bitmap) {
        int width = bitmap.getWidth();            //获取位图的宽
        int height = bitmap.getHeight();        //获取位图的高
        int[][] datas = new int[width][height];    //通过位图的大小创建像素点数组
        //bitmap.getPixels(datas, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int grey = bitmap.getPixel(i, j);
                int red = (grey & 0x00ff0000) >> 16; //取高两位
                int green = (grey & 0x0000ff00) >> 8; //取中两位
                int blue = grey & 0x000000ff; //取低两位

                grey = (int) ((float) red*0.4 + (float) green*0.3+ (float) blue*0.3);
                datas[i][j] = grey;
            }
        }
        return datas;
    }

    static public Bitmap array2Bitmap(int[][] garyDatas, int width, int height) {
        Bitmap whiteBgBitmap = Bitmap.createBitmap(width * 6 + 20, height * 6 + 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(whiteBgBitmap);
        canvas.drawARGB(255, 255, 255, 255);
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(6);

        int x = 0;
        for (int xIndex = 10; x <width; xIndex += 6) {
            int y = 0;
            for (int yIndex = 10; y<height; yIndex += 6) {

                int charIndex = garyDatas[x][y] / 18;
                String _char = arr[charIndex];
                canvas.drawText(_char, xIndex, yIndex, mPaint);
                y++;
            }
            x++;
        }
        Log.i("array2Bitmap", "drawed");
        return whiteBgBitmap;
    }

    static public Bitmap array2Bitmap(int[] garyDatas, int[] colors, int width, int height) {
        Bitmap mBackgroundBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Bitmap whiteBgBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(whiteBgBitmap);
        canvas.drawColor(Color.WHITE);

        canvas.drawBitmap(mBackgroundBitmap, 0, 0, null);
        return whiteBgBitmap;
    }

    static public boolean addGraphToGallery(Context context, Bitmap bmp,String dirName,boolean need2Gallery) {
        boolean result = false;
        try {
            File photo = new File(Environment.getExternalStorageDirectory() + "/"+dirName, String.format("FunPic_%d.jpg",
                    System.currentTimeMillis()));

            File dir = new File(photo.getParent());

            dir.mkdirs();

            photo.createNewFile();

            Log.i("addGraphToGallery", "created file");
            saveBitmapToJPG(bmp, photo);
            Log.i("addGraphToGallery", "saved");
            if(need2Gallery) {
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(photo);
                mediaScanIntent.setData(contentUri);
                context.sendBroadcast(mediaScanIntent);
                Log.i("addGraphToGallery", "ok");
            }
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("addGraphToGallery", e.getMessage());
        }
        return result;
    }

    static private void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        OutputStream stream = new FileOutputStream(photo);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
        bitmap.recycle();
    }

    static private File getAlbumStorageDir(String albumName) throws IOException {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStorageDirectory(), albumName);
        if (!file.createNewFile()) {
            Log.e("bitmaputil", "Directory not created");
        }
        return file;
    }
}
