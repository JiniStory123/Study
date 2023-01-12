package kr.ac.mokwon.ch12_06_database03;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        DBHelper helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }
        Cursor cursor = db.query("contacts", null, null, null, null, null, null);
        String[] from = {"name", "tel"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to,
                                                                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Cursor cursor1 = (Cursor) adapter.getItem(i);
                Toast.makeText(getApplicationContext(), cursor1.getString(1) + " " + cursor1.getString(2), Toast.LENGTH_SHORT).show();
            }
        });
    }
}