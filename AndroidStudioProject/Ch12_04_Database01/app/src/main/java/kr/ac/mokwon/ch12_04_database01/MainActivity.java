package kr.ac.mokwon.ch12_04_database01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    DBHelper helper;
    SQLiteDatabase db;


    EditText et_name, et_phone;
    Button btn_add, btn_search, btn_allSearch;
    TextView tv_view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        btn_add = findViewById(R.id.btn_add);
        btn_search = findViewById(R.id.btn_search);
        btn_allSearch = findViewById(R.id.btn_allSearch);
        tv_view = findViewById(R.id.tv_text);

        helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        btn_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = et_name.getText().toString();
                String phone = et_phone.getText().toString();
                db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "', '" + phone + "');");
                et_name.setText("");
                et_phone.setText("");
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String tel = et_phone.getText().toString();
                Cursor cursor;
                cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE tel = '"+tel+"';", null);
                cursor.moveToNext();
                String name = cursor.getString(0);
                et_name.setText(name);
            }
        });

        btn_allSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cursor cursor;
                cursor = db.rawQuery("SELECT * FROM contacts", null);
                String msg = "ID        Name        TEL    \n";
                while (cursor.moveToNext())
                {
                    msg += cursor.getString(0) + "          ";
                    msg += cursor.getString(1) + "          ";
                    msg += cursor.getString(2) + "\n";
                }
                tv_view.setText(msg);
            }
        });
    }
}