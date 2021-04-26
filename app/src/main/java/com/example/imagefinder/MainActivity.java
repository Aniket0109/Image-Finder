package com.example.imagefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText imageURL;

    ImageView imageView;

    public void downloadImage(View view){

        ImageDownloader task = new ImageDownloader();

        Bitmap myImage;

        try{

            myImage = task.execute(imageURL.getText().toString()).get();

            imageView.setImageBitmap(myImage);

        } catch (Exception e){

            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageURL = (EditText) findViewById(R.id.editTextTextPersonName2);

        imageView = (ImageView) findViewById(R.id.imageView);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStreamReader = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStreamReader);

                return myBitmap;

            } catch (Exception e){

                e.printStackTrace();

                return null;
            }


        }
    }

}