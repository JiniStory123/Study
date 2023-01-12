package kr.ac.mokwon.ch12_05_database02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
    SQLiteDatabase db;
    EditText et_name, et_tel;
    TextView tv_result;
    Button bt_insert, bt_search, bt_allSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_tel = findViewById(R.id.et_phone);
        tv_result = findViewById(R.id.tv_text);
        bt_insert = findViewById(R.id.btn_add);
        bt_search = findViewById(R.id.btn_search);
        bt_allSearch = findViewById(R.id.btn_allSearch);

        DBHelper helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        bt_insert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = et_name.getText().toString();
                String tel = et_tel.getText().toString();

                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("tel", tel);

                db.insert("contacts", null, values);
            }
        });

        bt_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = et_name.getText().toString();
                String[] tableColumns = {"tel"};
                String whereClause = "name == ?";
                String[] whereAgr = {name};

                Cursor cursor = db.query("contacts", tableColumns, whereClause, whereAgr, null, null, null);

                if(cursor.moveToNext())
                {
                    et_tel.setText(cursor.getString(0));
                }
            }
        });

        bt_allSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cursor cursor = db.query("contacts", null, null, null, null, null, null);
                String str = "ID            Name            Tel\n";

                while(cursor.moveToNext())
                {
                    str += cursor.getString(0) + "              ";
                    str += cursor.getString(1) + "              ";
                    str += cursor.getString(2) + "\n";
                }
                tv_result.setText(str);
            }
        });
    }
}