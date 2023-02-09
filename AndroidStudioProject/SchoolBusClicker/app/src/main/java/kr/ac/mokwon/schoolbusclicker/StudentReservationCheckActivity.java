package kr.ac.mokwon.schoolbusclicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.FileTime;
import java.sql.Array;
import java.util.ArrayList;

public class StudentReservationCheckActivity extends AppCompatActivity
{
    String START, END, BUS, TIME, DATE;
    int SEAT;
    RecyclerView recyclerView = null;
    AdapterRecyclerReservation adapter = null;
    ArrayList<itemReservationCheck> list;
//    ArrayList<StudentItemReservationList> items = new ArrayList<>();
//    StudentReservationAdapter adapter;

    public String userID, userPass, userName, userAge, userType;
    Button btn_OK, btn_delete;
//    TextView textView;

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
        setContentView(R.layout.activity_student_reservation_check);

        DefaultSetting();

        // 아이디 찾아주기

        btn_OK = findViewById(R.id.btn_OK);
//        textView = findViewById(R.id.test);

        recyclerView = findViewById(R.id.recycler);
        list = new ArrayList<>();

        adapter = new AdapterRecyclerReservation(list);
        recyclerView.setAdapter(adapter);

        // 리사이클러뷰의 레이아웃 매니저 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        clickLoad();
//        textView.setText(START);
        adapter.notifyDataSetChanged();

        // 확인 버튼 눌렀을 때
        btn_OK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    public void clickLoad()
    {
        //서버주소
        String serverUrl="https://as8794.cafe24.com/bus_clicker/loadDBJson.php";

        itemReservationCheck item = new itemReservationCheck();

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(StudentReservationCheckActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                //파라미터로 응답받은 결과 JsonArray를 분석
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String userID2 = jsonObject.getString("userID");
                        String userName = jsonObject.getString("userName");
                        String bus = jsonObject.getString("bus");
                        String date = jsonObject.getString("date");
                        int seat = Integer.parseInt(jsonObject.getString("seat"));
                        int id = Integer.parseInt(jsonObject.getString("id"));
                        String start = jsonObject.getString("start");
                        String end = jsonObject.getString("end");
                        String time = jsonObject.getString("time");

                        if(userID2.equals(userID))
                        {
                            list.add(0, new itemReservationCheck(start, end, bus, time, date, seat, id));
                            adapter.notifyItemInserted(0);
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(StudentReservationCheckActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
    }
}