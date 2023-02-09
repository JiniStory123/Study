package kr.ac.mokwon.schoolbusclicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class BusStopListFragment extends Fragment
{
    BusMainActivityOld main;

    TextView tv_busStop;
    Button btn_busStop;

    // 제어변수들
    private String busNumber;
    private String busStop;
    private String date;
    boolean isThreadOn;

    // 스레드
    FragmentCallThread _thread;


    public BusStopListFragment()
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
                busStop = main.getBusStop();

                main.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tv_busStop.setText(busStop);
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
        busStop = main.getBusStop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_bus_stop_list_fragment, container, false);
        tv_busStop = view.findViewById(R.id.tv_bus_stop);
        btn_busStop = view.findViewById(R.id.btn_bus_stop);

        tv_busStop.setText(busStop);

        _thread = new FragmentCallThread();
        isThreadOn = true;
        _thread.start();

        btn_busStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SetPopupMenu(view);
            }
        });

        return view;
    }

    void SetPopupMenu(View view)
    {
        main.setCheck_start(0);
        main.setCheck_end(0);
        if (busNumber.equals("D1"))
        {
            final PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_bus_d1, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.action_S1:
                            main.setBusStop("산내소방서");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S2:
                            main.setBusStop("은어송초등학교");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S3:
                            main.setBusStop("효동현대아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S4:
                            main.setBusStop("대전역");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S5:
                            main.setBusStop("교보생명");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S6:
                            main.setBusStop("오류동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S7:
                            main.setBusStop("유천동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S8:
                            main.setBusStop("버드내아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S9:
                            main.setBusStop("도마동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S10:
                            main.setBusStop("정림삼거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S11:
                            main.setBusStop("가수원네거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S12:
                            main.setBusStop("수목토아파트");
//                            main.FragmentRecall();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        // 여기까지 D1

        if (busNumber.equals("D2"))
        {
            final PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_bus_d2, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.action_S1:
                            main.setBusStop("법동우체국");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S2:
                            main.setBusStop("동춘당");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S3:
                            main.setBusStop("웰니스요양병원");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S4:
                            main.setBusStop("대전복합터미널");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S5:
                            main.setBusStop("성남사거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S6:
                            main.setBusStop("목동금호한사랑아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S7:
                            main.setBusStop("용문동치안센터");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S8:
                            main.setBusStop("탄방동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S9:
                            main.setBusStop("큰마을네거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S10:
                            main.setBusStop("갈마서부농협");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S11:
                            main.setBusStop("대전일보");
//                            main.FragmentRecall();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        // 여기까지 D2

        if (busNumber.equals("D3"))
        {
            final PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_bus_d3, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.action_S1:
                            main.setBusStop("신탄진역");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S2:
                            main.setBusStop("신탄진톨게이트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S3:
                            main.setBusStop("대덕구보건소");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S4:
                            main.setBusStop("목상동파출소");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S5:
                            main.setBusStop("송강실내테니스장");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S6:
                            main.setBusStop("대덕테크노밸리아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S7:
                            main.setBusStop("전민동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S8:
                            main.setBusStop("한빛탑");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S9:
                            main.setBusStop("만년동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S10:
                            main.setBusStop("유성온천역3번출구");
//                            main.FragmentRecall();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        // 여기까지 D3

        if (busNumber.equals("H1"))
        {
            final PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_bus_h1, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.action_S1:
                            main.setBusStop("가수원네거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S2:
                            main.setBusStop("정림동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S3:
                            main.setBusStop("도마동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S4:
                            main.setBusStop("버드내아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S5:
                            main.setBusStop("유천동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S6:
                            main.setBusStop("서대전공원");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S7:
                            main.setBusStop("중구청2번출구");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S8:
                            main.setBusStop("대전역");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S9:
                            main.setBusStop("효동현대아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S10:
                            main.setBusStop("은어송아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S11:
                            main.setBusStop("산내소방서");
//                            main.FragmentRecall();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        // 여기까지 H1

        if (busNumber.equals("H2"))
        {
            final PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_bus_h2, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.action_S1:
                            main.setBusStop("유성온천역2번출구");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S2:
                            main.setBusStop("월평삼거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S3:
                            main.setBusStop("대전일보");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S4:
                            main.setBusStop("갈마네거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S5:
                            main.setBusStop("큰마을네거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S6:
                            main.setBusStop("롯데백화점");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S7:
                            main.setBusStop("용문동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S8:
                            main.setBusStop("목동더샵리슈빌아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S9:
                            main.setBusStop("성남사거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S10:
                            main.setBusStop("대전복합터미널");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S11:
                            main.setBusStop("동춘당");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S12:
                            main.setBusStop("법동우체국");
//                            main.FragmentRecall();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        // 여기까지 H2

        if (busNumber.equals("H3"))
        {
            final PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_bus_h3, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.action_S1:
                            main.setBusStop("유성온천역7번출구");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S2:
                            main.setBusStop("충남대학교");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S3:
                            main.setBusStop("궁동로데오거리");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S4:
                            main.setBusStop("유성구청");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S5:
                            main.setBusStop("월평동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S6:
                            main.setBusStop("대덕초등학교도룡분교장");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S7:
                            main.setBusStop("전민동");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S8:
                            main.setBusStop("대덕테크노밸리아파트");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S9:
                            main.setBusStop("송강실내테니스장");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S10:
                            main.setBusStop("목상동행정복지센터");
//                            main.FragmentRecall();
                            break;
                        case R.id.action_S11:
                            main.setBusStop("신탄진역");
//                            main.FragmentRecall();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        // 여기까지 H2
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