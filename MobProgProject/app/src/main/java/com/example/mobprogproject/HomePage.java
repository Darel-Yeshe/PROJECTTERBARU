package com.example.mobprogproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;

public class HomePage extends AppCompatActivity {

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        dbHandler = new DBHandler(HomePage.this);

        ImageView splashImage = findViewById(R.id.ic);
        try {
            InputStream inputStream = getAssets().open("img.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            splashImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button buttonNutrition = findViewById(R.id.nutrition);
        buttonNutrition.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
        });

        Button buttonBMI = findViewById(R.id.BMI);
        buttonBMI.setOnClickListener(v -> navigateToBMIPage());

        ImageView buttonProfile = findViewById(R.id.userPP);
        buttonProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, ProfilePage.class);
            startActivity(intent);
        });
    }

    private void navigateToBMIPage() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        String email = preferences.getString("USER_EMAIL", null);

        if (email != null) {
            Cursor cursor = dbHandler.getUserProfile(email);
            if (cursor.moveToFirst()) {
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String height = cursor.getString(cursor.getColumnIndex("height"));

                try {
                    float weightValue = Float.parseFloat(weight);
                    float heightValue = Float.parseFloat(height) / 100;
                    float bmi = weightValue / (heightValue * heightValue);

                    if (bmi >= 19 && bmi <= 24) {
                        navigateToPage(BMIPage.class);
                    } else if (bmi > 24 && bmi <= 29) {
                        navigateToPage(FlatBMIPage.class);
                    } else {
                        navigateToPage(BadBMIPage.class);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid data for BMI calculation.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No user data available. Please update your profile.", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Toast.makeText(this, "No user logged in. Please log in first.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToPage(Class<?> activityClass) {
        Intent intent = new Intent(HomePage.this, activityClass);
        startActivity(intent);
    }
}