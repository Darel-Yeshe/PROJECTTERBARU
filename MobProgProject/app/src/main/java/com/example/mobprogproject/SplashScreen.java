package com.example.mobprogproject;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView splashImage = findViewById(R.id.splashImage);

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("img.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            splashImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}