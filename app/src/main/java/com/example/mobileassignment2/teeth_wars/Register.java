package com.example.mobileassignment2.teeth_wars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// Register Screen One
public class Register extends AppCompatActivity {

    // Link with Database
    Database helper = new Database(this); // Call Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    // On Button Press
    public void onSignUpClick(View v) {
        if (v.getId() == R.id.button2) {

            // Assign all edit text fields a Value
            EditText fname = (EditText) findViewById(R.id.editText2); // Name
            EditText sname = (EditText) findViewById(R.id.editText3); // Email
            EditText username = (EditText) findViewById(R.id.editText); // Username
            EditText pass1 = (EditText) findViewById(R.id.editText6); // Username
            EditText pass2 = (EditText) findViewById(R.id.editText7); // Username


            //Error Handel
            if (fname.getText().toString().equals("") || sname.getText().toString().equals("") || pass2.getText().toString().equals("") || pass1.getText().toString().equals("") || username.getText().toString().equals("")) {
                Toast pass = Toast.makeText(Register.this, "Please Fill in all fields!", Toast.LENGTH_SHORT);
                pass.show();
                ;
            } else {


                // Strings to Database
                String namestr = fname.getText().toString();
                String snamestr = sname.getText().toString();
                String unamestr = username.getText().toString();
                String pass1str = pass1.getText().toString();
                String pass2str = pass2.getText().toString();
                String avatar = "o";
                int Coins = 0;

                if (!pass1str.equals(pass2str)) {
                    //Error Handel Passwords Do Not Math
                    Toast pass = Toast.makeText(Register.this, "Your Passwords Do Not Match, Try Again!", Toast.LENGTH_SHORT);
                    pass.show();
                } else {

                    Contact c = new Contact();
                    c.setName(namestr);
                    c.setSname(snamestr);
                    c.setUname(unamestr);
                    c.setPass(pass1str);
                    c.setAvatar(avatar);
                    c.setCoins(Coins);

                    //Displays Message to Know user has completed sign in process
                    helper.insertContact(c);
                    Toast pass = Toast.makeText(Register.this, "You Have Registered!", Toast.LENGTH_SHORT);
                    pass.show();


                    // Parse Data to Register Screen Two
                    Intent b = new Intent(Register.this, RegisterS2.class);
                    b.putExtra("Username", unamestr);
                    b.putExtra("Firstname", namestr);
                    startActivity(b);
                }

            }
        }
    }
}