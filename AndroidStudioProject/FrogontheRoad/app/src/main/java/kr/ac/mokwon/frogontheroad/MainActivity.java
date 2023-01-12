package kr.ac.mokwon.frogontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    // 시스템 변수
    Button btn_start; // 게임 시작 버튼
    Button btn_ranking; // 랭킹 페이지 이동 버튼
    Button btn_rankClear; // 랭킹 초기화 버튼
    EditText et_playerName; // 플레이어 이름 입력 에디트박스
    String str_playerName; // 플레이어 이름

    // 데이터베이스
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 아이디 찾아주기
        btn_start = findViewById(R.id.btn_start);
        btn_ranking = findViewById(R.id.btn_ranking);
        btn_rankClear = findViewById(R.id.btn_clear);
        et_playerName = findViewById(R.id.et_name);

        // 데이터베이스 선언
        helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        // 게임 시작 버튼이 눌리면
        btn_start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // 플레이어 이름 에디터박스에 있는 텍스트를 가져와 플레이어 이름 스트링 변수에 입력
                str_playerName = et_playerName.getText().toString();
                // 만약 플레이어 이름이 입력되지 않았으면
                if(str_playerName.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 게임 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), GameMainView.class);
                intent.putExtra("str_playerName", str_playerName);
                startActivity(intent);
                finish();
            }
        });

        // 랭킹 화면 이동 버튼이 눌렸다면
        btn_ranking.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 랭킹 초기화 버튼이 눌렸다면
        btn_rankClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db.execSQL("DELETE FROM contacts");
                Toast.makeText(getApplicationContext(), "랭킹이 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}