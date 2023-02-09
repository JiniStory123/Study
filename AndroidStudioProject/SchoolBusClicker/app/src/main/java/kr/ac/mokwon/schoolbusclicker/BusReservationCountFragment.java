package kr.ac.mokwon.schoolbusclicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import retrofit2.Call;
import retrofit2.Callback;

public class BusReservationCountFragment extends Fragment
{
    BusMainActivityOld main;

    TextView tv_start;
    TextView tv_end;

    // 제어변수들
    private String busNumber;
    private String busStop;
    private String DATE;
    boolean isThreadOn;

    // 항목 수
    int check_start;
    int check_end;
    int START = 0;
    int END = 0;

    // 스레드
    FragmentCallThread _thread;

    public BusReservationCountFragment()
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
        busNumber = main.getBusNumber();
        busStop = main.getBusStop();
        DATE = main.getDate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_bus_reservation_count_fragment, container, false);

        tv_start = view.findViewById(R.id.tv_start);
        tv_end = view.findViewById(R.id.tv_end);

        DataLode();

        _thread = new FragmentCallThread();
        isThreadOn = true;
        _thread.start();

        return view;
    }

    class FragmentCallThread extends Thread
    {
        String serverUrl="https://as8794.cafe24.com/bus_clicker/loadDBJson.php";
        itemReservationCheck item = new itemReservationCheck();
        JsonArrayRequest jsonArrayRequest;

        Call<Retrofit_Data_model_BusDB> call;

//        @Override
//        public void run()
//        {
//            while(isThreadOn)
//            {
//                busNumber = main.getBusNumber();
//                busStop = main.getBusStop();
//                DATE = main.getDate();
//                check_start = main.getCheck_start();
//                check_end = main.getCheck_end();
//                START = 0;
//                END = 0;
//
//                try
//                {
//                    call = Retrofit_LoadDBJson.getApiService().test_api_get("5");
//                    call.enqueue(new Callback<Retrofit_Data_model_BusDB>()
//                    {
//                        @Override
//                        public void onResponse(Call<Retrofit_Data_model_BusDB> call, retrofit2.Response<Retrofit_Data_model_BusDB> response)
//                        {
//                            Retrofit_Data_model_BusDB result = response.body();
//
//                            String bus = result.getBus();
//                            String start = result.getStart();
//                            String date = result.getDate();
//
//                            String str = bus + start + date;
//                            main.setTest(str);
//                            if(bus.equals(busNumber) && start.equals(busStop) && date.equals(DATE))
//                                {
//                                    START++;
//                                    if(check_start != START)
//                                        check_start = START;
//                                    main.setCheck_start(check_start);
//                                }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Retrofit_Data_model_BusDB> call, Throwable t)
//                        {
//
//                        }
//                    });
//                    Thread.sleep(100);
//                } catch (Exception e)
//                {
//
//                }
//                Log.v("Thread", "BusCount");
//            }
//        }

                @Override
        public void run()
        {
            while(isThreadOn)
            {
                busNumber = main.getBusNumber();
                busStop = main.getBusStop();
                DATE = main.getDate();
                check_start = main.getCheck_start();
                check_end = main.getCheck_end();
                START = 0;
                END = 0;

                jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                Toast.makeText(StudentReservationCheckActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        //파라미터로 응답받은 결과 JsonArray를 분석
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String userID = jsonObject.getString("userID");
                                String userName = jsonObject.getString("userName");
                                String bus = jsonObject.getString("bus");
                                String date = jsonObject.getString("date");
                                int seat = Integer.parseInt(jsonObject.getString("seat"));
                                String start = jsonObject.getString("start");
                                String end = jsonObject.getString("end");
                                String time = jsonObject.getString("time");

                                if(bus.equals(busNumber) && start.equals(busStop) && date.equals(DATE))
                                {
                                    START++;
                                    if(check_start != START)
                                        check_start = START;
                                    main.setCheck_start(check_start);
                                }

                                if(bus.equals(busNumber) && end.equals(busStop) && date.equals(DATE))
                                {
                                    END++;
                                    if(check_end != END)
                                        check_end = END;
                                    main.setCheck_end(check_end);
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

                main.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tv_start.setText(check_start+"");
                        tv_end.setText(check_end+"");
                    }
                });

                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {

                }
                Log.v("Thread", "BusCount");
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
                        String userID = jsonObject.getString("userID");
                        String userName = jsonObject.getString("userName");
                        String bus = jsonObject.getString("bus");
                        String date = jsonObject.getString("date");
                        int seat = Integer.parseInt(jsonObject.getString("seat"));
                        String start = jsonObject.getString("start");
                        String end = jsonObject.getString("end");
                        String time = jsonObject.getString("time");

                        if(bus.equals(busNumber) && start.equals(busStop) && date.equals(DATE))
                        {
                            START++;
//                            check_start = START;
                            main.setCheck_start(START);
//                            tv_start.setText(check_start+"");
                        }

                        if(bus.equals(busNumber) && end.equals(busStop) && date.equals(DATE))
                        {
                            END++;
//                            check_end = END;
                            main.setCheck_end(END);
//                            tv_end.setText(check_end+"");
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