package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    // 화면 크기
    static int WIDTH = 310;
    static int HEIGHT = 340;

    // 객체들
    GameThread _thread; // 스레드
    Car[] _car; // 자동차
    Frog _frog; // 개구리
    DrawScore _drawScore; // 점수 그리기
    DrawLife _drawLife; // 개구리 라이프

    // 시스템 변수
    int _score; // 점수
    boolean _isGameOver; // 게임오버 판단

    // 테스트용 변수들
    int _touchX, _touchY; // 테스트용 터치한 좌표

    public GameSurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        _frog = new Frog(context, 10);
        _car = new Car[25];
        for(int i=0; i<_car.length; i++)
        {
            _car[i] = new Car(context, 10);
        }
        _drawScore = new DrawScore();
        _score = 0;
        _drawLife = new DrawLife(context);
        _isGameOver = false;

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        _thread = new GameThread(holder, context);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder)
    {
        _thread.setRun(true);
        _thread.setFrog(_frog);
        _thread.setCar(_car);
        _thread.setDrawScore(_drawScore);
        _thread.setDrawLife(_drawLife);
        _thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder)
    {
        _thread.setRun(false);
        boolean retry = true;
        while(retry)
        {
            try
            {
                _thread.join();
                retry = false;
            } catch(InterruptedException e)
            {

            }
        }
    }

    // 개구리 이동 메소드
    public void moveOn(int down)
    {
        _frog.set_speed(down);
    }

    // 점수를 얻어오는 메소드
    public int get_score()
    {
        _score = _frog.get_score(); // 개구리 객체에게서 점수 얻어오기
        return _score;
    }

    // 점수 셋팅
    public void set_score(int _score)
    {
        this._score = _score;
    }

    // 게임오버 판단 메소드
    public boolean get_isGameOver()
    {
        _isGameOver = _frog.get_isGameOver(); // 개구리 객체에게서 게임오버 판단 얻어오기
        return _isGameOver;
    }

    public void set_isGameOver(boolean _isGameOver)
    {
        this._isGameOver = _isGameOver;
    }

    // 게임 리셋 메소드
    public void ReStart()
    {
        _frog.DefaultSetting();
        for(int i=0; i<_car.length; i++)
        {
            _car[i].DefaultSetting();
        }
        _thread.startBgm();
    }

    // 이스터에그용
    public void setUserName(String strUserName)
    {
        _frog.setStr_userName(strUserName);
    }
}