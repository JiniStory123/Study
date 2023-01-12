package kr.ac.mokwon.final_test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Combat
{
    int _x, _y;
    int _diameter;
    int _speed;
    boolean _isGameOver;
    static int WIDTH = 320;
    static int HEIGHT = 340;
    public Paint p = new Paint();

    public Combat(int d)
    {
        _x = 160;
        _y = 310;
        _speed = 0;
        _diameter = d;
        _isGameOver = false;
        p.setColor(Color.BLUE);
    }

    public void setX(int x)
    {
        if(!_isGameOver)
            _x = x;
    }

    public int getX()
    {
        return _x;
    }

    public int getY()
    {
        return _y;
    }

    public void Paint(Canvas c)
    {
        if(_x < 0)
            _x = 0;
        if(_x > WIDTH)
            _x = WIDTH;

        switch (_speed)
        {
            case -1 :
                _x -= 2;
                break;
            case 0 :
                break;
            case 1 :
                _x += 2;
                break;
        }

        c.drawCircle(_x, _y, _diameter, p);
    }
}