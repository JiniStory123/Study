package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DrawLife extends View
{
    // 라이프 아이콘
    Bitmap _frogLife;

    // 화면 크기
    static int WIDTH = 310;
    static int HEIGHT = 480;

    // 라이프를 그릴 위치
    int _x, _y;

    // 개구리 라이프
    int _life;

    // 페인트
    Paint paint_life;

    public DrawLife(Context context)
    {
        super(context);
        _frogLife = BitmapFactory.decodeResource(getResources(), R.drawable.frog_heart);
        _x = 230;
        _y = 2;
        _life = 3;
        paint_life = new Paint();
    }

    public void Paint(Canvas c)
    {
        for(int i=0; i<_life; i++)
        {
            c.drawBitmap(_frogLife, _x + (i * 30), _y, null);
        }
    }

    // getter, setter

    public int get_life()
    {
        return _life;
    }

    public void set_life(int _life)
    {
        this._life = _life;
    }
}