package com.example.mobileassignment2.teeth_wars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


// Login Screen Activity
public class MainActivity extends AppCompatActivity {

    // Check Database and if first time launch
    SharedPreferences prefs = null;
    Database helper = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("com.example.mobileassignment2.teeth_wars", MODE_PRIVATE);
        setContentView(R.layout.activity_main);

    }

    // Check if Application First Run, if so Go Register Screen
    @Override
      protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            Intent a = new Intent(MainActivity.this, Register.class);
            startActivity(a);
            prefs.edit().putBoolean("firstrun", false).commit();

        }}

    // Register Screen
    public void onButtonClick(View v)
    {
        if (v.getId() == R.id.button3) {
            Intent a = new Intent(MainActivity.this, Register.class);
            startActivity(a);


        }

        // Check Login inputs
        if(v.getId() == R.id.button)
        {
            // Check Authentification
            EditText a = (EditText)findViewById(R.id.editText4); // Username
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.editText5); // Password
            String pass = b.getText().toString();
            String password = helper.searchPass(str); // Databse Helper

            // Password Check
            if(pass.equals(password))
            {
                Intent i = new Intent(MainActivity.this, Mainmenu.class);
                i.putExtra("Username",str);
                startActivity(i);
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this , "Your Username and password don't match!" , Toast.LENGTH_SHORT);
                temp.show();
            }
        }

        }
    }


