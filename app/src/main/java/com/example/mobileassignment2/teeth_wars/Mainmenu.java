
package com.example.mobileassignment2.teeth_wars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

// Main Menu Activity
public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }


    // Bottom Navigation Menu
    public void onImageClick(View v) {
        final String username1 = getIntent().getStringExtra("Username");

        if (v.getId() == R.id.startbrush) {
            Intent b = new Intent(Mainmenu.this, Brushing.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton3) {
            Intent b = new Intent(Mainmenu.this, Store.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton) {
            Intent b = new Intent(Mainmenu.this, AlarmManagerActivity.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton2) {
            Intent b = new Intent(Mainmenu.this, profile.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }
    }
}






