package kr.ac.mokwon.ch09_002_activityforresult;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int resNum;
    EditText edit01, edit02, edit03;
    TextView txt;
    ActivityResultLauncher<Intent> _startResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result->{
                if(result.getResultCode() == RESULT_OK) {
                    int n3 = result.getData().getIntExtra("n3", 0);
                    resNum = n3;
                    edit03.setText(n3+"");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit01 = findViewById(R.id.edit01);
        edit02 = findViewById(R.id.edit02);
        edit03 = findViewById(R.id.edit03);
        Button btn = findViewById(R.id.btn01);
        Button btn2 = findViewById(R.id.btn02);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("n1", Integer.parseInt(edit01.getText().toString()));
                intent.putExtra("n2", edit02.getText().toString());
                _startResult.launch(intent); // 반환값이 있기 때문에 이런 번거로운 처리가 필요한 것
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(edit01.getText().toString());
                int num2 = Integer.parseInt(edit02.getText().toString());
                int num3 = Integer.parseInt(edit03.getText().toString());

                if(num3 == num1 * num2)
                    Toast.makeText(getApplicationContext(), "정답입니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "오답입니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}