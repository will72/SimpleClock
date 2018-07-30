package com.wharwell.simpleclock;

//ubuntu git test

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuActivity extends AppCompatActivity {

    String[] timesList;
    TextView timeTextView;
    TextView timesSecondsTextView;
    TextView amPmTextView;
    TextView dayTextView;
    TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        Typeface typeface = Typeface.createFromAsset(getApplicationContext()
                .getAssets(), "font/gotham_light.ttf");
        timeTextView = findViewById(R.id.time_text_view);
        timesSecondsTextView =
                findViewById(R.id.time_seconds_text_view);
        amPmTextView = findViewById(R.id.am_pm_text_view);
        dayTextView = findViewById(R.id.daY_text_view);
        dateTextView = findViewById(R.id.date_text_view);
        timeTextView.setTypeface(typeface);
        timesSecondsTextView.setTypeface(typeface);
        amPmTextView.setTypeface(typeface);
        dayTextView.setTypeface(typeface);
        dateTextView.setTypeface(typeface);

        timesList = getDateArray();
        timeTextView.setText(timesList[0]);
        timesSecondsTextView.setText(timesList[1]);
        amPmTextView.setText(timesList[2]);
        dayTextView.setText(timesList[3]);
        dateTextView.setText(timesList[4]);

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timesList = getDateArray();
                                timeTextView.setText(timesList[0]);
                                timesSecondsTextView.setText(timesList[1]);
                                amPmTextView.setText(timesList[2]);
                                dayTextView.setText(timesList[3]);
                                dateTextView.setText(timesList[4]);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    private String[] getDateArray() {
        String[] return_val = new String[5];
        Date currentDate = new Date();
        return_val[0] = new SimpleDateFormat("hh:mm")
                .format(currentDate);
        return_val[1] = new SimpleDateFormat("ss")
                .format(currentDate);
        return_val[2] = new SimpleDateFormat("a")
                .format(currentDate);
        return_val[3] = fullDay(new SimpleDateFormat("EEE")
                .format(currentDate));
        return_val[4] = new SimpleDateFormat("yyyy - MM - dd")
                .format(currentDate);
        return return_val;
    }

    private String fullDay(String dayIn) {
        dayIn = dayIn.toLowerCase();
        switch (dayIn) {
            case "mon":
                return "MONDAY";
            case "tue":
                return "TUESDAY";
            case "wed":
                return "WEDNESDAY";
            case "thu":
                return "THURSDAY";
            case "fri":
                return "FRIDAY";
            case "sat":
                return "SATURDAY";
            case "sun":
                return "SUNDAY";
            default:
                return "ERROR";
        }
    }
}
