package kr.ac.mokwon.ex_06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class MyThread extends Thread
{
    Ball[] _balls;
    ImgView map;
    SurfaceHolder _holder;
    boolean _isRun; // isRun이 true일때만 Thread run

    public MyThread(SurfaceHolder holder)
    {
        _holder = holder;
       // map = BitmapFactory.decodeResource(R.drawable.mo);
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
                map.Paint(c);
                synchronized (_holder)
                {

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
