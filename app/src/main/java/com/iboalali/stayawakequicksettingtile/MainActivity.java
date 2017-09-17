package com.iboalali.stayawakequicksettingtile;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final int STAY_AWAKE_ON = 7;
    private final int STAY_AWAKE_OFF = 0;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchStayAwakeSettings();
            }
        });



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            int currentFlag = Settings.Global.getInt(getContentResolver(), Settings.Global.STAY_ON_WHILE_PLUGGED_IN);
            Log.d(TAG, "OnResume: Stay Awake Flag: " + String.valueOf(currentFlag));

            tv.setText(String.valueOf(currentFlag));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void switchStayAwakeSettings(){
        int currentFlag = 999999999;
        try {
            currentFlag = Settings.Global.getInt(getContentResolver(), Settings.Global.STAY_ON_WHILE_PLUGGED_IN);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Stay Awake Flag: " + String.valueOf(currentFlag));

        if (currentFlag == STAY_AWAKE_OFF) {
            Settings.Global.putInt(getContentResolver(), Settings.Global.STAY_ON_WHILE_PLUGGED_IN, STAY_AWAKE_ON);
            //onView(withText("Stay awake")).perform(click());
            //onView(withText("what")).perform(click());
        }else if(currentFlag == STAY_AWAKE_ON){
            //onView(withText("what")).perform(click());
            //onView(withText("Stay awake")).perform(click());
            Settings.Global.putInt(getContentResolver(), Settings.Global.STAY_ON_WHILE_PLUGGED_IN, STAY_AWAKE_OFF);
        }

        Log.d(TAG, "Stay Awake Flag changed too: " + String.valueOf(currentFlag));
        tv.setText(String.valueOf(currentFlag));
    }
}
