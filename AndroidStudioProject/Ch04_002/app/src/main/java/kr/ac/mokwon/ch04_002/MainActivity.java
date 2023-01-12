package kr.ac.mokwon.ch04_002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        LinearLayout lLayout = new LinearLayout(this);
        lLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);

        Button btn = new Button(this);
        Button btn2 = new Button(this);
        btn.setText("button1");
        btn2.setText("button2");
        btn.setLayoutParams(params);
        btn2.setLayoutParams(params);
        lLayout.addView(btn);
        lLayout.addView(btn2);

        setContentView(lLayout);
    }
}