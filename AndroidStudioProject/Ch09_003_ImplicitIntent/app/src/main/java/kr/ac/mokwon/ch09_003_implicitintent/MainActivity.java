package kr.ac.mokwon.ch09_003_implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btn01;
    Button btn02;
    Button btn03;
    Button btn04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClickListener ls = new ClickListener();
        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);
        btn04 = findViewById(R.id.btn04);
        btn01.setOnClickListener(ls);
        btn02.setOnClickListener(ls);
        btn03.setOnClickListener(ls);
        btn04.setOnClickListener(ls);

    }

    public class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId())
            {
                case R.id.btn01 :
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01093989709"));
                    break;
                case R.id.btn02:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.30, 127.2?z=10"));
                    break;
                case R.id.btn03:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    break;
                case R.id.btn04:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                    break;
            }
            if(intent != null)
                startActivity(intent);
        }
    }
}