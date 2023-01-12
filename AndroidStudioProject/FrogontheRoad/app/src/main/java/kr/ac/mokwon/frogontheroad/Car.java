package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Car extends View
{
    // 화면 크기
    static int WIDTH = 310;
    static int HEIGHT = 480;

    // 자동차의 위치
    private int _x, _y;

    // 자동차의 스피드
    private int _speed;

    // (자동차를 원으로 표현할 시) 자동차 반지름
    private int _diameter;

    // 자동차 이미지 불러오기
    Bitmap _carImg;

    // 개구리 위치
    private static int _frogX, _frogY;

    // 점수
    private static int _score;

    // 개구리 라이프
    private static int _life;

    // 게임오버인지
    private static boolean _isGameOver;

    // 페인트
    Paint p;
    Paint XY;

    // 자동차 x 좌표용 임시 변수
    int pastX;

    void DefaultSetting()
    {
        pastX = (int)((Math.random()*10000)%10);
        setCarLocation();
        setCarSpeedAboutTheScore();
        _score = 0;
        _life = 3;
        _isGameOver = false;

        _carImg = BitmapFactory.decodeResource(getResources(), R.drawable.car_left);

        p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(50);

        XY = new Paint();
        XY.setTextSize(20);
    }

    public Car(Context context)
    {
        super(context);
        DefaultSetting();
    }

    public Car(Context context, int _diameter)
    {
        super(context);
        this._diameter = _diameter;
        DefaultSetting();
    }

    public void setCarLocation()
    {
        if(pastX < 5)
        {
            _x = 0 - (int)(Math.random()*100 + 1);
        }
        else
        {
            _x = 310 + (int)(Math.random()*100 + 1);
        }
        _y = (int)(Math.random()*360 + 60);
    }

    public void setCarSpeedAboutTheScore()
    {
        if(_score >= 3000 && _score < 5000)
        {
            _speed = (int)(Math.random()*3 + 1);
        }
        else if(_score >= 5000 && _score < 6000)
        {
            _speed = (int)(Math.random()*4 + 1);
        }
        else if(_score >= 6000 && _score < 8000)
        {
            _speed = (int)(Math.random()*5 + 2);
        }
        else if(_score >= 8000)
        {
            _speed = (int)(Math.random()*10 + 2);
        }
        else
        {
            _speed = (int)(Math.random()*2 + 1);
        }
    }

    void Paint(Canvas c)
    {
        // 자동차의 방향 설정
        if(pastX < 5)
        {
            _carImg = BitmapFactory.decodeResource(getResources(), R.drawable.car_right);
            _x += _speed;
        }
        else
        {
            _x -= _speed;
        }

        // 자동차가 화면 밖으로 사라지면
        if(pastX < 5)
        {
            if(_x >= WIDTH)
            {
                pastX = (int)((Math.random()*10000)%10);
                setCarLocation();
                setCarSpeedAboutTheScore();
            }
        }
        else
        {
            if(_x < -1)
            {
                pastX = (int)((Math.random()*10000)%10);
                setCarLocation();
                setCarSpeedAboutTheScore();
            }
        }

        // 개구리와 자동차가 충돌하면
        if(_isGameOver == false)
        {
            if(_frogX - 10 < _x && _frogX + 10 > _x && _frogY - 10 < _y && _frogY + 10 > _y)
            {
                if(_life > 0)
                {
                    _life--;
                    pastX = (int)((Math.random()*10000)%10);
                    setCarLocation();
                    setCarSpeedAboutTheScore();
                    if(_life == 0)
                    {
                        _isGameOver = true;
                    }
                }
                else
                {
                    _isGameOver = true;
                }
            }
        }

//        c.drawCircle(_x, _y, _diameter, p);
        c.drawBitmap(_carImg, _x-10, _y-10, null);
//        c.drawText(_x +" "+_y, _x, _y, XY);
    }

    // getter, setter
    public int get_x()
    {
        return _x;
    }

    public void set_x(int _x)
    {
        this._x = _x;
    }

    public int get_y()
    {
        return _y;
    }

    public void set_y(int _y)
    {
        this._y = _y;
    }

    public int get_speed()
    {
        return _speed;
    }

    public void set_speed(int _speed)
    {
        this._speed = _speed;
    }

    public int get_diameter()
    {
        return _diameter;
    }

    public void set_diameter(int _diameter)
    {
        this._diameter = _diameter;
    }

    public int get_frogX()
    {
        return _frogX;
    }

    public void set_frogX(int _frogX)
    {
        this._frogX = _frogX;
    }

    public int get_frogY()
    {
        return _frogY;
    }

    public void set_frogY(int _frogY)
    {
        this._frogY = _frogY;
    }

    public static int get_score()
    {
        return _score;
    }

    public static void set_score(int _score)
    {
        Car._score = _score;
    }

    public static boolean get_isGameOver()
    {
        return _isGameOver;
    }

    public static void set_isGameOver(boolean _isGameOver)
    {
        Car._isGameOver = _isGameOver;
    }

    public static int get_life()
    {
        return _life;
    }

    public static void set_life(int _life)
    {
        Car._life = _life;
    }
}
