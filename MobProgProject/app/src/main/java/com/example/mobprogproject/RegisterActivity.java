package com.example.mobprogproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput, nameInput, ageInput, weightInput, heightInput, genderInput;
    private Button registerButton, returnButton;
    private DBHandler dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        genderInput = findViewById(R.id.genderInput);
        registerButton = findViewById(R.id.registerButton);

        dbHandler = new DBHandler(RegisterActivity.this);

        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> {
            finish();
        });
        registerButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String name = nameInput.getText().toString();
            String age = ageInput.getText().toString();
            String weight = weightInput.getText().toString();
            String height = heightInput.getText().toString();
            String gender = genderInput.getText().toString();

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHandler.addNewUser(email, password, name, age, weight, height, gender);
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}