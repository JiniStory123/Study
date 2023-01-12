package kr.ac.mokwon.ch12_ex001;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class MainActivity2 extends AppCompatActivity
{
    SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    EditText et_name, et_tel;
    Button btn_save, btn_search, btn_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_name = findViewById(R.id.et_name);
        et_tel = findViewById(R.id.et_tel);
        btn_save = findViewById(R.id.btn_save);
        btn_search = findViewById(R.id.btn_search);
        btn_exit = findViewById(R.id.btn_exit);


    }
}