package kr.ac.mokwon.frogontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RankingActivity extends AppCompatActivity
{
    // XML 리소스 받아오기
    Button btn_title;
    TextView tv_playerName;
    TextView tv_score;
    TextView tv_data;

    // 랭킹 시스템을 위한 에디트텍스트 XML 리소스 받아오기
    private int MAX = 10;
    EditText[] et_rank = new EditText[MAX];
    EditText[] et_name = new EditText[MAX];
    EditText[] et_score = new EditText[MAX];

    // 시스템 변수
    private String str_playerName;
    private int _score;

    // 데이터베이스
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        // XML 에디트텍스트 배열 변수 값 찾아주기
        for(int i=0; i<MAX; i++)
        {
            int int_rank = getResources().getIdentifier("et_rank_" + (i+1), "id", getApplicationContext().getPackageName());
            int int_name = getResources().getIdentifier("et_name_" + (i+1), "id", getApplicationContext().getPackageName());
            int int_score = getResources().getIdentifier("et_score_" + (i+1), "id", getApplicationContext().getPackageName());
            et_rank[i] = findViewById(int_rank);
            et_name[i] = findViewById(int_name);
            et_score[i] = findViewById(int_score);
        }

        // 인텐트로 값 받아오기
        Intent intent = getIntent();
        str_playerName = intent.getStringExtra("str_playerName");
        _score = intent.getIntExtra("_score", 0);

        // 데이터베이스 선언
        helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        // 랭킹 시스템 구현
        int RANK = 0;
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM contacts ORDER BY cast(score as integer) desc limit 10;", null);
        while (cursor.moveToNext())
        {
            ++RANK;
            et_rank[(RANK-1)].setText((RANK)+"");
            et_name[(RANK-1)].setText(cursor.getString(1)+"");
            et_score[(RANK-1)].setText(cursor.getString(2)+"");
        }

        // 타이틀 버튼 구현
        btn_title = findViewById(R.id.btn_title);
        btn_title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}