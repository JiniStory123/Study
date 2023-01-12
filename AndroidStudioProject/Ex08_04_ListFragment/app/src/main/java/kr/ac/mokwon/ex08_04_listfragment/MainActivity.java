package kr.ac.mokwon.ex08_04_listfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    Button btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.lin, new FragmentBtn(), "one");
        ft.commitAllowingStateLoss();
    }

    public void ShowFragments(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (i)
        {
            case 0:
                ft.replace(R.id.linear, new Fragment1(), "one");
                ft.commitAllowingStateLoss();
                break;
            case 1:
                ft.replace(R.id.linear, new Fragment2(), "one");
                ft.commitAllowingStateLoss();
                break;
            case 2:
                ft.replace(R.id.linear, new Fragment3(), "one");
                ft.commitAllowingStateLoss();
                break;
            case 3:
                ft.replace(R.id.linear, new Fragment4(), "one");
                ft.commitAllowingStateLoss();
                break;
        }
    }
}