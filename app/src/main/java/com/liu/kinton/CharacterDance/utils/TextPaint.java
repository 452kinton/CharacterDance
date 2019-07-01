package com.liu.kinton.CharacterDance.utils;

import android.graphics.Color;
import android.graphics.Paint;

public class TextPaint extends Paint {
    public TextPaint(){
        super();
        this.setAntiAlias(true);
        this.setStrokeWidth(1);
        this.setColor(Color.BLACK);
        this.setTextSize(6);
    }
}
