package kr.ac.mokwon.ch12_03_preftest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    static String PREF_NAME = "MyPref";
    EditText editText;
    Button btn_save, btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_name);
        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);

        btn_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences setting = getSharedPreferences(PREF_NAME, 0);
                SharedPreferences.Editor editor = setting.edit();
                editor.putString("name", editText.getText().toString());
                editor.commit();
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences setting = getSharedPreferences(PREF_NAME, 0);
                editText.setText(setting.getString("name", ""));
            }
        });
    }

    ;
}