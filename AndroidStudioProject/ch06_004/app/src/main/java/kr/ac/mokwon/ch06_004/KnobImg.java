package kr.ac.mokwon.ch06_004;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class KnobImg extends View
{
    Bitmap _bit;
    public KnobImg(Context context)
    {
        super(context);
        _bit = BitmapFactory.decodeResource(getResources(), R.drawable.mo);
    }

    public void Paint(Canvas c)
    {
        c.drawBitmap(_bit, 0, 0, null);
    }
}
