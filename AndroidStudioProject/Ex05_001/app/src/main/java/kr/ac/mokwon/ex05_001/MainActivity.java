package kr.ac.mokwon.ex05_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.txt);
        TextView text2 = findViewById(R.id.txt2);
        VolumeController volume = findViewById(R.id.volume);
        RatingBar ration = findViewById(R.id.ration);

        volume.setKnobChangeListener(new VolumeController.KnobListener() {
            @Override
            public void OnChange(float angle) {
                text.setText(angle+"");
                if(angle < 0)
                    angle += 360;
                angle /= (float)60;
                ration.setRating(angle);
                text2.setText(angle+"");
            }
        });
    }
}