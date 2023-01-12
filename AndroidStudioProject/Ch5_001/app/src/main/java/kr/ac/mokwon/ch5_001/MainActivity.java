package kr.ac.mokwon.ch5_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 클릭 리스너
        ClickListener listener = new ClickListener();
        CheckBox box1 = findViewById(R.id.check01);
        CheckBox box2 = findViewById(R.id.check02);
        box1.setOnClickListener(listener);
        box2.setOnClickListener(listener);
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox)view).isChecked();
            switch(view.getId()) {
                case R.id.check01:
                    Toast.makeText(getApplicationContext(), ((CheckBox)view).getText().toString()
                                    + " : " + checked, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.check02:
                    Toast.makeText(getApplicationContext(), ((CheckBox)view).getText().toString()
                            + " : " + checked, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}