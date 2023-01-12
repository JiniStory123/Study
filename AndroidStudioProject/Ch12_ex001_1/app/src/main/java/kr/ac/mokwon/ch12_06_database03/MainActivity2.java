package kr.ac.mokwon.ch12_06_database03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity
{
    private boolean RESTART;

    DBHelper helper;
    SQLiteDatabase db;

    EditText et_name, et_tel;
    Button btn_save, btn_search, btn_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RESTART = false;

        et_name = findViewById(R.id.et_name);
        et_tel = findViewById(R.id.et_tel);
        btn_save = findViewById(R.id.btn_save);
        btn_search = findViewById(R.id.btn_search);
        btn_exit = findViewById(R.id.btn_exit);

        helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        btn_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = et_name.getText().toString();
                String tel = et_tel.getText().toString();
                db.execSQL("INSERT contacts VALUES (null, '" + name + "', '" + tel + "');");
                et_name.setText("");
                et_tel.setText("");
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(et_tel.equals(" "))
                {
                    return;
                }
                else
                {
                    String tel = et_tel.getText().toString();
                    Cursor cursor;
                    cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE tel = '"+tel+"';", null);
                    cursor.moveToNext();
                    String name = cursor.getString(0);
                    et_name.setText(name);
                }
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RESTART = true;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}