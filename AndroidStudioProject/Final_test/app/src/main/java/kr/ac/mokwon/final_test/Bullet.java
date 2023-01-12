package kr.ac.mokwon.final_test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bullet
{
    Combat combat;
    int _x, _y;
    int _diameter;
    static int HEIGHT = 340;
    Paint p = new Paint();

    public Bullet(int d)
    {
        _diameter = d;
        _x = combat.getX();
        _y = 310;
        p.setColor(Color.BLACK);
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
        _y -= 5;

        c.drawCircle(_x, _y, _diameter, p);
    }
}
