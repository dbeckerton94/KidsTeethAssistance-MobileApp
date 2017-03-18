package com.example.mobileassignment2.teeth_wars;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;


// Camera Feature
public class Game extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;

    // Output of Picture
    ImageView mimageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Intilise Buttons and Imageview
        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        Button button = (Button) this.findViewById(R.id.take_image_from_camera);
    }

    // Activate Phone Camera
    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
        startActivityForResult(cameraIntent, CAMERA_REQUEST);


    }

    // Request Image taken and Save with random interger
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(mphoto);


            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/DCIM/myCapturedImages");
            myDir.mkdirs();
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            String fname = "FILENAME-"+ n +".jpg";
            File file = new File (myDir, fname);
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                mphoto.compress(Bitmap.CompressFormat.JPEG, 100, out);
                Toast.makeText(Game.this, "Image Saved", Toast.LENGTH_SHORT).show();
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            getApplicationContext().sendBroadcast(mediaScanIntent);





        }
    }




}



