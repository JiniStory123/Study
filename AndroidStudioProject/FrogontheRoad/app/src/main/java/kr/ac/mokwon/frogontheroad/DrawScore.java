package kr.ac.mokwon.frogontheroad;

import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawScore
{
    // 화면 크기
    static int WIDTH = 310;
    static int HEIGHT = 480;

    // 스코어를 그릴 위치
    int _x, _y;

    // 점수
    int _score;

    // 페인트
    Paint paint_score;

    DrawScore()
    {
        _x = 80;
        _y = 100;
        _score = 0;

        paint_score = new Paint();
        paint_score.setTextSize(25);
    }

    void Paint(Canvas c)
    {
        c.drawText("Your Score : " + _score, _x, _y, paint_score);
    }

    public int get_score()
    {
        return _score;
    }

    public void set_score(int _score)
    {
        this._score = _score;
    }
}
