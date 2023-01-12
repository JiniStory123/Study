package kr.ac.mokwon.ch12_ex001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    SimpleCursorAdapter adapter;
    ListView listView;
    Button btn_add;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_user);
        btn_add = findViewById(R.id.btn_add);

        DBHelper helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

//        Cursor cursor = db.query("contacts", null, null, null, null, null, null);
//        String[] from = {"name", "tel"};
//        int[] to = {android.R.id.text1, android.R.id.text2};
//        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to,
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM contacts", null);

        listView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }
}