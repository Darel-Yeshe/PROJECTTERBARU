package com.example.mobprogproject;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;

public class DetailProduct extends AppCompatActivity {

    private boolean isCookNowClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailTextView = findViewById(R.id.detailTextView);
        TextView ori = findViewById(R.id.ori);
        TextView futy = findViewById(R.id.futy);
        TextView igd = findViewById(R.id.igd);
        TextView htm = findViewById(R.id.htm);

        String name = getIntent().getStringExtra("PRODUCT_NAME");
        String productImage = getIntent().getStringExtra("PRODUCT_IMAGE");
        String origins = getIntent().getStringExtra("origins");
        String foodType = getIntent().getStringExtra("foodType");
        String ingredients = getIntent().getStringExtra("ingredients");
        String hourToMake = getIntent().getStringExtra("hourToMake");
        String steps = getIntent().getStringExtra("step");

        detailTextView.setText(name);
        ori.setText("Origins : " + origins);
        futy.setText("Food Type : " + foodType);
        igd.setText("Ingredients : \n" + ingredients);
        htm.setText("Hour To Make : " + hourToMake);

        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(productImage);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            detailImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> {
            finish();
        });

        Button button = findViewById(R.id.cooknow);
        button.setOnClickListener(v -> {
            if (!isCookNowClicked) {
                igd.setText("Steps : \n" + steps);
                button.setText("DONE");
                isCookNowClicked = true;
            } else {
                finish();
            }
        });
    }
}