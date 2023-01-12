package kr.ac.mokwon.frogontheroad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class DrawGameOverButton extends View
{
    Bitmap btn_ranking;
    Bitmap btn_reStart;

    public DrawGameOverButton(Context context)
    {
        super(context);
    }

    public void Paint(Canvas c)
    {
        btn_ranking = BitmapFactory.decodeResource(getResources(), R.drawable.gameover_btn1);
        btn_reStart = BitmapFactory.decodeResource(getResources(), R.drawable.gameover_btn2);

        c.drawBitmap(btn_ranking, 55, 140, null);
        c.drawBitmap(btn_reStart, 55, 260, null);
    }
}
