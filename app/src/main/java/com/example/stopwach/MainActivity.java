package com.example.stopwach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private long millisec;
    private boolean running;
    long startMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTimer();
    }


    public void onClickStart(View view) {
        running = true;
        if (millisec == 0) {
            startMillis = System.currentTimeMillis();
        }   else {
            startMillis = System.currentTimeMillis() - millisec;
        }
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        millisec = 0;
    }

    private void runTimer() {
        final TextView textView = (TextView)findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long minutes = millisec / 60000;
                long secon = (millisec % 60000) / 1000;
                long millis = millisec % 100;

                String time = String.format("%02d:%02d:%02d", minutes, secon, millis);
                textView.setText(time);
                if(running) {
                    millisec = System.currentTimeMillis()-startMillis;
                }
                handler.postDelayed(this,10);
            }
        });

    }
}
