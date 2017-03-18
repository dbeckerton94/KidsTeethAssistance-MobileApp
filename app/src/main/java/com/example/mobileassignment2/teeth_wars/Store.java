package com.example.mobileassignment2.teeth_wars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



// Store Activity
public class Store extends AppCompatActivity {

    // Intilise Integers
    public int getScore = 0; // Coins
    public int cost;         // Costs

    // Link Database
    Database helper = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Check Users Coin value then Print to Textview
        CheckValue();
        String username = getIntent().getStringExtra("Username");
        Integer coins = helper.getCoins(username);

        TextView tv13 = (TextView) findViewById(R.id.textView17);
        tv13.setText("Coins:" + (String.valueOf(coins)));
    }

    // Bottom Navigation Bar, Transfers Username as Intent
    public void onImageClick(View v) {
        final String username1 = getIntent().getStringExtra("Username");

        if (v.getId() == R.id.imageButton4) {
            Intent b = new Intent(Store.this, profile.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton3) {
            Intent b = new Intent(Store.this, Store.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton) {
            Intent b = new Intent(Store.this, AlarmManagerActivity.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton2) {
            Intent b = new Intent(Store.this, Mainmenu.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }
    }

    // Store Slideshow Next
    public void upScore() {
        if (getScore >= 5) {
            getScore = 0;

        } else
            getScore = getScore + 1;
    }

    // Store Slideshow Previous
    public void downScore() {

        if (getScore <= 0) {
        } else {

            getScore = getScore - 1;
        }
    }

    // Check Current Slide on Store
    public void CheckValue() {

        if (getScore <= 0) {
            TextView tv13 = (TextView) findViewById(R.id.textView13);
            tv13.setText("angrybird");
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.angry);

            cost = 1;
            TextView tv133 = (TextView) findViewById(R.id.priceav);
            tv133.setText("Costs" + (String.valueOf(cost)) + "Coins");
        }

        if (getScore == 1) {
            TextView tv13 = (TextView) findViewById(R.id.textView13);
            tv13.setText("lorax");
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.lorax);

            cost = 1;
            TextView tv133 = (TextView) findViewById(R.id.priceav);
            tv133.setText("Costs" + (String.valueOf(cost)) + "Coins");
        }

        if (getScore >= 2) {
            TextView tv13 = (TextView) findViewById(R.id.textView13);
            tv13.setText("plant");
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.plant);
            cost = 1;
            TextView tvc = (TextView) findViewById(R.id.costv);
            tvc.setText((String.valueOf(cost)));
            TextView tv133 = (TextView) findViewById(R.id.priceav);
            tv133.setText("Costs" + (String.valueOf(cost)) + "Coins");
        }
    }

    // Button Interaction with Slideshow

    public void onButtonClick(View v) {

        if (v.getId() == R.id.nextb) {
            upScore();
            CheckValue();
        }

        if (v.getId() == R.id.prevbut) {
            downScore();
            CheckValue();
        }


        if (v.getId() == R.id.avatarconf) {
            String username = getIntent().getStringExtra("Username");   // Get Username
            Integer coins = helper.getCoins(username);                  // Get Coins from Database

            if(coins >= cost ) // If Have More Coins than Cost
            {
                int coins2;
                coins2 = coins - cost;
                TextView tv13 = (TextView) findViewById(R.id.textView17);
                tv13.setText("Coins: " + (String.valueOf(coins2)));
                String msg2 = String.valueOf(coins2);
                helper.editcoins(username, msg2);


                TextView tv133 = (TextView) findViewById(R.id.textView13);
                String msg23 = String.valueOf(tv133.getText());
                helper.editavatar(username, msg23);
                Toast pass = Toast.makeText(Store.this, "Your Have Purchased your Avatar", Toast.LENGTH_SHORT);
                pass.show();

            }
            else  // Less Coins than Cost
            {
                Toast pass = Toast.makeText(Store.this, "You Cannot Afford this Avatar", Toast.LENGTH_SHORT);
                pass.show();
            }
        }

    }
}