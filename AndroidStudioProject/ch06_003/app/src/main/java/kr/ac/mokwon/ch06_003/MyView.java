package kr.ac.mokwon.ch06_003;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View
{
    float _angle;
    public MyView(Context context)
    {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.rotate(_angle, getWidth()/2, getHeight()/2);
        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.kim);
        canvas.drawBitmap(map, 0, 0, null);
//        Matrix m = new Matrix();
//        m.preScale(0.5f, 0.5f);
//        Bitmap map2 = Bitmap.createBitmap(map, 0, 0, map.getWidth(), map.getHeight(), m, false);
//        canvas.drawBitmap(map2, 0, map.getHeight(), null);
    }

    public void SetAngle(float angle)
    {
        _angle = angle;
        invalidate();
    }
}