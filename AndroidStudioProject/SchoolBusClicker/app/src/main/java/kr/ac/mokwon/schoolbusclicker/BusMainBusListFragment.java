package kr.ac.mokwon.schoolbusclicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class BusMainBusListFragment extends Fragment
{
    private String[] busList;
    private static int selectList;
    BusMainActivityOld main;
    BusReservationListFragment fragment_bus_reservation_list;

    // 제어변수
    String busNumber;
    String busStop;
    String date;
    boolean isThreadOn;

    TextView tv_busNumber;
    Button btn_busNumber;

    // 스레드

    FragmentCallThread _thread;

    public BusMainBusListFragment()
    {
        super();
    }

    class FragmentCallThread extends Thread
    {
        @Override
        public void run()
        {
            while(isThreadOn)
            {
                busNumber = main.getBusNumber();

                main.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tv_busNumber.setText(busNumber);
                    }
                });

                try
                {
                    Thread.sleep(1);
                } catch (InterruptedException e)
                {

                }
                Log.v("Thread", "BusListFragment");
            }
        }
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
        _thread = new FragmentCallThread();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_bus_main_bus_list_fragment, container, false);
        tv_busNumber = view.findViewById(R.id.tv_bus_number);
        btn_busNumber = view.findViewById(R.id.btn_bus_number);

        tv_busNumber.setText(busNumber);

//        tv_busNumber.setText(busList[selectList]);

        isThreadOn = true;
        _thread.start();

        btn_busNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_bus_client_list, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        main.setCheck_start(0);
                        main.setCheck_end(0);
                        switch (menuItem.getItemId())
                        {

                            case R.id.action_D1:
                                main.setBusNumber("D1");
                                main.setBusStop("산내소방서");
//                                main.FragmentRecall();
                                break;
                            case R.id.action_D2:
                                main.setBusNumber("D2");
                                main.setBusStop("법동우체국");
//                                main.FragmentRecall();
                                break;
                            case R.id.action_D3:
                                main.setBusNumber("D3");
                                main.setBusStop("신탄진역");
//                                main.FragmentRecall();
                                break;
                            case R.id.action_H1:
                                main.setBusNumber("H1");
                                main.setBusStop("가수원네거리");
//                                main.FragmentRecall();
                                break;
                            case R.id.action_H2:
                                main.setBusNumber("H2");
                                main.setBusStop("유성온천역2번출구");
//                                main.FragmentRecall();
                                break;
                            case R.id.action_H3:
                                main.setBusNumber("H3");
                                main.setBusStop("유성온천역7번출구");
//                                main.FragmentRecall();
                                break;
                            case R.id.action_DI1:
                                break;
                            case R.id.action_DI2:
                                break;
                            case R.id.action_HI1:
                                break;
                            case R.id.action_HI2:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return view;
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



//                        tv_busNumber.setText(busList[INDEX]);
////                        Bundle bundle = new Bundle(); // 번들을 통해 값 전달
////                        bundle.putString("busNumber",tv_busNumber.getText().toString());//번들에 넘길 값 저장
////                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
////                        BusReservationListFragment fragment2 = new BusReservationListFragment();//프래그먼트2 선언
////                        BusStopListFragment busStopFragment = new BusStopListFragment();
//////                        String on = "true";
//////                        Bundle bundle1 = new Bundle();
//////                        bundle1.putString("isBtnOn", on);
////                        fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
////                        busStopFragment.setArguments(bundle);
//////                        busStopFragment.setArguments(bundle1);
////                        transaction.replace(R.id.bus_student_list, fragment2);
////                        transaction.replace(R.id.bus_stop_list, busStopFragment);
////                        transaction.commit();
////                        main.setSelectBusNumber(tv_busNumber.getText().toString());