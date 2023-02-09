package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class StudentReservationInterCityBusActivity extends AppCompatActivity
{
    String userID, userPass, userName, userAge, userType, date;
    EditText et_reservationBusNumber, et_reservationBusSeat, et_reservationStart, et_reservationEnd, et_reservationTime, et_reservationDate;
    Button btn_reservationBusNumber, btn_reservationBusSeat, btn_reservationStart, btn_reservationEnd, btn_reservationTime, btn_OK, btn_cancel, btn_reservationDate;
    TextView tv_title;
    int selectSeat;

    DatePickerDialog datePickerDialog;

    // 폼에서 선택된 버스 번호를 확인하는 변수들
    boolean checkSelectBus = false;
    String selectBus = "";
    String selectBusTime = "";
    String selectDate = "";

    int jini;

    protected void DefaultSetting()
    {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPass = intent.getStringExtra("userPass");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");
        userType = intent.getStringExtra("userType");
        selectSeat = intent.getIntExtra("selectSeat", selectSeat);
        date = intent.getStringExtra("date");
        jini = intent.getIntExtra("jini", 0);

        selectBus = intent.getStringExtra("selectBus");
        et_reservationBusNumber.setText(selectBus);

        selectDate = intent.getStringExtra("selectDate");
        et_reservationDate.setText(selectDate);

        if(jini == 1)
        {
            et_reservationBusNumber.setText("등교 시외버스 1호차");
            et_reservationEnd.setText("목원대학교");
            et_reservationStart.setText("");
            checkSelectBus = true;
            selectBus = "DI1";
            date = "2022/12/7";
            et_reservationDate.setText(date);
        }
    }

    protected void GetSeatInformation()
    {
        Intent intent = getIntent();
        selectSeat = intent.getIntExtra("selectSeat", -1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reservation_inter_city_bus);

        selectSeat = -1;

        // 아이디 찾아주기
        tv_title = findViewById(R.id.title);
        et_reservationBusNumber = findViewById(R.id.reservationBusNumber);
        et_reservationStart = findViewById(R.id.reservationStart);
        et_reservationEnd = findViewById(R.id.reservationEnd);
        et_reservationBusSeat = findViewById(R.id.reservationBusSeat);
        et_reservationDate = findViewById(R.id.reservationDate);
        btn_reservationBusNumber = findViewById(R.id.reservationBusNumberBtn);
        btn_reservationStart = findViewById(R.id.reservationStartBtn);
        btn_reservationEnd = findViewById(R.id.reservationEndBtn);
        btn_reservationBusSeat = findViewById(R.id.reservationBusSeatBtn);
        btn_OK = findViewById(R.id.OKBtn);
        btn_cancel = findViewById(R.id.CancelBtn);
        btn_reservationDate = findViewById(R.id.reservationDateBtn);

        DefaultSetting();

        et_reservationDate.setText(date);

        // intent 값이 있으면 셋팅
        if(selectSeat != -1)
            et_reservationBusSeat.setText((selectSeat+1) + "");

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

                datePickerDialog = new DatePickerDialog(StudentReservationInterCityBusActivity.this, new DatePickerDialog.OnDateSetListener()
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
                        String date = year + "/" + month + "/" + day;

                        et_reservationDate.setText(date);
                    }
                }, pYear, pMonth, pDay);
                datePickerDialog.getDatePicker().setMinDate(calender.getTimeInMillis());
//                datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
                datePickerDialog.show();
            }
        });


        // 버스 선택 버튼을 클릭
        btn_reservationBusNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                getMenuInflater().inflate(R.menu.popup_select_bus_intercity, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId())
                        {
                            case R.id.action_DI1:
                                et_reservationBusNumber.setText("등교 시외버스 1호차");
                                et_reservationEnd.setText("목원대학교");
                                et_reservationStart.setText("");
                                checkSelectBus = true;
                                selectBus = "DI1";
                                break;
                            case R.id.action_DI2:
                                et_reservationBusNumber.setText("등교 시외버스 2호차");
                                et_reservationEnd.setText("목원대학교");
                                et_reservationStart.setText("");
                                checkSelectBus = true;
                                selectBus = "DI2";
                                break;
                            case R.id.action_HI1:
                                et_reservationBusNumber.setText("하교 시외버스 1호차");
                                et_reservationStart.setText("목원대학교");
                                et_reservationEnd.setText("");
                                checkSelectBus = true;
                                selectBus = "HI1";
                                break;
                            case R.id.action_HI2:
                                et_reservationBusNumber.setText("하교 시외버스 2호차");
                                et_reservationStart.setText("목원대학교");
                                et_reservationEnd.setText("");
                                checkSelectBus = true;
                                selectBus = "HI2";
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        // 좌석 선택 버튼 클릭
        btn_reservationBusSeat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), StudentReservationIntercityBusSeatActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userPass", userPass);
                intent.putExtra("userName", userName);
                intent.putExtra("userAge", userAge);
                intent.putExtra("userType", userType);
                intent.putExtra("selectSeat", selectSeat);
                intent.putExtra("checkSelectBus", checkSelectBus);

                selectBus = et_reservationBusNumber.getText().toString();
                intent.putExtra("selectBus", selectBus);

                selectDate = et_reservationDate.getText().toString();
                intent.putExtra("selectDate", date);

                jini = 1;
                intent.putExtra("jini", jini);

                startActivity(intent);
                finish();
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
                        case "DI1" :
                            getMenuInflater().inflate(R.menu.popup_bus_di1, popupMenu.getMenu());
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem)
                                {
                                    switch (menuItem.getItemId())
                                    {
                                        case R.id.action_S1:
                                            et_reservationStart.setText("교대역9번출구");
                                            break;
                                        case R.id.action_S2:
                                            et_reservationStart.setText("죽전간이정류장");
                                            break;
                                        case R.id.action_S3:
                                            et_reservationStart.setText("신갈버스정류장");
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                            break;

                        // 여기까지 D1
                    }
                }
            }
        });

        // 예약 버튼 클릭
        btn_OK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(),"예약 되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // 취소 버튼 클릭
        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}