package kr.ac.mokwon.ch06_004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        MySurfaceView view = new MySurfaceView(this);
        setContentView(view);
    }
}