package kr.ac.mokwon.ch05_004_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    TimePicker timer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.time);
        textView = findViewById(R.id.txt);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timer.getHour();
                int min = timer.getMinute();
                SetTime(hour, min);
            }
        });
    }

    void SetTime(int hour, int min)
    {
        String str = "오전";
        if(hour == 0)
        {
            str = "오전";
            hour = 12;
        } else if(hour == 12)
            str = "오후";
        else if(hour > 12)
        {
            str = "오후";
            hour -= 12;
        }

        textView.setText(str+ " " +hour+ ":" +min);
    }
}