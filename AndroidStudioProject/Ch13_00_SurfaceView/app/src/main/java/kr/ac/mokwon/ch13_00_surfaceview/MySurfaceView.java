package kr.ac.mokwon.ch13_00_surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    MyBall myBall;
    MyThread myThread;

    public MySurfaceView(Context context)
    {
        super(context);
        myBall = new MyBall();

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        myThread = new MyThread(holder);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder)
    {
        myThread._isRunning = true;
        myThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder)
    {
        myThread._isRunning = false;
        boolean retry;
        try
        {
            myThread.join();
            retry = false;
        } catch (InterruptedException e) {}
    }

    class MyThread extends Thread
    {
        boolean _isRunning;
        SurfaceHolder _holder;

        public MyThread(SurfaceHolder holder)
        {
            _holder = holder;
        }

        @Override
        public void run()
        {
            while(_isRunning)
            {
                Canvas c = null;

                try
                {
                    c = _holder.lockCanvas(null);
                    c.drawColor(Color.BLACK);
                    synchronized (_holder)
                    {
                        myBall.paint(c);
                    }
                } finally
                {
                    if (c != null)
                    {
                        _holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}