package com.example.kevinwu.kevalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker timePicker;
    private TextView textView;
    private ToggleButton toggleButton;
    public static MainActivity inst;

    public static MainActivity instance(){
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timePicker = (TimePicker) findViewById(R.id.alarm_time);
        textView = (TextView) findViewById(R.id.alarm_text);
        toggleButton = (ToggleButton) findViewById(R.id.alarm_toggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void setAlarm(View view){
        if(toggleButton.isChecked())
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());

            Intent intent = new Intent(MainActivity.this, AlarmReciever.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            textView.setText("Alarm is active");
        }
        else{
            alarmManager.cancel(pendingIntent);
            textView.setText("");
        }
    }

    public void setAlarmText(String msg){
        textView.setText(msg);
    }
}
