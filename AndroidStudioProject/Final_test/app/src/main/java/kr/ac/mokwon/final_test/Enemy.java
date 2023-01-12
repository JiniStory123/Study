package kr.ac.mokwon.final_test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Enemy
{
    MySurfaceView myView;
    int _x, _y;
    int _ySpeed;
    int _diameter;
    static int _combatX, _combatY;
    static int _score;
    static int WIDTH = 320;
    static int HEIGHT = 340;
    boolean _isGameOver;
    Paint p = new Paint();
    Paint scoreText = new Paint();

    public Enemy(int d)
    {
        _diameter = d;
        _x = (int)(Math.random()*(WIDTH - _diameter) + _diameter);
        _y = (int)(Math.random()*(WIDTH)-WIDTH);
        _score = 0;
        _isGameOver = false;
        p.setColor(Color.RED);
        p.setTextSize(50);
        scoreText.setColor(Color.BLACK);
        scoreText.setTextSize(20);

    }

    public int getY()
    {
        return _y;
    }

    public void setCombatX(int x)
    {
        _combatX = x;
    }

    public void setCombatY(int y)
    {
        _combatY = y;
    }

    public int getScore()
    {
        return _score;
    }

    public void Paint(Canvas c)
    {
        if(_isGameOver == false)
        {
            if(_y > HEIGHT)
            {
                _score += 10;
                _x = (int)(Math.random()*(WIDTH - _diameter) + _diameter);
                _y = (int)(Math.random()*(WIDTH)-WIDTH);
            }

            if((_combatX - 10 < _x && _combatX + 10 > _x) && (_combatY - 10 < _y && _combatY + 10 > _y))
            {
                _isGameOver = true;
            }
            _y += 2;

            c.drawCircle(_x, _y, _diameter, p);
        }
        else
        {
            c.drawText("GameOver", 10, 200, p);
        }
        c.drawText("Score : " + _score, 120, 20, scoreText);
    }


}
