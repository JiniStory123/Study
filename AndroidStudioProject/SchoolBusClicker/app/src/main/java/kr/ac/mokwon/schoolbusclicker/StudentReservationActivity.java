package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StudentReservationActivity extends AppCompatActivity
{
    CalendarView calendarView;
    String userID, userPass, userName, userAge, userType;
    EditText et_reservationBusNumber, et_reservationDate, et_reservationStart, et_reservationEnd, et_reservationTime;
    Button btn_reservationBusNumber,btn_reservationDate, btn_reservationStart, btn_reservationEnd, btn_reservationTime, btn_OK, btn_cancel;
    TextView tv_title;

    // 폼에서 선택된 버스 번호를 확인하는 변수들
    boolean checkSelectBus = false;
    String selectBus = "";
    String selectBusTime = "";

    DatePickerDialog datePickerDialog;

    protected void DefaultSetting()
    {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPass = intent.getStringExtra("userPass");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");
        userType = intent.getStringExtra("userType");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reservation);

        DefaultSetting();

        // 아이디 찾아주기
        tv_title = findViewById(R.id.title);
        et_reservationBusNumber = findViewById(R.id.reservationBusNumber);
        et_reservationDate = findViewById(R.id.reservationDate);
        et_reservationStart = findViewById(R.id.reservationStart);
        et_reservationEnd = findViewById(R.id.reservationEnd);
//        et_reservationTime = findViewById(R.id.reservationTime);
        btn_reservationBusNumber = findViewById(R.id.reservationBusNumberBtn);
        btn_reservationDate = findViewById(R.id.reservationDateBtn);
        btn_reservationStart = findViewById(R.id.reservationStartBtn);
        btn_reservationEnd = findViewById(R.id.reservationEndBtn);
        btn_OK = findViewById(R.id.OKBtn);
        btn_cancel = findViewById(R.id.CancelBtn);
//        btn_reservationTime = findViewById(R.id.reservationTimeBtn);


        // 버스 선택 버튼을 클릭
        btn_reservationBusNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                getMenuInflater().inflate(R.menu.popup_select_bus, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId())
                        {
                            case R.id.action_D1:
                                et_reservationBusNumber.setText("등교 버스 1호차");
                                et_reservationEnd.setText("목원대학교");
                                et_reservationStart.setText("");
                                checkSelectBus = true;
                                selectBus = "D1";
                                break;
                            case R.id.action_D2:
                                et_reservationBusNumber.setText("등교 버스 2호차");
                                et_reservationEnd.setText("목원대학교");
                                et_reservationStart.setText("");
                                checkSelectBus = true;
                                selectBus = "D2";
                                break;
                            case R.id.action_D3:
                                et_reservationBusNumber.setText("등교 버스 3호차");
                                et_reservationEnd.setText("목원대학교");
                                et_reservationStart.setText("");
                                checkSelectBus = true;
                                selectBus = "D3";
                                break;
                            case R.id.action_H1:
                                et_reservationBusNumber.setText("하교 버스 1호차");
                                et_reservationStart.setText("목원대학교");
                                et_reservationEnd.setText("");
                                checkSelectBus = true;
                                selectBus = "H1";
                                break;
                            case R.id.action_H2:
                                et_reservationBusNumber.setText("하교 버스 2호차");
                                et_reservationStart.setText("목원대학교");
                                et_reservationEnd.setText("");
                                checkSelectBus = true;
                                selectBus = "H2";
                                break;
                            case R.id.action_H3:
                                et_reservationBusNumber.setText("하교 버스 3호차");
                                et_reservationStart.setText("목원대학교");
                                et_reservationEnd.setText("");
                                selectBus = "H3";
                                checkSelectBus = true;
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        // 탑승 일자 버튼을 클릭
        btn_reservationDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar calender = Calendar.getInstance();
                Calendar week = Calendar.getInstance();
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.KOREA);
                dayFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                int pYear = calender.get(Calendar.YEAR);
                int pMonth = calender.get(Calendar.MONTH);
                int nMonth = pMonth + 1;
                int pDay = calender.get(Calendar.DAY_OF_WEEK);

//                maxDate.set(pDay, nMonth, pDay);

                datePickerDialog = new DatePickerDialog(StudentReservationActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day)
                    {
                        week.set(year, month, day);
                        String weekDate = dayFormat.format(week.getTime());

                        if(weekDate.equals("토요일") || weekDate.equals("일요일"))
                        {
                            Toast.makeText(getApplicationContext(),"주말을 선택할 수 없습니다.",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        month = month + 1;

                        String date;
                        if(day < 10)
                        {
                            date = year + "/" + month + "/0" + day;
                        }
                        else
                        {
                            date = year + "/" + month + "/" + day;
                        }

                        et_reservationDate.setText(date);
                    }
                }, pYear, pMonth, pDay);
                datePickerDialog.getDatePicker().setMinDate(calender.getTimeInMillis());
//                datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        // 승차 지점 버튼을 클릭
        btn_reservationStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                if(!checkSelectBus)
                {
                    Toast.makeText(getApplicationContext(),"예약할 버스를 선택해야 합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    switch (selectBus)
                    {
                        case "H1" :
                        case "H2" :
                        case "H3" :
                            Toast.makeText(getApplicationContext(),"하교 버스는 승차 지점을 변경할 수 없습니다.",Toast.LENGTH_SHORT).show();
                            break;
                        case "D1" :
                            getMenuInflater().inflate(R.menu.popup_bus_d1, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationStart.setText("산내소방서");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationStart.setText("은어송초등학교");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationStart.setText("효동현대아파트");
                                            break;
                                        case R.id.action_S4:
                                            et_reservationStart.setText("대전역");
                                            break;
                                        case R.id.action_S5:
                                            et_reservationStart.setText("교보생명");
                                            break;
                                        case R.id.action_S6:
                                            et_reservationStart.setText("오류동");
                                            break;
                                        case R.id.action_S7:
                                            et_reservationStart.setText("유천동");
                                            break;
                                        case R.id.action_S8:
                                            et_reservationStart.setText("버드내아파트");
                                            break;
                                        case R.id.action_S9:
                                            et_reservationStart.setText("도마동");
                                            break;
                                        case R.id.action_S10:
                                            et_reservationStart.setText("정림삼거리");
                                            break;
                                        case R.id.action_S11:
                                            et_reservationStart.setText("가수원네거리");
                                            break;
                                        case R.id.action_S12:
                                            et_reservationStart.setText("수목토아파트");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 D1
                        case "D2" :
                            getMenuInflater().inflate(R.menu.popup_bus_d2, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationStart.setText("법동우체국");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationStart.setText("동춘당");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationStart.setText("웰니스요양병원");
                                            break;
                                        case R.id.action_S4:
                                            et_reservationStart.setText("대전복합터미널");
                                            break;
                                        case R.id.action_S5:
                                            et_reservationStart.setText("성남사거리");
                                            break;
                                        case R.id.action_S6:
                                            et_reservationStart.setText("목동금호한사랑아파트");
                                            break;
                                        case R.id.action_S7:
                                            et_reservationStart.setText("용문동치안센터");
                                            break;
                                        case R.id.action_S8:
                                            et_reservationStart.setText("탄방동");
                                            break;
                                        case R.id.action_S9:
                                            et_reservationStart.setText("큰마을네거리");
                                            break;
                                        case R.id.action_S10:
                                            et_reservationStart.setText("갈마서부농협");
                                            break;
                                        case R.id.action_S11:
                                            et_reservationStart.setText("대전일보");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 D2
                        case "D3" :
                            getMenuInflater().inflate(R.menu.popup_bus_d3, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationStart.setText("신탄진역");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationStart.setText("신탄진톨게이트");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationStart.setText("대덕구보건소");
                                            break;
                                        case R.id.action_S4:
                                            et_reservationStart.setText("목상동파출소");
                                            break;
                                        case R.id.action_S5:
                                            et_reservationStart.setText("송강실내테니스장");
                                            break;
                                        case R.id.action_S6:
                                            et_reservationStart.setText("대덕테크노밸리아파트");
                                            break;
                                        case R.id.action_S7:
                                            et_reservationStart.setText("전민동");
                                            break;
                                        case R.id.action_S8:
                                            et_reservationStart.setText("한빛탑");
                                            break;
                                        case R.id.action_S9:
                                            et_reservationStart.setText("만년동");
                                            break;
                                        case R.id.action_S10:
                                            et_reservationStart.setText("유성온천역3번출구");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 D3
                    }
                }
            }
        });

        // 하차 지점 버튼을 클릭
        btn_reservationEnd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                if(!checkSelectBus)
                {
                    Toast.makeText(getApplicationContext(),"예약할 버스를 선택해야 합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    switch (selectBus)
                    {
                        case "D1" :
                        case "D2" :
                        case "D3" :
                            Toast.makeText(getApplicationContext(),"등교 버스는 하차 지점을 변경할 수 없습니다.",Toast.LENGTH_SHORT).show();
                            break;
                        case "H1" :
                            getMenuInflater().inflate(R.menu.popup_bus_h1, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationEnd.setText("가수원네거리");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationEnd.setText("정림동");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationEnd.setText("도마동");
                                            break;
                                        case R.id.action_S4:
                                            et_reservationEnd.setText("버드내아파트");
                                            break;
                                        case R.id.action_S5:
                                            et_reservationEnd.setText("유천동");
                                            break;
                                        case R.id.action_S6:
                                            et_reservationEnd.setText("서대전공원");
                                            break;
                                        case R.id.action_S7:
                                            et_reservationEnd.setText("중구청2번출구");
                                            break;
                                        case R.id.action_S8:
                                            et_reservationEnd.setText("대전역");
                                            break;
                                        case R.id.action_S9:
                                            et_reservationEnd.setText("효동현대아파트");
                                            break;
                                        case R.id.action_S10:
                                            et_reservationEnd.setText("은어송아파트");
                                            break;
                                        case R.id.action_S11:
                                            et_reservationEnd.setText("산내소방서");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 H1
                        case "H2" :
                            getMenuInflater().inflate(R.menu.popup_bus_h2, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationEnd.setText("유성온천역2번출구");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationEnd.setText("월평삼거리");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationEnd.setText("대전일보");
                                            break;
                                        case R.id.action_S4:
                                            et_reservationEnd.setText("갈마네거리");
                                            break;
                                        case R.id.action_S5:
                                            et_reservationEnd.setText("큰마을네거리");
                                            break;
                                        case R.id.action_S6:
                                            et_reservationEnd.setText("롯데백화점");
                                            break;
                                        case R.id.action_S7:
                                            et_reservationEnd.setText("용문동");
                                            break;
                                        case R.id.action_S8:
                                            et_reservationEnd.setText("목동더샵리슈빌아파트");
                                            break;
                                        case R.id.action_S9:
                                            et_reservationEnd.setText("성남사거리");
                                            break;
                                        case R.id.action_S10:
                                            et_reservationEnd.setText("대전복합터미널");
                                            break;
                                        case R.id.action_S11:
                                            et_reservationEnd.setText("동춘당");
                                            break;
                                        case R.id.action_S12:
                                            et_reservationEnd.setText("법동우체국");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 H2
                        case "H3" :
                            getMenuInflater().inflate(R.menu.popup_bus_h3, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationEnd.setText("유성온천역7번출구");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationEnd.setText("충남대학교");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationEnd.setText("궁동로데오거리");
                                            break;
                                        case R.id.action_S4:
                                            et_reservationEnd.setText("유성구청");
                                            break;
                                        case R.id.action_S5:
                                            et_reservationEnd.setText("월평동");
                                            break;
                                        case R.id.action_S6:
                                            et_reservationEnd.setText("대덕초등학교도룡분교장");
                                            break;
                                        case R.id.action_S7:
                                            et_reservationEnd.setText("전민동");
                                            break;
                                        case R.id.action_S8:
                                            et_reservationEnd.setText("대덕테크노밸리아파트");
                                            break;
                                        case R.id.action_S9:
                                            et_reservationEnd.setText("송강실내테니스장");
                                            break;
                                        case R.id.action_S10:
                                            et_reservationEnd.setText("목상동행정복지센터");
                                            break;
                                        case R.id.action_S11:
                                            et_reservationEnd.setText("신탄진역");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 H3
                    }
                }
            }
        });

        // 예약 버튼을 클릭
        btn_OK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // 에디트박스에 있는 값을 전부 가져온다.
                String busNumber = et_reservationBusNumber.getText().toString();
                String date = et_reservationDate.getText().toString();
                int seat = 0;
                String start = et_reservationStart.getText().toString();
                String end = et_reservationEnd.getText().toString();
                String time = "0";

                // EditText에 값이 비어있지 않은지 확인한다
                if(busNumber.equals("") || date.equals("") || start.equals("") || end.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"선택하지 않은 항목이 있습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    time = SetBusTime();
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 예약에 성공한 경우
                                Toast.makeText(getApplicationContext(),"예약에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                finish();
                            } else { // 예약에 실패한 경우
                                Toast.makeText(getApplicationContext(),"예약에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                RequestReservation requestReservation = new RequestReservation(userID, userName, selectBus, date, seat, start, end, time, responseListener);
                RequestQueue queue = Volley.newRequestQueue(StudentReservationActivity.this);
                queue.add(requestReservation);

            }
        });


        // 취소 버튼을 클릭
        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    public String SetBusTime()
    {
        String busNumber = et_reservationBusNumber.getText().toString();
        String start = et_reservationStart.getText().toString();
        String time = "";

        if (selectBus.equals("H1") || selectBus.equals("H2") || selectBus.equals("H3"))
        {
            time = "18:00";
        }

        if (selectBus.equals("D1"))
        {
            switch (start)
            {
                case "산내소방서" :
                    time = "07:25";
                    break;
                case "은어송초등학교" :
                    time = "07:29";
                    break;
                case "효동현대아파트" :
                    time = "07:35";
                    break;
                case "대전역" :
                    time = "07:45";
                    break;
                case "교보생명" :
                    time = "07:48";
                    break;
                case "오류동" :
                    time = "07:51";
                    break;
                case "유천동" :
                    time = "07:53";
                    break;
                case "버드내아파트" :
                    time = "07:54";
                    break;
                case "도마동" :
                    time = "07:56";
                    break;
                case "정림삼거리" :
                    time = "07:58";
                    break;
                case "가수원네거리" :
                    time = "08:00";
                    break;
                case "수목토아파트" :
                    time = "08:05";
                    break;
            }
        }

        if(selectBus.equals("D2"))
        {
            switch (start)
            {
                case "법동우체국" :
                    time = "07:25";
                    break;
                case "동춘당" :
                    time = "07:29";
                    break;
                case "웰니스요양병원" :
                    time = "07:35";
                    break;
                case "대전복합터미널" :
                    time = "07:37";
                    break;
                case "성남사거리" :
                    time = "07:41";
                    break;
                case "목동금호한사랑아파트" :
                    time = "07:43";
                    break;
                case "용문동치안센터" :
                    time = "07:51";
                    break;
                case "탄방동" :
                    time = "07:53";
                    break;
                case "큰마을네거리" :
                    time = "07:57";
                    break;
                case "갈마서부농협" :
                    time = "08:01";
                    break;
                case "대전일보" :
                    time = "08:03";
                    break;
            }
        }

        if(selectBus.equals("D3"))
        {
            switch (start)
            {
                case "신탄진역" :
                    time = "07:25";
                    break;
                case "신탄진톨게이트" :
                    time = "07:28";
                    break;
                case "대덕구보건소" :
                    time = "07:30";
                    break;
                case "목상동파출소" :
                    time = "07:33";
                    break;
                case "송강실내테니스장" :
                    time = "07:36";
                    break;
                case "대덕테크노밸리아파트" :
                    time = "07:39";
                    break;
                case "전민동" :
                    time = "07:47";
                    break;
                case "한빛탑" :
                    time = "07:55";
                    break;
                case "만년동" :
                    time = "08:00";
                    break;
                case "유성온천역3번출구" :
                    time = "08:010";
                    break;
            }
        }

        return time;
    }
}