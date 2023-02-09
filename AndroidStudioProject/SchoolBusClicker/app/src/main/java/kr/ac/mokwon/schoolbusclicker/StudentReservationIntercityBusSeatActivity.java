package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentReservationIntercityBusSeatActivity extends AppCompatActivity
{
    String userID, userPass, userName, userAge, userType, date;
    Button btn_OK, btn_cancel;
    Button[] btn_seat = new Button[24];
    String[] seatData = new String[24];
    TextView test;

    int selectSeat;

    // 폼에서 선택된 버스 번호를 확인하는 변수들
    boolean checkSelectBus = false;
    String selectBus = "";
    String selectDate = "";
    String selectBusTime = "";

    int jini;

    protected void DefaultSetting()
    {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPass = intent.getStringExtra("userPass");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");
        userType = intent.getStringExtra("userType");
        date = intent.getStringExtra("date");

//        selectSeat = intent.getIntExtra("selectSeat", selectSeat);
//
//        checkSelectBus = intent.getBooleanExtra("checkSelectBus", checkSelectBus);
        selectBus = intent.getStringExtra("selectBus");
        selectDate = intent.getStringExtra("selectDate");

        jini = intent.getIntExtra("jini", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reservation_intercity_bus);
        selectSeat = -1;
        DefaultSetting();

        btn_OK = findViewById(R.id.btn_OK);
        btn_cancel = findViewById(R.id.btn_cancel);
        test = findViewById(R.id.seat);
        test.setText(selectSeat+"");

        for(int i=0; i<24; i++)
        {
            seatData[i] = "0";
        }

//        seatData[1] = "1";
//        seatData[15] = "1";

        for(int i=0; i<24; i++)
        {
            btn_seat[i] = findViewById(getResources().getIdentifier("btn_seat_" + (i+1), "id", "kr.ac.mokwon.schoolbusclicker"));
            if(seatData[i].equals("1"))
            {
                btn_seat[i].setBackgroundResource(R.drawable.bus_seat2);
            }
            final int INDEX = i;
            btn_seat[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(seatData[INDEX].equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),"이미 선택된 좌석입니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        if(selectSeat == INDEX)
                        {
                            btn_seat[INDEX].setBackgroundResource(R.drawable.bus_seat);
                            selectSeat = -1;
                        }
                        else
                        {
                            if(selectSeat == -1)
                            {
                                btn_seat[INDEX].setBackgroundResource(R.drawable.bus_seat3);
                                selectSeat = INDEX;
                            }
                            else
                            {
                                btn_seat[selectSeat].setBackgroundResource(R.drawable.bus_seat);
                                btn_seat[INDEX].setBackgroundResource(R.drawable.bus_seat3);
                                selectSeat = INDEX;
                            }
                        }
                    }
                    test.setText(selectSeat+"");
                }
            });
        }

        btn_OK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(selectSeat == -1)
                {
                    Toast.makeText(getApplicationContext(),"선택한 좌석이 없습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), StudentReservationInterCityBusActivity.class);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userPass", userPass);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userAge", userAge);
                    intent.putExtra("userType", userType);
                    intent.putExtra("selectSeat", selectSeat);
                    intent.putExtra("checkSelectBus", checkSelectBus);
                    intent.putExtra("selectBus", selectBus);
                    intent.putExtra("date", date);
                    intent.putExtra("selectDate", "2022/12/7");
                    intent.putExtra("jini", jini);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), StudentReservationInterCityBusActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userPass", userPass);
                intent.putExtra("userName", userName);
                intent.putExtra("userAge", userAge);
                intent.putExtra("userType", userType);
                intent.putExtra("selectSeat", selectSeat);
                intent.putExtra("checkSelectBus", checkSelectBus);
                intent.putExtra("selectBus", selectBus);
                startActivity(intent);
                finish();
            }
        });

    }

    void FindBtnNumber()
    {

    }
}