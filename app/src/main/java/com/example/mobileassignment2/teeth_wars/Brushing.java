package com.example.mobileassignment2.teeth_wars;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


// Brushing Activity
public class Brushing extends AppCompatActivity {

    // Database Link
    Database helper = new Database(this);
    // Media Player Link
    MediaPlayer mySound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brushing);
        String username = getIntent().getStringExtra("Username");

        // Start Media Player
        mySound = MediaPlayer.create(this, R.raw.pokemon);

        // Get Avatar Value
        String avatar = helper.getAvatar(username);

        // Get Coin Value and Set Value
        Integer coins = helper.getCoins(username);
        TextView tv13 = (TextView) findViewById(R.id.avatardatabase);
        tv13.setText(String.valueOf(coins));


        // Check and Output of Avatar Value
        if (avatar.equals("SpondgeBob")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.image);
            imageView2.setImageResource(R.drawable.bobww);

        }
        if (avatar.equals("pokemon1")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.image);
            imageView2.setImageResource(R.drawable.pokemon1);
        }
        if (avatar.equals("pokemon2")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.image);
            imageView2.setImageResource(R.drawable.pokemon2);
        }

        if (avatar.equals("angrybird")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.image);
            imageView2.setImageResource(R.drawable.angry);
        }
        if (avatar.equals("lorax")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.image);
            imageView2.setImageResource(R.drawable.lorax);
        }

        if (avatar.equals("plant")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.image);
            imageView2.setImageResource(R.drawable.plant);
        }
    }

    // Bottom Navigation Menu
    public void onBrushteeth(View v) {
        final String username1 = getIntent().getStringExtra("Username");

        if (v.getId() == R.id.startbutton) {
            Brush();
        }

        if (v.getId() == R.id.imageButton3) {
            Intent b = new Intent(Brushing.this, Store.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton) {
            Intent b = new Intent(Brushing.this, Game.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton2) {
            Intent b = new Intent(Brushing.this, Mainmenu.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }
        if (v.getId() == R.id.imageButton4) {
            Intent b = new Intent(Brushing.this, profile.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.photo) {
            Intent b = new Intent(Brushing.this, Game.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

    }



    public void Brush() {
        mySound.start(); // Start Media Player
        View b = findViewById(R.id.startbutton);
        b.setVisibility(View.GONE); // Make Button invisible

        // Circular Timer Process
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);


        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        // Animation Process
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(120000);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        final TextView textic = (TextView) findViewById(R.id.tv);


        // Additional Text Timer
        CountDownTimer Count = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                textic.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            // When Timer Finishes
            public void onFinish() {
                // Update Coin Value and Visibility value
                String username = getIntent().getStringExtra("Username");
                Integer coins = helper.getCoins(username);
                TextView tv13 = (TextView) findViewById(R.id.avatardatabase);
                int coins2;

                coins2 = coins + 1;
                tv13.setText(String.valueOf(coins2));
                String msg2 = String.valueOf(tv13.getText());
                helper.editcoins(username, msg2);
                textic.setText("Well Done!");
                View c = findViewById(R.id.photo);
                c.setVisibility(View.VISIBLE);
                View d = findViewById(R.id.welldone);
                d.setVisibility(View.VISIBLE);
            }
        };
        Count.start();
                }
            }



