package kr.ac.mokwon.ex_06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ImgView extends View {
    public ImgView(Context context) {
        super(context);
    }

    public void Paint(Canvas c)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mo);
        c.drawBitmap(bitmap, 0, 0, null);
    }
}