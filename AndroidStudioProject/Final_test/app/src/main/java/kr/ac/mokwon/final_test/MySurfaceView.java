package kr.ac.mokwon.final_test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    int _combatX, _combatY;
    MyThread _thread;
    Combat _combat;
    Bullet _bullet;
    Enemy[] _enemy;
    boolean _isDown;

    public MySurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        _combat = new Combat(10);
        _enemy = new Enemy[30];
        _combatX = _combat.getX();
        _combatY = _combat.getY();
        for(int i=0; i<_enemy.length; i++)
        {
            _enemy[i] = new Enemy(10);
            _enemy[i].setCombatX(_combat.getX());
            _enemy[i].setCombatY(_combat.getY());
        }

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        _thread = new MyThread(holder, context);
    }

//    public void setCombatX(int x)
//    {
//        _combatX = _combat.getX();
//
//        _combatX += x;
//        _combat.setX(_combatX);
//
//        _combatX = _combat.getX();
//        _combatY = _combat.getY();
//        for(int i=0; i<_enemy.length; i++)
//        {
//            _enemy[i].setCombatX(_combatX);
//            _enemy[i].setCombatY(_combatY);
//        }
//    }

    public void setCombatX()
    {
        _combatX = _combat.getX();
        for(int i=0; i<_enemy.length; i++)
        {
            _enemy[i].setCombatX(_combatX);
            _enemy[i].setCombatY(_combatY);
        }
    }

    public void MoveOn(int down)
    {
        _combat._speed = down;
    }

    public void MoveOff()
    {
        _isDown = false;
    }

    public int getCombatX()
    {
        return _combat.getX();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder)
    {
        _thread.setRun(true);
        _thread.setCombat(_combat);
        _thread.setEnemy(_enemy);
        _thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

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
}
