package kr.ac.mokwon.schoolbusclicker;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainActivityLoginButtonListFragment extends Fragment
{
    MainActivity main;
    public MainActivityLoginButtonListFragment()
    {
        super();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        main = (MainActivity)context;
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_main_login_btn_list_fragment, container, false);
        Button btn_student = view.findViewById(R.id.btn_student);
        Button btn_bus = view.findViewById(R.id.btn_bus);

        btn_student.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                main.ShowFragments(0);
                btn_student.setBackgroundResource(R.drawable.left_btn_background);
                btn_bus.setBackgroundResource(R.drawable.right_btn_background);
            }
        });

        btn_bus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                main.ShowFragments(1);
                btn_student.setBackgroundResource(R.drawable.left_btn_background2);
                btn_bus.setBackgroundResource(R.drawable.right_btn_background2);
            }
        });

        return view;
    }
}
