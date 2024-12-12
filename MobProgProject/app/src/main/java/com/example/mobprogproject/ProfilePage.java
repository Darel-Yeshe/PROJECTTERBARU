package com.example.mobprogproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilePage extends AppCompatActivity {

    private TextView profileName, profileAge, profileWeight, profileHeight, profileGender;
    private DBHandler dbHandler;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        profileName = findViewById(R.id.profileName);
        profileAge = findViewById(R.id.profileAge);
        profileWeight = findViewById(R.id.profileWeight);
        profileHeight = findViewById(R.id.profileHeight);
        profileGender = findViewById(R.id.profileGender);

        dbHandler = new DBHandler(ProfilePage.this);

        loadUserData();

        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(v -> finish());

        ImageView setButton = findViewById(R.id.setting);
        setButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilePage.this, editProfile.class);
            editProfileLauncher.launch(intent);
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilePage.this, LoginActivity.class);
            editProfileLauncher.launch(intent);
        });
    }

    private final ActivityResultLauncher<Intent> editProfileLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadUserData();
                }
            });

    @SuppressLint("Range")
    private void loadUserData() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        String email = preferences.getString("USER_EMAIL", null);

        if (email != null) {
            Cursor cursor = dbHandler.getUserProfile(email);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String height = cursor.getString(cursor.getColumnIndex("height"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));

                profileName.setText("Name: " + name);
                profileAge.setText("Age: " + age);
                profileWeight.setText("Weight: " + weight);
                profileHeight.setText("Height: " + height);
                profileGender.setText("Gender: " + gender);
            } else {
                Toast.makeText(this, "No profile found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
        }
    }
}