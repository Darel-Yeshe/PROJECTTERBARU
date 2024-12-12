package com.example.mobprogproject;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BMIPage extends AppCompatActivity {

    private TextView weightValue, heightValue, bmiValue;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmipage);

        weightValue = findViewById(R.id.weightValue);
        heightValue = findViewById(R.id.heightValue);
        bmiValue = findViewById(R.id.bmiValue);

        dbHandler = new DBHandler(BMIPage.this);

        loadUserData();

        ImageView backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(v -> finish());
    }

    private void loadUserData() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        String email = preferences.getString("USER_EMAIL", null);

        if (email != null) {
            Cursor cursor = dbHandler.getUserProfile(email);
            if (cursor.moveToFirst()) {
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String height = cursor.getString(cursor.getColumnIndex("height"));

                weightValue.setText(weight);
                heightValue.setText(height);

                calculateBMI(weight, height);
            } else {
                weightValue.setText("0");
                heightValue.setText("0");
                bmiValue.setText("0");
            }
            cursor.close();
        } else {
            weightValue.setText("0");
            heightValue.setText("0");
            bmiValue.setText("0");
        }
    }

    private void calculateBMI(String weight, String height) {
        try {
            float weightValue = Float.parseFloat(weight);
            float heightValue = Float.parseFloat(height) / 100; // Convert height to meters

            float bmi = weightValue / (heightValue * heightValue);

            bmiValue.setText(String.format("%.2f", bmi));
        } catch (NumberFormatException e) {
            bmiValue.setText("Invalid Data");
        }
    }
}