package kr.ac.mokwon.ex_06;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball
{
    int _x, _y;
    int _speedX = 1, _speedY = 1;
    int _diameter;
    static int WIDTH = 300;
    static int HEIGHT = 300;
    Paint p = new Paint();

    public Ball(int d)
    {
        _diameter = d; // 볼의 크기
        _x = (int)(Math.random()*(WIDTH - _diameter) + _diameter);
        _y = (int)(Math.random()*(WIDTH - _diameter) + _diameter); // (0,0)이나 (300, 300) 외각으로 못 벗어나게
//        _speedX = (int)(Math.random()*2 + 1);
        _speedX = 0;
        _speedY = 1;
        p.setColor(Color.WHITE);
    }

    public void Paint(Canvas c)
    {
//        if(_x > WIDTH || _x < 0)
//            _speedX *= -1;
        if(_y > (HEIGHT + 60) || _y < 1)
        {
            _speedY = 1;
            _x = (int)(Math.random()*(WIDTH - _diameter) + _diameter);
            _y = 0;
        }
        _x += _speedX;
//        _y += _speedY;
        _y += _speedY;
        c.drawCircle(_x, _y, _diameter, p);
    }
}