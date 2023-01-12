package kr.ac.mokwon.ch06_004;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class MyThread extends Thread
{
    Ball[] _balls;
    SurfaceHolder _holder;
    KnobImg _img;
    boolean _isRun; // isRun이 true일때만 Thread run

    public MyThread(SurfaceHolder holder, Context context)
    {
        _holder = holder;
        _img = new KnobImg(context);
    }

    public void SetBalls(Ball[] balls)
    {
        _balls = balls;
    }

    public void SetRun(boolean b)
    {
        _isRun = b;
    }

    @Override
    public void run()
    {
        while(_isRun)
        {
            Canvas c = null;
            try
            {
                c = _holder.lockCanvas(null);
                c.drawColor(Color.BLACK);
                synchronized (_holder)
                {
                    _img.Paint(c);
                    for(int i=0; i<_balls.length; i++)
                    {
                        _balls[i].Paint(c);
                    }
                }
            } finally
            {
                if(c != null)
                    _holder.unlockCanvasAndPost(c);
            }
        }
    }
}
