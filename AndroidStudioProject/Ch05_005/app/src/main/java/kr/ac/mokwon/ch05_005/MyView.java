package kr.ac.mokwon.ch05_005;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View
{
    int x, y;
    String str;
    Paint p;

    public MyView(Context context)
    {
        super(context);
        setBackgroundColor(Color.YELLOW);
        p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(25);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawCircle(x, y, 50, p);
        canvas.drawText("액션 종류 : "+ str, 0, 50, p);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        x = (int)event.getX();
        y = (int)event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN)
            str = "down";
        else if(event.getAction() == MotionEvent.ACTION_MOVE)
            str = "move";
        else if(event.getAction() == MotionEvent.ACTION_UP)
            str = "up";

        invalidate();
        return true;
    }

}