package kr.ac.mokwon.frogontheroad;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Time
{
    // 시계의 위치
    int _x, _y;

    // 시 분 초
    int _hour;
    int _minute;
    int _second;

    // 초를 체크할 변수
    int _intSecond;

    Paint p;

    Time()
    {
        _x = 10;
        _y = 10;
        p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(30);
    }

    public void Paint(Canvas c)
    {
        if(_intSecond >= 60)
        {
            _second++;
            _intSecond -= 60;
        }
        if(_second >= 60)
        {
            _minute++;
            _second -= 60;
        }
        if(_minute >= 60)
        {
            _hour++;
            _minute -= 60;
        }

        c.drawText(_hour +":"+_minute+":"+_second, 210, 25, p);
    }
}
