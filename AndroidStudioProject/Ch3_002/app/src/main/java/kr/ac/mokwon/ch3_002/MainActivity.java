package kr.ac.mokwon.ch3_002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edit;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn01);
        edit = findViewById(R.id.edit01);
        text = findViewById(R.id.txt01);

        btn.setOnClickListener(v->{
            text.setText(edit.getText());
        });
    }
}