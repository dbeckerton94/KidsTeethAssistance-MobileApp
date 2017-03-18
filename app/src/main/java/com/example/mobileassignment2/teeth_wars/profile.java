package com.example.mobileassignment2.teeth_wars;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;


// Profile Screen
public class profile extends AppCompatActivity {

    // Link Database
    Database helper = new Database(this);

    // Link Phone Files
    File[] allFiles ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Location for Photo to Save to
        File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/myCapturedImages/");
        allFiles = folder.listFiles();

        // Get first name , second name and Avatar from Database
        String username = getIntent().getStringExtra("Username");
        String firstname = helper.getName(username);
        String secondname = helper.getSName(username);
        String avatar = helper.getAvatar(username);

        // Print Values to Textview
        TextView tv13 = (TextView) findViewById(R.id.textView12);
        tv13.setText("First Name: " + firstname);
        TextView tv14 = (TextView) findViewById(R.id.textView15);
        tv14.setText("Second Name: " + secondname);


        // Change Image Properities Dependent upon Avatar
        if (avatar.equals("SpondgeBob")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView4);
            imageView2.setImageResource(R.drawable.bobww);
        }
        if (avatar.equals("pokemon1")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView4);
            imageView2.setImageResource(R.drawable.pokemon1);
        }
        if (avatar.equals("pokemon2")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView4);
            imageView2.setImageResource(R.drawable.pokemon2);
        }
        if (avatar.equals("angrybird")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView4);
            imageView2.setImageResource(R.drawable.angry);

        }
        if (avatar.equals("lorax")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView4);
            imageView2.setImageResource(R.drawable.lorax);
        }
        if (avatar.equals("plant")) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView4);
            imageView2.setImageResource(R.drawable.plant);
        }

    }

    // Activate Phone Camera
    public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

        // Location to Save
        private MediaScannerConnection mMs;
        private File mFile;

        public SingleMediaScanner(Context context, File f) {
            mFile = f;
            mMs = new MediaScannerConnection(context, this);
            mMs.connect();
        }

        // Check Space and Path
        public void onMediaScannerConnected() {
            mMs.scanFile(mFile.getAbsolutePath(), null);
        }

        public void onScanCompleted(String path, Uri uri) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
            mMs.disconnect();
        }

    }

    // Bottom Navigation Menu
    public void onImageClick(View v) {
        final String username1 = getIntent().getStringExtra("Username");

        if (v.getId() == R.id.imageButton5) {
            Intent b = new Intent(profile.this, Mainmenu.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton3) {
            Intent b = new Intent(profile.this, Store.class);
            b.putExtra("Username", username1);
            startActivity(b);
                        }

        if (v.getId() == R.id.imageButton) {
            Intent b = new Intent(profile.this, AlarmManagerActivity.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.imageButton2) {
            Intent b = new Intent(profile.this, profile.class);
            b.putExtra("Username", username1);
            startActivity(b);
        }

        if (v.getId() == R.id.button44) {
            new SingleMediaScanner(profile.this, allFiles[0]);
        }
    }
}