package kr.ac.mokwon.ch05_002;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layoutId);
        RadioButton radioButton1 = findViewById(R.id.radio01);
        RadioButton radioButton2 = findViewById(R.id.radio02);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boolean checked = ((RadioButton)view).isChecked();
        switch(view.getId()) {
            case R.id.radio01:
                if(checked)
                    layout.setBackgroundColor(Color.RED);
                break;
            case R.id.radio02:
                if(checked)
                    layout.setBackgroundColor(Color.BLUE);
                break;
        }
    }
}