package kr.ac.mokwon.schoolbusclicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.lin_btn_list, new MainActivityLoginButtonListFragment());
        ft.commitAllowingStateLoss();
        ShowFragments(0);
    }

    public void ShowFragments(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (i)
        {
            case 0:
                ft.replace(R.id.lin_login_list, new MainActivityLoginStudentFragment());
                ft.commitAllowingStateLoss();
                break;
            case 1:
                ft.replace(R.id.lin_login_list, new MainActivityLoginBusFragment());
                ft.commitAllowingStateLoss();
                break;
        }
    }
}