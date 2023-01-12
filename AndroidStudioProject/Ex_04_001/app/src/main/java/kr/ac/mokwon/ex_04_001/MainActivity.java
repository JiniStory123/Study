package kr.ac.mokwon.ex_04_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    boolean isFirst;
    int calcId;
    int number1;
    int number2;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // 메인 레이아웃
        LinearLayout lLayout = new LinearLayout(this);
        lLayout.setOrientation(LinearLayout.VERTICAL);

        // 서브 레이아웃
        LinearLayout subLayout[] = new LinearLayout[5];
        for(int i=0; i<5; i++)
        {
            subLayout[i] = new LinearLayout(this);
            subLayout[i].setOrientation(LinearLayout.HORIZONTAL);
        }
        for(int i=0; i<5; i++)
            lLayout.addView(subLayout[i]); // 메인 레이아웃에 서브 레이아웃 add

        // 사이즈 선언
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);

        // 상단 TextView
        txt = new TextView(this);
        txt.setText("");
        txt.setTextSize(30);
        // 하단 공백 생성용 TextView
        TextView nullText = new TextView(this);
        nullText.setLayoutParams(params);

        // 버튼 배열 생성, 초기화
        Button btn[] = new Button[15];
        for(int i=0; i<15; i++)
        {
            btn[i] = new Button(this);
            if(i<10)
                btn[i].setText(i + "");
            else
            {
                switch(i)
                {
                    case 10:
                        btn[i].setText("+");
                        break;
                    case 11:
                        btn[i].setText("-");
                        break;
                    case 12:
                        btn[i].setText("x");
                        break;
                    case 13:
                        btn[i].setText("/");
                        break;
                    case 14:
                        btn[i].setText("=");
                        break;
                }
            }
            btn[i].setLayoutParams(params);
        }

        // 서브 레이아웃 구성
        subLayout[0].addView(txt); // sub 0번
        for(int i=7; i<=9; i++)
            subLayout[1].addView(btn[i]);
        subLayout[1].addView(btn[10]); // sub 1번

        for(int i=4; i<=6; i++)
            subLayout[2].addView(btn[i]);
        subLayout[2].addView(btn[11]); // sub 2번

        for(int i=1; i<=3; i++)
            subLayout[3].addView(btn[i]);
        subLayout[3].addView(btn[12]); // sub 3번

        subLayout[4].addView(nullText);
        subLayout[4].addView(btn[0]);
        subLayout[4].addView(btn[14]);
        subLayout[4].addView(btn[13]); // sub 4번

        setContentView(lLayout);

        isFirst = true;
        number1 = 0;
        number2 = 0;
        calcId = 0;

        // 버튼 리스너 구성
        ButtonListener bl = new ButtonListener();

        for(int i=0; i<15; i++)
            btn[i].setOnClickListener(bl);

        /*
        String[] strArray = {"7", "8", "9","+",
                            "4", "5", "6", "-",
                            "1", "2", "3", "x",
                            " ", "0", "=", "/"};

        Button[] btn = new Button[strArray.length];
        for(int i=0; i<btn.length; i++)
        {
            btn[i] = new Button(this);
            btn[i].setText(strArray[i]);
            btn[i].setOnClickListener(bl);
        }

        txt.addView(lLayout);
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4; j++)
            {
                int index = i * 4 + j;
                subLayout[i].addView(btn[index]);
            }
        }
        btn[12].setVisibility(View.INVISIBLE);

        setContentView(lLayout);
         */

    }

    class ButtonListener implements View.OnClickListener
    {
        void CalcNumber()
        {
            if(number2 == 0)
                return;
            switch(calcId)
            {
                case 1:
                    number1 += number2;
                    number2 = 0;
                    txt.setText(number1+"");
                    break;
                case 2:
                    number1 -= number2;
                    number2 = 0;
                    txt.setText(number1+"");
                    break;
                case 3:
                    number1 *= number2;
                    number2 = 0;
                    txt.setText(number1+"");
                    break;
                case 4:
                    number1 /= number2;
                    number2 = 0;
                    txt.setText(number1+"");
                    break;
            }
        }

        @Override
        public void onClick(View view)
        {
            String str = ((Button)view).getText().toString();

            switch (str)
            {
                case "+":
                    if(calcId > 0)
                        CalcNumber();
                    else
                    {
                        if(sum > 0)
                            number1 = sum;
                    }
                    calcId = 1;
                    isFirst = true;
                    break;
                case "-":
                    if(calcId > 0)
                        CalcNumber();
                    else
                    {
                        if(sum > 0)
                            number1 = sum;
                    }
                    calcId = 2;
                    isFirst = true;
                    break;
                case "x":
                    if(calcId > 0)
                        CalcNumber();
                    else
                    {
                        if(sum > 0)
                            number1 = sum;
                    }
                    calcId = 3;
                    isFirst = true;
                    break;
                case "/":
                    if(calcId > 0)
                        CalcNumber();
                    else
                    {
                        if(sum > 0)
                            number1 = sum;
                    }
                    calcId = 4;
                    isFirst = true;
                    break;
                case "=":
                    switch (calcId)
                    {
                        case 1:
                            sum = number1 + number2;
                            txt.setText(sum+"");
                            break;
                        case 2:
                            sum = number1 - number2;
                            txt.setText(sum+"");
                            break;
                        case 3:
                            sum = number1 * number2;
                            txt.setText(sum+"");
                            break;
                        case 4:
                            sum = number1 / number2;
                            txt.setText(sum+"");
                            break;
                    }
                    number1 = number2 = 0;
                    calcId = 0;
                    break;
                default:
                    int number = Integer.parseInt(str);

                    if(calcId == 0)
                    {
                        if(isFirst)
                        {
                            number1 = number;
                            isFirst = false;
                        }
                        else
                        {
                            number1 *= 10;
                            number1 += number;
                        }
                        txt.setText(number1+"");
                    }
                    else {
                        if (isFirst)
                        {
                            number2 = number;
                            isFirst = false;
                        }
                        else
                        {
                            number2 *= 10;
                            number2 += number;
                        }
                        txt.setText(number2+"");
                    }
                    break;
            }

            // txt.setText(str2 + str);
        }
    }
}