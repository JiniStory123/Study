package kr.ac.mokwon.final_test;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MyThread extends Thread
{
    Combat _combat;
    Bullet _bullet;
    Enemy _enemy[];
    SurfaceHolder _holder;
    boolean _isRun;

    public MyThread(SurfaceHolder holder, Context context)
    {
        _holder = holder;
    }

    public void setCombat(Combat combat)
    {
        _combat = combat;
    }

    public void setBullet(Bullet bullet)
    {
        _bullet = bullet;
    }

    public void setEnemy(Enemy[] enemy)
    {
        _enemy = enemy;
    }

    public void setRun(boolean b)
    {
        _isRun = b;
    }

    @Override
    public void run()
    {
        while(_isRun)
        {
            Canvas c = null;
            try
            {
                c = _holder.lockCanvas(null);
                c.drawColor(0x88ffffff);
                synchronized (_holder)
                {
                    _combat.Paint(c);
                    for(int i=0; i<_enemy.length; i++)
                    {
                            _enemy[i].Paint(c);
                            if(_enemy[i]._isGameOver == true)
                            {
                                _combat._isGameOver = true;
                                for(int j=0; j<_enemy.length; j++)
                                    _enemy[j]._isGameOver = true;
                            }
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