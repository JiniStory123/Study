package kr.ac.mokwon.ex_06;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    ImgView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        imgView = findViewById(R.id.img);
        MySurfaceView view = new MySurfaceView(this);
        setContentView(view);
    }
}