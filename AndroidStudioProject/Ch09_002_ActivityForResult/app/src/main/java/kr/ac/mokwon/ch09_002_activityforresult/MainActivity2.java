package kr.ac.mokwon.ch09_002_activityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txt = findViewById(R.id.txt01);
        editText = findViewById(R.id.edit01);
        Button btn = findViewById(R.id.btn01);
        Button btn2 = findViewById(R.id.btn02);
        Intent intent = getIntent();
        int n1 = intent.getIntExtra("n1", 0);
        String n2 = intent.getStringExtra("n2");
        txt.setText(n1+" x "+n2+" = ");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.putExtra("n3", Integer.parseInt(editText.getText().toString()));
                setResult(RESULT_OK, in);
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}