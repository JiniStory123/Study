package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class StudentMainActivity extends AppCompatActivity
{
    String userID, userPass, userName, userAge, userType;
    TextView tv_userName;
    Button btn_reservation, btn_cityReservation, btn_reservationCheck, btn_reservationIntercityCheck;

    DatePickerDialog datePickerDialog;

    protected void DefaultSetting()
    {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPass = intent.getStringExtra("userPass");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");
        userType = intent.getStringExtra("userType");
        tv_userName.setText(userName +"님 환영합니다");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        // 아이디 찾아주기
        tv_userName = findViewById(R.id.userName);
        btn_reservation = findViewById(R.id.BusReservationBtn);
        btn_cityReservation = findViewById(R.id.BusCityReservationBtn);
        btn_reservationCheck = findViewById(R.id.BusReservationCheckBtn);
        btn_reservationIntercityCheck = findViewById(R.id.BusCityReservationBtn);

        DefaultSetting();

        // 버스 예약 버튼 클릭
        btn_reservation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), StudentReservationActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userPass", userPass);
                intent.putExtra("userName", userName);
                intent.putExtra("userAge", userAge);
                intent.putExtra("userType", userType);
                startActivity(intent);
//                Response.Listener<String> responseListener = new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        try
//                        {
//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean success = jsonObject.getBoolean("success");
//                            if (success)
//                            {
//                                Intent intent = new Intent(getApplicationContext(), StudentReservationActivity.class);
//                                intent.putExtra("userID", userID);
//                                intent.putExtra("userPass", userPass);
//                                intent.putExtra("userName", userName);
//                                intent.putExtra("userAge", userAge);
//                                intent.putExtra("userType", userType);
//                                startActivity(intent);
//                            }
//                            else
//                            {
//                                Toast.makeText(getApplicationContext(),"이미 예약 정보가 존재합니다.",Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                RequestValidate validateRequest = new RequestValidate(userID, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(StudentMainActivity.this);
//                queue.add(validateRequest);
            }
        });

        // 시외버스 예약 버튼 클릭
        btn_cityReservation.setOnClickListener(new View.OnClickListener()
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
                startActivity(intent);
//                Response.Listener<String> responseListener = new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        try
//                        {
//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean success = jsonObject.getBoolean("success");
//                            if (success)
//                            {
//                                Intent intent = new Intent(getApplicationContext(), StudentReservationInterCityBusActivity.class);
//                                intent.putExtra("userID", userID);
//                                intent.putExtra("userPass", userPass);
//                                intent.putExtra("userName", userName);
//                                intent.putExtra("userAge", userAge);
//                                intent.putExtra("userType", userType);
//                                startActivity(intent);
//                            }
//                            else
//                            {
//                                Toast.makeText(getApplicationContext(),"이미 예약 정보가 존재합니다.",Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                RequestValidate validateRequest = new RequestValidate(userID, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(StudentMainActivity.this);
//                queue.add(validateRequest);
            }
        });

        // 예약 정보 확인 버튼 클릭
        btn_reservationCheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (!success)
                            {
                                Intent intent = new Intent(getApplicationContext(), StudentReservationCheckActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userAge", userAge);
                                intent.putExtra("userType", userType);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"예약정보가 없습니다.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };

                RequestValidate validateRequest = new RequestValidate(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(StudentMainActivity.this);
                queue.add(validateRequest);
            }
        });
    }
}