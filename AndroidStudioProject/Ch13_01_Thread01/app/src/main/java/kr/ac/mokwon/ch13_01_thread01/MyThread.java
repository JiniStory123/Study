package kr.ac.mokwon.ch13_01_thread01;

import android.util.Log;

public class MyThread extends Thread
{
    public boolean isRunning;
    @Override
    public void run()
    {
        for(int i=0; i<20 && isRunning; i++)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            Log.i("Thread", i+"");
        }
    }
}