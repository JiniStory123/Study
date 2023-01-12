package kr.ac.mokwon.ch12_06_database03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private boolean RESTART;

    SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    ListView listView;
    Button btn_add, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RESTART = false;
        listView = findViewById(R.id.list);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);

        DBHelper helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        Cursor cursor = db.rawQuery("SELECT * FROM contacts", null);

        String[] from = {"name", "tel"};

        int[] to = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to,
                                                                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);


        btn_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db.execSQL("DELETE FROM contacts");
            }
        });
    }
}