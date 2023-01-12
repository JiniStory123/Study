package kr.ac.mokwon.ch05_006;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View
{
    Paint p;
    Path path;

    public MyView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        p = new Paint();
        p.setStrokeWidth(50);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE); // 선 전용으로 쓰것다
        p.setStrokeJoin(Paint.Join.ROUND); // 부드러워져라
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawPath(path, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP :
                break;
        }
        invalidate();
        return true;
    }
}
