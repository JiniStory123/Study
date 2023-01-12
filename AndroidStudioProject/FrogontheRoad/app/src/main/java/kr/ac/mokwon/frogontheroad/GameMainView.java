package kr.ac.mokwon.frogontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class GameMainView extends AppCompatActivity
{
    // 서비스뷰
    GameSurfaceView gameView;

    // 시스템 변수
    private String str_playerName; // 플레이어 이름
    boolean _isGameOver; // 게임오버 판단
    int _score; // 받아온 점수

    // 데이터베이스
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_view);

        // 타이틀 화면에서 플레이어 이름 받아오기
        Intent intent = getIntent();
        str_playerName = intent.getStringExtra("str_playerName");

        // 데이터베이스 선언
        helper = new DBHelper(this);
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e)
        {
            db = helper.getReadableDatabase();
        }

        // 랭킹 스코어 초기화용
//        db.execSQL("DELETE FROM contacts");

        // 게임 화면 엑티비티에 서피스뷰 그리기
        gameView = findViewById(R.id.SurfaceView);

        // 이스터에그용
        gameView.setUserName(str_playerName);

        gameView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                // 화면이 터치될 때마다 서피스뷰에 있는 메소드 실행
                _isGameOver = gameView.get_isGameOver();
                _score = gameView.get_score();

                int curX = (int)event.getX();  //눌린 곳의 X좌표
                int curY = (int)event.getY();  //눌린 곳의 Y좌표

                // 게임 플레이 중일때
                if(_isGameOver == false)
                {
                    switch (event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            // 화면 오측 하단에 그린 방향키
                            if(curX >= 200 && curX <= 250 && curY >= 428 && curY <= 478)
                            {
                                gameView.moveOn(1);
                            }
                            if(curX >= 260 && curX <= 310 && curY >= 428 && curY <= 478)
                            {
                                gameView.moveOn(-1);
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            gameView.moveOn(0);
                            break;
                    }
                }

                // 게임오버일때,
                if(_isGameOver == true)
                {
                    switch (event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            // 상단에 그린 랭킹 등록 버튼
                            if(curX >= 55 && curX <= 255 && curY >= 140 && curY <= 240)
                            {
                                gameView._thread.setRun(false); // 스레드를 멈춘다
                                _score = gameView.get_score(); // 점수를 동기화한다
                                // 쿼리문을 작성한다
                                db.execSQL("INSERT INTO contacts VALUES (null, '" + str_playerName + "', '" + _score + "');");
                                RankingActivity(); // 인텐트를 담은 메소드를 실행한다
                            }

                            // 하단에 그린 재시작 버튼
                            if(curX >= 55 && curX <= 255 && curY >= 260 && curY <= 360)
                            {
                                gameView.ReStart(); // 서피스뷰의 재시작 메소드를 실행한다
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            break;
                    }
                }
                return true;
            }
        });
    }

    // 랭킹 화면 이동 인텐트 메소드
    void RankingActivity()
    {
        Intent intent = new Intent(this, RankingActivity.class);
        intent.putExtra("str_playerName", str_playerName);
        intent.putExtra("_score", _score);
        startActivity(intent);
        finish();
    }

    // 키보드 테스트용
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
//        switch(keyCode)
//        {
//            case KeyEvent.KEYCODE_DPAD_UP:
//                gameView.moveOn(1);
//                break;
//            case KeyEvent.KEYCODE_DPAD_DOWN:
//                gameView.moveOn(-1);
//                break;
//            case KeyEvent.ACTION_UP:
//            case KeyEvent.KEYCODE_DPAD_CENTER:
//                gameView.moveOn(0);
//                break;
//        }
        return false;
    }
}