package com.example.mobprogproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Modul.Product;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<>();
    private RecyclerView rvProduct;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(v -> {
            finish();
        });

        ImageView splashImage = findViewById(R.id.ic);
        try {
            InputStream inputStream = getAssets().open("img.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            splashImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView buttonProfile = findViewById(R.id.userPP);
        buttonProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfilePage.class);
            startActivity(intent);
        });

        rvProduct = findViewById(R.id.rv_product);
        rvProduct.setLayoutManager(new GridLayoutManager(this,2));

        products.add(new Product("Bubur Manado", "bubur.jpeg", "North Sulawesi", "Porridge", "1. Rice\n2. Spinach\n3. Pumpkin\n4. Corn", "30 minutes", "1. Cook rice in water until it becomes porridge-like.\n2. Add chopped spinach, pumpkin, and corn\n3. cook until vegetables are tender.\n4. Serve hot with additional toppings like fried shallots."));
        products.add(new Product("Caprese Skewers", "caprese_skewers.jpeg", "Italy", "Appetizer", "1. Cherry Tomatoes\n2. Mozzarella Balls\n3. Fresh Basil Leaves\n4. Balsamic Glaze", "45 minutes", "1. Thread cherry tomatoes and mozzarella balls onto skewers with basil leaves.\n2. Drizzle with balsamic glaze before serving."));
        products.add(new Product("Chickpea Curry", "chickpea_curry.jpeg", "India", "Curry", "1. Chickpeas\n2. Coconut Milk\n3. Curry Powder\n4. Spinach", "45 minutes", "1. Sauté onions with curry powder in a pot.\n2. Add chickpeas and coconut milk; simmer for about 10 minutes.\n3. Stir in spinach until wilted before serving."));
        products.add(new Product("Gado-Gado", "gado.jpeg", "Betawi", "Salad", "1. Mixed Vegetables (cabbage, spinach, bean sprouts, carrots)\n2. Tofu\n3. Tempeh\n4. Hard-boiled eggs", "45 minutes", "1. Blanch or lightly boil the vegetables until tender.\n2. Fry the tofu and tempeh until golden brown.\n3. Arrange the vegetables, tofu, tempeh, and eggs on a plate.\n4. Drizzle with peanut sauce before serving."));
        products.add(new Product("Greek Salad", "greek.jpeg", "Greece", "Salad", "1. Cucumber\n2. Tomatoes\n3. Red Onion\n4. Feta Cheese\n5. Olives\n6. Olive Oil\n7. Oregano", "15 Minutes", "1. Chop cucumbers, tomatoes, and red onion.\n2. Combine in a bowl with feta cheese and olives.\n3. Drizzle with olive oil and sprinkle oregano before serving."));
        products.add(new Product("Grilled Chicken Tacos", "grilled_chicken_tacos.jpeg", "Mexico", "Mexican Dish", "1. Chicken Breast\n2. Corn Tortilla\n3. Avocado\n4. Salsa", "30 Minutes", "1. Grill chicken until cooked through; slice into strips.\n2. Serve in corn tortillas topped with avocado and salsa."));
        products.add(new Product("Grilled Shrimp Salad", "grilled_shrimp_Salad.jpeg", "Thailand", "Salad", "1. Shrimp\n2. Mixed Greens\n3. Lime Dressing", "30 Minutes", "1. Grill shrimp until cooked through.\n2. Toss over mixed greens drizzled with lime dressing before serving cold or warm."));
        products.add(new Product("Hummus with Vegetables", "hummus.jpeg", "Middle East", "Snack", "1. Chickpeas\n2. Tahini\n3. Garlic\n4. Lemon Juice\n5. Assorted Vegetables for Dipping", "30 Minutes", "1. Blend chickpeas with tahini, garlic, and lemon juice until smooth.\n2. Serve with sliced vegetables like carrots and cucumbers."));
        products.add(new Product("Karedok", "karedok.jpeg", "West Java", "Salad", "1. Raw Vegetables (cucumber, bean sprouts, long beans)\n2. Peanut Sauce (peanuts, chili)", "15 minutes", "bercocok tanam"));
        products.add(new Product("Lentil Soup", "lentil_soup.jpeg", "Middle East", "Soup", "1. Lentils\n2. Carrots\n3. Celery\n4. Onion\n5. Garlic\n6. Vegetable Broth", "45 Minutes", "1. Sauté chopped onion, garlic, carrots, and celery in a pot.\n2. Add lentils and vegetable broth; simmer until lentils are tender.\n3. Season with salt and pepper before serving."));
        products.add(new Product("Oatmeal Bowl", "oatmeal.jpeg", "USA", "Snack", "1. Oats\n2. Almond Milk\n3. Fruits\n4. Nuts/Seeds", "15 Minutes", "1. Cook oats in almond milk until creamy.\n2. Top with fruits and nuts/seeds before serving warm."));
        products.add(new Product("Pecel", "pecel.jpeg", "East Java", "Salad", "1. Boiled Vegetables (long beans, cabbage)\n2. Peanut Sauce (peanuts, chili, garlic)", "30 Minutes", "1. Boil the vegetables until tender.\n2. Blend peanuts with chili and garlic to make peanut sauce\n3. Serve the boiled vegetables drizzled with peanut sauce."));
        products.add(new Product("Quinoa salad", "quinoa_salad.jpeg", "Peru", "Salad", "1. Quinoa\n2. Bell Peppers\n3. Corn\n4. Black Beans\n5. Lime Juice\n6. Cilantro", "30 Minutes", "1. Cook quinoa according to package instructions.\n2. Mix cooked quinoa with diced bell peppers, corn, and black beans.\n3. Add lime juice and chopped cilantro before serving."));
        products.add(new Product("Roasted Vegetable Medley", "roasted_vegetable.jpeg", "France", "Side Dish", "1. Zucchini\n2. Bell Peppers\n3. Carrot\n4. Olive Oil\n5. Herbs de Provence", "45 Minutes", "1. Toss chopped vegetables with olive oil and herbs.\n2. Roast at 425°F (220°C) for about 25 minutes."));
        products.add(new Product("Smoothie Bowl", "smoothie.jpeg", "USA", "Snack", "1. Frozen Fruits (banana or berries)\n2. Yogurt or Almond Milk\n3. Toppings (Nuts or Seeds)", "15 Minutes", "1 .Blend frozen fruits with yogurt until smooth\n2. Pour into a bowl and top with nuts or seeds."));
        products.add(new Product("Soto Ayam", "soto.jpeg", "Java", "Soup", "1. Chicken\n2. Turmeric\n3. Lemongrass\n4. Galangal\n5. Vegetables (carrots, potatoes)", "45 Minutes", "1. Boil chicken with turmeric, lemongrass, and galangal until cooked.\n2. Remove chicken and shred it.\n3. Strain the broth and return it to the pot; add chopped vegetables.\n4. Serve with shredded chicken on top."));
        products.add(new Product("Spinach Frittata", "spinach_frittata.jpeg", "Italy", "Egg dish", "1. Eggs\n2. Spinach\n3. Feta Cheese\n4. Onion", "20 Minutes", "1. Sauté onions in a skillet; add spinach until wilted.\n 2. Whisk eggs with cheese; pour over the spinach mixture and cook until set."));
        products.add(new Product("Sushi Roll", "sushi_roll.jpeg", "Japan", "Appetizer", "1. Sushi Rice\n2. Nori Sheets\n3. Cucumber\n4. Avocado\n5. Fish", "30 Minutes", "1. Cook sushi rice and let it cool.\n2. Place a nori sheet on a bamboo mat and spread rice evenly.\n3. Add cucumber, avocado, and fish; roll tightly and slice."));
        products.add(new Product("Urap", "urap.jpeg", "Java", "Salad", "1. Steamed Vegetables (cabbage, spinach, green beans)\n2. Grated Coconut\n3. Spices (garlic, chili, salt)", "30 Minutes", "1. Steam the vegetables until cooked but still crisp.\n2. Mix grated coconut with minced garlic and chili.\n3. Combine the coconut mixture with the steamed vegetables and serve."));
        products.add(new Product("Vegetable Stir Fry", "vegetable_stir_fry.jpeg", "China ", "Stir Fry", "1. Mixed Vegetables (broccoli bell peppers)\n2. Soy Sauce\n3. Ginger", "20 Minutes", "1. Heat oil in a pan; add ginger and mixed vegetables.\n2. Stir-fry for a few minutes; add soy sauce before serving."));


        adapter = new ProductAdapter(this, products);
        rvProduct.setAdapter(adapter);
    }
}