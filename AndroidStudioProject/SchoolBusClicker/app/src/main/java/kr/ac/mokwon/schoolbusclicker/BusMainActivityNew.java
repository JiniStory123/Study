package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class BusMainActivityNew extends AppCompatActivity
{
    
    // 이거 안 씀



    String userID, userPass, userName, userAge, userType;

    // 제어변수들
    String busNumber;
    String busStop;

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
        setContentView(R.layout.activity_bus_main);

//        DefaultSetting();

//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.bus_list, new BusMainBusListFragment());
//        ft.replace(R.id.bus_student_list, new BusReservationListFragment());
//        ft.replace(R.id.bus_stop_list, new BusStopListFragment());
    }
}