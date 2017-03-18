package com.example.mobileassignment2.teeth_wars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


// Alarm Manager
public class AlarmManagerActivity extends Activity {

    private AlarmManagerBroadcastReceiver alarm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // Bottom Navigation Menu
    public void onImageClick(View v) {
        final String username1 = getIntent().getStringExtra("Username");

        if (v.getId() == R.id.imageButton2) {
            Intent b = new Intent(AlarmManagerActivity.this, Mainmenu.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton3) {
            Intent b = new Intent(AlarmManagerActivity.this, Store.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton4) {
            Intent b = new Intent(AlarmManagerActivity.this, profile.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }
    }

    // Call Broadcaster
    public void startRepeatingTimer(View view) {

        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.SetAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    // Cancel Broadcasr
    public void cancelRepeatingTimer(View view){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.CancelAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    // One time Broadcast
    public void onetimeTimer(View view){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_widget_alarm_manager, menu);
        return true;
    }


}