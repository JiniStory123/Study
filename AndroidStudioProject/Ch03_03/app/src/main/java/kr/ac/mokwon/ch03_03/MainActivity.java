package kr.ac.mokwon.ch03_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText id;
    EditText password;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.edit1);
        password = findViewById(R.id.edit2);
        phoneNumber = findViewById(R.id.edit3);
    }
}