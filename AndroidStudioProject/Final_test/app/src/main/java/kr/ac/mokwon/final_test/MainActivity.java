package kr.ac.mokwon.final_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    MySurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myView = findViewById(R.id.newView);
        Button bt1 = findViewById(R.id.btn01);
        bt1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        myView.MoveOn(-1);
                        myView.setCombatX();
                        break;
                    case MotionEvent.ACTION_UP:
                        myView.MoveOn(0);
                        myView.setCombatX();
                        break;
                }
                return false;
            }
        });

        Button bt2 = findViewById(R.id.btn02);
        bt2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        myView.MoveOn(1);
                        myView.setCombatX();
                        break;
                    case MotionEvent.ACTION_UP:
                        myView.MoveOn(0);
                        myView.setCombatX();
                        break;
                }
                return false;
            }
        });





    }

}