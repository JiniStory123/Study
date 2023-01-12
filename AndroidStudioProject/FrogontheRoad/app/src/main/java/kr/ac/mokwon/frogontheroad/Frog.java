package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Frog extends View
{
    // 화면 크기
    static int WIDTH = 310;
    static int HEIGHT = 480;

    // 개구리 위치 제한
    int _top = 60;
    int _bottom = 420;

    // 개구리 위치
    static int _x;
    static int _y;

    // 개구리 스피드
    static int _speed;

    // 개구리 방향
    static boolean _isDown;

    // 실시간으로 개구리의 최대 이동 거리를 계산할 변수
    static int _maxY;

    // 화면 터치 제어 변수
    static int _isTouch;

    // (개구리를 원으로 표현할 시) 자동차 반지름
    private int _diameter;

    // 개구리 그림 불러오기
    Bitmap _frogImg;

    // 점수
    private int _score;

    // 게임오버 유무
    private boolean _isGameOver;

    // 페인트
    Paint paint_frog;
    Paint paint_frogY;
    Paint paint_score;
    Paint paint_coin;

    // 테스트용 변수들
    int _touchX, _touchY; // 터치한 곳의 좌표 테스트용
    Paint paint_test; // 터치한 곳의 좌표를 그릴 페인트

    // 이스터 에그용
    String str_userName;

    public void DefaultSetting()
    {
        _x = WIDTH / 2;
        _y = 450;
        _speed = 0;
        _isDown = false;
        _score = 0;
        _isGameOver = false;

        _frogImg = BitmapFactory.decodeResource(getResources(), R.drawable.frog_up);

        _maxY = _bottom;

        paint_frog = new Paint();
        paint_frog.setColor(Color.GREEN);

        paint_frogY = new Paint();
        paint_frogY.setTextSize(20);

        paint_score = new Paint();
        paint_score.setTextSize(20);

        paint_test = new Paint();
        paint_test.setTextSize(20);

        paint_coin = new Paint();
        paint_coin.setColor(Color.YELLOW);
    }

    public Frog(Context context)
    {
        super(context);
        DefaultSetting();
    }

    public Frog(Context context, int _diameter)
    {
        super(context);
        this._diameter = _diameter;
        DefaultSetting();
    }

    void Paint(Canvas c)
    {
        // 개구리가 상단 화면을 벗어나려고 하면
        if(_y <= 0)
        {
            _y = 0;
        }

        // 개구리가 하단 화면을 벗어나려고 하면
        if(_y >= HEIGHT)
        {
            _y = HEIGHT;
        }

        // 개구리가 위로 올라가야 하면
        if(_isDown == false)
        {
            c.drawCircle(WIDTH/2, 30, 10, paint_coin);
        }

        // 개구리가 아래로 내려가야 하면
        if(_isDown == true)
        {
            c.drawCircle(WIDTH/2, 450, 10, paint_coin);
        }

        // 개구리가 화면 상단까지 갔다
        if(_isDown == false && _y <= 30)
        {
            _maxY = _top;
            _isDown = true;
            _score += 1000;
        }

        // 개구리가 화면 하단까지 갔다
        if(_isDown == true && _y >= 450)
        {
            _maxY = _bottom;
            _isDown = false;
            _score += 1000;
        }

        switch(_speed)
        {
            case 1:
                _frogImg = BitmapFactory.decodeResource(getResources(), R.drawable.frog_up);
                _y -= 2;
                if(_y >= _top && _y <= _bottom)
                {
                    if(_isDown == false)
                    {
                        if(_y < _maxY)
                        {
                            _maxY = _y;
                            _score += 1;
                        }
                    }
                }
                break;
            case 0:
                break;
            case -1:
                _frogImg = BitmapFactory.decodeResource(getResources(), R.drawable.frog_down);
                _y += 2;
                if(_isDown == true)
                {
                    if(_y > _maxY)
                    {
                        _maxY = _y;
                        _score += 1;
                    }
                }
                break;
        }

//        c.drawCircle(_x, _y, _diameter, paint_frog);
        c.drawBitmap(_frogImg, _x-10, _y-10, null);
        c.drawText("score : " + _score, 10, 20, paint_score);
    }


    public float getX()
    {
        return _x;
    }

    public void setX(int x)
    {
        _x = x;
    }

    public float getY()
    {
        return _y;
    }

    public static void setY(int y)
    {
        _y += _speed;
    }

    public static int get_speed()
    {
        return _speed;
    }

    public static void set_speed(int _speed)
    {
        Frog._speed = _speed;
    }

    public int get_score()
    {
        return _score;
    }

    public void set_score(int _score)
    {
        this._score = _score;
    }

    public boolean get_isGameOver()
    {
        return _isGameOver;
    }

    public void set_isGameOver(boolean _isGameOver)
    {
        this._isGameOver = _isGameOver;
    }

    public String getStr_userName()
    {
        return str_userName;
    }

    public void setStr_userName(String str_userName)
    {
        this.str_userName = str_userName;
    }
}
