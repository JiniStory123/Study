package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class BackgroundImg extends View
{
    Bitmap _backgroundImg;

    public BackgroundImg(Context context)
    {
        super(context);
    }

    public void Paint(Canvas c)
    {
        _backgroundImg = BitmapFactory.decodeResource(getResources(), R.drawable.road);

        c.drawBitmap(_backgroundImg, 0, 0, null);
    }
}
