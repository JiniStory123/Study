package kr.ac.mokwon.ch12_06_database03;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(@Nullable Context context)
    {
        super(context, "mydatabase.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, tel TEXT);");

        for(int i=0; i<20; i++)
        {
            ContentValues values = new ContentValues();
            values.put("name", "kang"+i);
            values.put("tel", "1234"+i);

            db.insert("contacts", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS contacts;");
        onCreate(db);
    }
}
