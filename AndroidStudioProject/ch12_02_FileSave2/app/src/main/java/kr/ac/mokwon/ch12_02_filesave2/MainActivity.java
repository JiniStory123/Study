package kr.ac.mokwon.ch12_02_filesave2;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Output;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity
{
    static String FILE_NAME = "test.txt";
    EditText edit;
    Button readBtn, writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.edit);
        readBtn = findViewById(R.id.btn01);
        writeBtn = findViewById(R.id.btn02);

        // 외부 저장소의 상태를 String 타입으로 받아온다
        String state = Environment.getExternalStorageState();

        if(state.equals(Environment.MEDIA_MOUNTED))
        { // 외부와 접근이 가능한 경우
            readBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // 외부 저장소 경로 받아오기
                    File file = new File(getExternalFilesDir(null), FILE_NAME);
                    try
                    {
                        InputStream is = new FileInputStream(file);
                        byte[] buffer = new byte[is.available()];
                        is.read(buffer);
                        edit.setText(new String(buffer));
                        is.close();
                    } catch (IOException e)
                    {

                    }
                }
            });

            writeBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    File file = new File(getExternalFilesDir(null), FILE_NAME);
                    try
                    {
                        OutputStream os = new FileOutputStream(file);
                        os.write(edit.getText().toString().getBytes());
                        os.close();
                    } catch (IOException e) {

                    }
                }
            });
        }
    }
}