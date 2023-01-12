package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
    // 이미지 및 오디오
    BackgroundImg _backgroundImg;
    DrawButton _softKey;
    DrawGameOverButton _gameOverButton;
    private static MediaPlayer mp;

    // 게임 객체
    Frog _frog;
    Car[] _car;
    DrawScore _drawScore;
    DrawLife _drawLife;

    // 게임 시스템 관련
    private int _frogX, _frogY; // 개구리 위치
    private boolean _isGameOver;
    private int _score;
    private int _life;

    // 서피스뷰 및 스레드 관련
    SurfaceHolder _holder;
    boolean _isRun;

    public GameThread(SurfaceHolder holder, Context context)
    {
        _holder = holder;
        _backgroundImg = new BackgroundImg(context);
        _gameOverButton = new DrawGameOverButton(context);
        _softKey = new DrawButton(context);
        mp = MediaPlayer.create(context, R.raw.animal);
        mp.setLooping(true);
        mp.start();
    }

    public void setFrog(Frog _frog)
    {
        this._frog = _frog;
    }

    public void setCar(Car[] _car)
    {
        this._car = _car;
    }

    public void setDrawScore(DrawScore _drawScore)
    {
        this._drawScore = _drawScore;
    }

    public void setRun(boolean b)
    {
        _isRun = b;
    }

    public void setDrawLife(DrawLife _drawLife)
    {
        this._drawLife = _drawLife;
    }

    public void startBgm()
    {
        mp.seekTo(0);
        mp.start();
    }

    @Override
    public void run()
    {
        while(_isRun)
        {
            // 변수 동기화
            _frogX = (int) _frog.getX();
            _frogY = (int) _frog.getY();
            _car[0].set_frogX(_frogX);
            _car[0].set_frogY(_frogY);
            _isGameOver = _car[0].get_isGameOver();
            _frog.set_isGameOver(_isGameOver);
            _score = _frog.get_score();
            _car[0].set_score(_score);
            _drawScore.set_score(_score);
            _life = _car[0].get_life();
            _drawLife.set_life(_life);

            Canvas c = null;
            try
            {
                c = _holder.lockCanvas(null);
                c.drawColor(0x88ffffff);
                _backgroundImg.Paint(c);
                synchronized (_holder)
                {
                    if(_isGameOver == false)
                    {
                        _softKey.Paint(c);
                        _frog.Paint(c);
                        _drawLife.Paint(c);
                        for(int i=0; i<_car.length; i++)
                        {
                            _car[i].Paint(c);
                        }
                    }
                    else
                    {
                        mp.pause();
                        _drawScore.Paint(c);
                        _gameOverButton.Paint(c);
                    }
                }

            } finally
            {
                if(c != null)
                    _holder.unlockCanvasAndPost(c);
            }
        }
    }


}