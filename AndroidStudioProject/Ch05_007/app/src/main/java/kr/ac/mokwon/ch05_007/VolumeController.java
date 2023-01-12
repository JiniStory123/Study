package kr.ac.mokwon.ch05_007;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class VolumeController extends AppCompatImageView {
    float _mX, _mY, _angle;
    double _dAngle;

    KnobListener listener;
    public interface KnobListener {
        public void OnChange(float angle);
    }
    public void setKnobChangeListener(KnobListener listener) {
        this.listener = listener;
    }

    public VolumeController(@NonNull Context context) {
        super(context);
        setImageResource(R.drawable.volumebtn);
    }

    public VolumeController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs); // xml에 넣고 싶다면 이 생성자가 꼭 필요하다. ID뭐 넣으려고.
        setImageResource(R.drawable.volumebtn);
    }

    // 각도를 계산해주는 매개변수
    float GetAngle(float x, float y) {
        _mX = x - (getWidth()/2.0f);
        _mY = (getHeight() / 2.0f) - y;
        _dAngle = Math.atan2(_mX, _mY) * 180 / Math.PI; // 라디안 값을 ~~도로 바꿈.
        return (float)_dAngle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        _angle = GetAngle(event.getX(), event.getY()); // 각도를 계산받고 그 각도만큼 이미지를 돌린다
        invalidate();
        listener.OnChange(_angle);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(_angle, getWidth()/2.0f, getHeight()/2.0f);
        super.onDraw(canvas);
    }
}
