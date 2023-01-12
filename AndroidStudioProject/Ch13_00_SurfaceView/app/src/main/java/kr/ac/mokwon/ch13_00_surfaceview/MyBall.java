package kr.ac.mokwon.ch13_00_surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyBall
{
    int _x, _y, _xSpeed, _ySpeed;
    int _diameter;
    Paint paint = new Paint();

    public MyBall()
    {
        _diameter = 10;
        _xSpeed = 3;
        _ySpeed = 3;
        _x = 10;
        _y = 200;
    }

    public void paint(Canvas c)
    {
        _x += _xSpeed;
        _y += _ySpeed;
        if(_x < 0 || _x > 300)
        {
            _xSpeed = -_xSpeed;
        }
        if(_y < 0 || _y > 300)
        {
            _ySpeed = -_ySpeed;
        }
        paint.setColor(Color.RED);
        c.drawCircle(_x, _y, _diameter, paint);
    }
}
