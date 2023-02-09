package kr.ac.mokwon.schoolbusclicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;

public class BusReservationListFragment extends Fragment
{
    BusMainActivityOld main;

    RecyclerView recyclerView = null;
    AdapterRecyclerReservationFromBus adapter = null;
    ArrayList<itemReservationCheckFromBus> list;

    ArrayList<itemReservationCheckFromBus> list2;

    Button btn_busNumber;

    private String busSelect;

    // 제어변수들
    String busNumber;
    String busStop;
    String DATE;
    boolean isThreadOn;

    FragmentCallThread _thread;

    public BusReservationListFragment()
    {
        super();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        main = (BusMainActivityOld) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        main = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        busSelect = "D1";
        busNumber = main.getBusNumber();
        busStop = main.getBusStop();
        DATE = main.getDate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_bus_reservation_list, container, false);

//        if (getArguments() != null)
//        {
//            busSelect = getArguments().getString("busNumber"); // 프래그먼트1에서 받아온 값 넣기
//        }
//
//        busSelect = main.getSelectBusNumber();

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        list2 = new ArrayList<>();

        adapter = new AdapterRecyclerReservationFromBus(list);
        recyclerView.setAdapter(adapter);
        // 리사이클러뷰의 레이아웃 매니저 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DataLode();

//        _thread = new FragmentCallThread();
//        isThreadOn = true;
//        _thread.start();
//
        adapter.notifyDataSetChanged();



        return view;
    }

    class FragmentCallThread extends Thread
    {
        @Override
        public void run()
        {
            String serverUrl="https://as8794.cafe24.com/bus_clicker/loadDBJson.php";
            itemReservationCheck item = new itemReservationCheck();
            JsonArrayRequest jsonArrayRequest;

            while(isThreadOn)
            {
                busNumber = main.getBusNumber();
                busStop = main.getBusStop();
                DATE = main.getDate();

                list = list2;

                jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
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
                                String start = jsonObject.getString("start");
                                String end = jsonObject.getString("end");
                                String time = jsonObject.getString("time");

                                if(bus.equals(busNumber) && (start.equals(busStop) || end.equals(busStop)) && date.equals(DATE))
                                {
                                    list.add(0, new itemReservationCheckFromBus(userName, start, end));
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
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });

                //실제 요청 작업을 수행해주는 요청큐 객체 생성
                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

                //요청큐에 요청 객체 생성
                requestQueue.add(jsonArrayRequest);

                try
                {
                    Thread.sleep(10);
                } catch (InterruptedException e)
                {

                }
                Log.v("Thread", "ReCall");
            }
        }
    }

    public void DataLode()
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
                        String start = jsonObject.getString("start");
                        String end = jsonObject.getString("end");
                        String time = jsonObject.getString("time");

                        if(bus.equals(busNumber) && (start.equals(busStop) || end.equals(busStop)) && date.equals(DATE))
                        {
                            list.add(0, new itemReservationCheckFromBus(userName, start, end));
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
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
    }

    public void setSelectBus(String busSelect)
    {
        this.busSelect = busSelect;
    }

    @Override
    public void onStop()
    {
        super.onStop();
//        if(_thread != null)
//        {
//            isThreadOn = false;
//            while(true)
//            {
//                try
//                {
//                    Log.i("Thread", "join");
//                    _thread.join();
//                } catch (InterruptedException e) {}
//            }
//        }
        isThreadOn = false;
    }
}