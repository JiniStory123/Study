package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BusMainActivityOld extends AppCompatActivity
{
    String userID, userPass, userName, userAge, userType;

    TextView tv_test;

    TextView test;


    //제어변수들
    String busNumber;
    String busStop;
    String date;
    int check_start;
    int check_end;

    boolean isThreadOn;

//    FragmentCallThread _thread;

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusStop() {
        return busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCheck_start() {
        return check_start;
    }

    public void setCheck_start(int check_start) {
        this.check_start = check_start;
    }

    public int getCheck_end() {
        return check_end;
    }

    public void setCheck_end(int check_end) {
        this.check_end = check_end;
    }

    public String getDate2()
    {
        return tv_test.getText().toString();
    }

    protected void DefaultSetting()
    {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPass = intent.getStringExtra("userPass");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");
        userType = intent.getStringExtra("userType");
        busNumber = "D1";
        busStop = "산내소방서";
    }

    public void FragmentRecall()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.bus_list, new BusMainBusListFragment());
        ft.replace(R.id.bus_student_list, new BusReservationListFragment());
        ft.replace(R.id.bus_stop_list, new BusStopListFragment());
        ft.replace(R.id.bus_student_sum, new BusReservationCountFragment());
        ft.commitAllowingStateLoss();
    }

    public void UpdateVail()
    {

    }

//    class FragmentCallThread extends Thread
//    {
//        @Override
//        public void run()
//        {
//            while(isThreadOn)
//            {
//                try
//                {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e)
//                {
//
//                }
//                FragmentRecall();
//                Log.v("Thread", "ReCall");
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_main_old);
        tv_test = findViewById(R.id.test_id_id);


        DefaultSetting();

        // 날짜 받아오기
        long now_time = System.currentTimeMillis();
        Date mDate = new Date(now_time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        date = simpleDateFormat.format(mDate);
        tv_test.setText("현재 일자 : "+date);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.bus_list, new BusMainBusListFragment());
//        ft.replace(R.id.bus_student_list, new BusReservationListFragment());
        ft.replace(R.id.bus_stop_list, new BusStopListFragment());
        ft.replace(R.id.bus_student_sum, new BusReservationCountFragment());
        ft.commitAllowingStateLoss();

//        isThreadOn = true;
//        _thread = new FragmentCallThread();
//        _thread.start();
//
//
//        (new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                while (!Thread.interrupted())
//                    try
//                    {
//                        Thread.sleep(5000); //1초 간격으로 실행
//                        runOnUiThread(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                FragmentManager fm = getSupportFragmentManager();
//                                FragmentTransaction ft = fm.beginTransaction();
//                                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
//                                bundle.putString("busNumber",selectBusNumber);//번들에 넘길 값 저장
//                                BusReservationListFragment fragment2 = new BusReservationListFragment();//프래그먼트2 선언
//                                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
//                                ft.replace(R.id.bus_student_list, fragment2);
//                                overridePendingTransition(0, 0);
//                                ft.commit();
//                            }
//                        });
//                    }
//                    catch (InterruptedException e)
//                    {
//                        // error
//                    }
//            }
//        })).start();
    }
}

