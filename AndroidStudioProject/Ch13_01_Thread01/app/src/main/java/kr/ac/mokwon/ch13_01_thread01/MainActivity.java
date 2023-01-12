package kr.ac.mokwon.ch13_01_thread01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    MyThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        thread = new MyThread();
        thread.isRunning = true;
        thread.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if(thread != null)
        {
            boolean retry = true;
            thread.isRunning = false;
            while(true)
            {
                try
                {
                    Log.i("Thread", "join");
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {}
            }
        }
    }
}