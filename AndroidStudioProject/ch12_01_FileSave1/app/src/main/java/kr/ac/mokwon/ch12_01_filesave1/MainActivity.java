package kr.ac.mokwon.ch12_01_filesave1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    static String FILE_NAME = "text.txt";
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.edit);
        Button readBtn = findViewById(R.id.btn01);
        Button writeBtn = findViewById(R.id.btn02);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream file = openFileInput(FILE_NAME);
                    byte[] buffer = new byte[file.available()];
                    file.read(buffer);
                    edit.setText(new String(buffer));
                    file.close();
                } catch (IOException e) {

                }
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream file = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    file.write(edit.getText().toString().getBytes());
                    file.close();
                } catch (IOException e) {

                }
            }
        });
    }
}