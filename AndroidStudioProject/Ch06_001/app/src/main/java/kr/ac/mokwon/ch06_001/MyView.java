package kr.ac.mokwon.ch06_001;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class MyView extends View
{
    public MyView(Context context)
    {
        super(context);
        setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint p = new Paint();
        p.setColor(Color.YELLOW);
        p.setStrokeWidth(10);
//        canvas.drawLine(10, 10, 200, 10, p);
//        canvas.drawRect(10, 20, 200, 150, p);
//        canvas.drawText("글씨!", 50, 200, p);
//        canvas.drawCircle(150, 300, 50, p);
        canvas.drawRoundRect(new RectF(10, 10, 200, 150), 10, 10, p); // 매개변수가 너무 많으니 따로 값 넣을게요
        canvas.drawOval(new RectF(10, 160, 100, 200), p);
        canvas.drawArc(new RectF(120, 160, 200, 200), 0, 300, true, p);
        float[] pts = {10, 210, 100, 240,
                        100, 240, 10, 260,
                        10, 260, 100, 280};
        canvas.drawLines(pts, p);
        super.onDraw(canvas);
    }
}
