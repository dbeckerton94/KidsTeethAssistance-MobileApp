package com.example.mobileassignment2.teeth_wars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


// Register Screen Two
public class RegisterS2 extends AppCompatActivity {

    public int getScore = 0;  // Initilise Current Slide (Slideshow)
    Database helper = new Database(this); // Link Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_s2);
    }

    // Next Slide iteration
    public void upScore() {
        if (getScore >= 2){
        }
        else
        getScore = getScore + 1;
    }

    // Previous Slide iteration
    public void downScore(){
        if (getScore <= 0){
        }
        else{
            getScore = getScore - 1;
        }
    }

    // Display Avatar dependent upon Slide

    public void CheckValue() {

        if(getScore <= 0){
            TextView tv13 = (TextView) findViewById(R.id.avatartext);
            tv13.setText("SpondgeBob");
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.bobww);
        }
        if(getScore == 1) {
            TextView tv13 = (TextView) findViewById(R.id.avatartext);
            tv13.setText("pokemon1");
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.pokemon1);
        }
        if(getScore >= 2){
            TextView tv13 = (TextView) findViewById(R.id.avatartext);
            tv13.setText("pokemon2");
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.pokemon2);
        }
    }

    public void onButtonClick(View v) {

        // Button/Slideshow interaction
        if (v.getId() == R.id.nextb) {
            upScore();
            CheckValue();
        }

        // Button/Slideshow interaction
        if (v.getId() == R.id.prevbut) {
            downScore();
            CheckValue();
        }


        // Confirm Avatar Choice
        if (v.getId() == R.id.avatarconf) {
            TextView tv13 = (TextView) findViewById(R.id.avatartext);
            String msg2 = String.valueOf(tv13.getText());
            final String username = getIntent().getStringExtra("Username");
            final String firstname = getIntent().getStringExtra("Firstname");

            helper.editavatar(username, msg2);
            Toast pass = Toast.makeText(RegisterS2.this, "Your Avatar Yes", Toast.LENGTH_SHORT);
            pass.show();

            Intent b = new Intent(RegisterS2.this, Mainmenu.class);
            b.putExtra("Username", username);
            b.putExtra("Firstname", firstname);
            startActivity(b);
        }
    }
}