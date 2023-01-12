package kr.ac.mokwon.ch03_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //@Override
    //public void onClick(View view) {
    //    Toast.makeText(getApplicationContext(),"버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
    //}

    @Override // 안전장치래요...
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_1 = findViewById(R.id.btn01); // 띄어진 View에서 버튼 btn01을 찾아주세요...
        Button btn_2 = findViewById(R.id.btn02);
        Button btn_3 = findViewById(R.id.btn03);

        buttonListener bl = new buttonListener();

        btn_1.setOnClickListener(bl);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"김민석", Toast.LENGTH_SHORT).show();
            }
        });

        // 람다식, 자바 버전이 낮으면 못 하지롱...
        btn_3.setOnClickListener(view->{
            Toast.makeText(getApplicationContext(),"즐거운 술타임", Toast.LENGTH_SHORT).show();
        });
    }

    class buttonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"내부 클래스로 등록한 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}