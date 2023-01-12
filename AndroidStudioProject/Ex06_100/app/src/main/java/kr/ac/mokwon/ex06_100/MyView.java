package kr.ac.mokwon.ex06_100;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class MyView extends View
{
    Paint _paint, _framePaint;
    RectF _oval;
    float _start = 5, _sweep = 360;
    float _startSpeed = 1;
    float _sweepSpeed = 2;
    //_startSpeed = 15;
    // _sweepSpeed = 2;
    int check = 0;
    boolean isOpenEnd = false;

    public MyView(Context context)
    {
        super(context);
        _paint = new Paint();
        _paint.setAntiAlias(true);
        _paint.setStyle(Paint.Style.FILL);
        _paint.setColor(0x88ff0000);

        _framePaint = new Paint();
        _framePaint.setAntiAlias(true);
        _framePaint.setStyle(Paint.Style.STROKE);
        _framePaint.setColor(0x8800ff00);

        _oval = new RectF(40, 10, 290, 260);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.YELLOW);
        canvas.drawRect(_oval, _framePaint);
        canvas.drawArc(_oval, _start, _sweep, true, _paint);

        if(isOpenEnd == false)
        {
            _sweep = _sweep - (_startSpeed) * 2;
            _start = _start + _startSpeed;
            check++;
            if(check == 30)
            {
                check = 0;
                isOpenEnd = true;
            }

            /*
            _start += _sweepSpeed;
            if(_start > 30)
                _isOpenEnd = true;
             */
        }
        else
        {
            _sweep = _sweep + (_startSpeed) * 2;
            _start = _start - _startSpeed;
            check++;
            if(check == 30)
            {
                check = 0;
                isOpenEnd = false;
            }
            /*
            _start -= _sweepSpeed;
            if(_start <= 0)
                _isOpenEnd = true;
             */
        }
        // _sweep = 360 - 2 * start;
        invalidate();
    }
}