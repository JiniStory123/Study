package kr.ac.mokwon.ch06_004;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    MyThread _thread;
    Ball[] _balls;

    public MySurfaceView(Context context)
    {
        super(context);
        _balls = new Ball[10];
        for(int i=0; i<_balls.length; i++)
            _balls[i] = new Ball(10);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        _thread = new MyThread(holder, context);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder)
    {
        //_thread = new MyThread(surfaceHolder);
        _thread.SetRun(true);
        _thread.SetBalls(_balls);
        _thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder)
    {
        _thread.SetRun(false);
        boolean retry = true;
        while(retry)
        {
            try
            {
                _thread.join();
                retry = false;
            } catch(InterruptedException e)
            {

            }
        }
    }
}