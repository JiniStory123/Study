package kr.ac.mokwon.ch07_002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txt);
        registerForContextMenu(textView); // 롱클릭

        /*
            컨텍스트 메뉴와 옵션 메뉴의 차이는
            옵션 메뉴 -> 엑티비티에서 호출 -> 한 개 밖에 만들 수 없다
            컨텍스트 메뉴 -> View마다 할당할 수 있다 -> 여러 View를 만들고 각각 할당할 수 있다
         */

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplicationContext(), "click : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    // 옵션 메뉴 구현을 위한 메뉴 바꿔치기(?) 함수
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true; // 그래야 우리가 만든 메뉴를 바꿔치기 할 수 있대요
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.blue :
                textView.setTextColor(Color.BLUE);
                break;
            case R.id.green :
                textView.setTextColor(Color.GREEN);
                break;
            case R.id.red :
                textView.setTextColor(Color.RED);
                break;
        }
        return true; // true로 둬야 버튼 클릭 했을 때 이벤트 가능하대
    }

    // 컨텍스트 메뉴 메뉴 바꿔치기 뭐시기 함수
    // item 뭐시기 필요없이 여기서도 다 넣을 수 있대
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("컨텍스트 메뉴");
        menu.add(0, 0, 0, "파랑"); // 그룹 아이디, 항목 아이디, 순서, 항목 이름
        menu.add(0, 1, 0, "초록"); // 순서 번호가 같으면 FIFO
        menu.add(0, 2, 0, "빨강");
        // 컨텍스트 메뉴는 길게 눌러 호출하는 메뉴라 롱클릭 이벤트를 메인에 넣어줘야 한다.
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case 0 :
                textView.setTextColor(Color.BLUE);
                break;
            case 1 :
                textView.setTextColor(Color.GREEN);
                break;
            case 2 :
                textView.setTextColor(Color.RED);
                break;
        }
        return true;
    }
}