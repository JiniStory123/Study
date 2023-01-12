package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class DrawButton extends View
{
    Bitmap _softKeyUp;
    Bitmap _softKeyDown;

    public DrawButton(Context context)
    {
        super(context);
    }

    public void Paint(Canvas c)
    {
        _softKeyUp = BitmapFactory.decodeResource(getResources(), R.drawable.arroy_up);
        _softKeyDown = BitmapFactory.decodeResource(getResources(), R.drawable.arroy_down);
        c.drawBitmap(_softKeyUp, 200, 428, null);
        c.drawBitmap(_softKeyDown, 260, 428, null);
    }
}
