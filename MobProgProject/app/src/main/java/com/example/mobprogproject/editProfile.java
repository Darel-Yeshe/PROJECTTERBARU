package com.example.mobprogproject;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class editProfile extends AppCompatActivity {

    private TextInputEditText editName, editAge, editWeight, editHeight, editGender;
    private Button confButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        editGender = findViewById(R.id.editGender);
        confButton = findViewById(R.id.confirm);

        dbHandler = new DBHandler(editProfile.this);

        loadUserProfile();

        confButton.setOnClickListener(v -> {
            String userName = editName.getText().toString().trim();
            String userAge = editAge.getText().toString().trim();
            String userWeight = editWeight.getText().toString().trim();
            String userHeight = editHeight.getText().toString().trim();
            String userGender = editGender.getText().toString().trim();

            if (userName.isEmpty() || userAge.isEmpty() || userWeight.isEmpty() || userHeight.isEmpty() || userGender.isEmpty()) {
                Toast.makeText(editProfile.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
            String email = preferences.getString("USER_EMAIL", null);

            if (email != null) {
                dbHandler.updateUserProfile(email, userName, userAge, userWeight, userHeight, userGender);
                Toast.makeText(editProfile.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(editProfile.this, "Error: No logged-in user found.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserProfile() {
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

                editName.setText(name);
                editAge.setText(age);
                editWeight.setText(weight);
                editHeight.setText(height);
                editGender.setText(gender);
            } else {
                Toast.makeText(this, "No profile data found.", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Error: No logged-in user found.", Toast.LENGTH_SHORT).show();
        }
    }
}