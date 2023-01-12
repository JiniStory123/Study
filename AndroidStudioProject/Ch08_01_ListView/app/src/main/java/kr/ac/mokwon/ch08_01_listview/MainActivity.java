package kr.ac.mokwon.ch08_01_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity
{
    String[] values = {"aaa", "bbb", "ccc",
                        "aaa", "bbb", "ccc",
                        "aaa", "bbb", "ccc",
                        "aaa", "bbb", "ccc"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        String item = (String)getListAdapter().getItem(position);
        Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
    }
}