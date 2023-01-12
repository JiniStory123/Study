package kr.ac.mokwon.ch06_003;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MyView myView;
    float _angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyView myView = new MyView(this);
//        setContentView(myView);
        _angle = 0;
        myView = findViewById(R.id.img);
        Button btn = findViewById(R.id.btn01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _angle += 20;
                myView.SetAngle(_angle);
            }
        });
    }
}