package kr.ac.mokwon.ch08_02_customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    String[] titles = {"민석1", "민석2", "민석3", "민석이의 두근두근 School Life"}; // 4개
    Integer[] images = {R.drawable.min1, R.drawable.min2, R.drawable.min3, R.drawable.min4}; // 4개
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomList adapter = new CustomList(MainActivity.this, images, titles);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(getApplicationContext(), titles[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}