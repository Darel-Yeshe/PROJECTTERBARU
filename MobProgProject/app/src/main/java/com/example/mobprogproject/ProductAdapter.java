package com.example.mobprogproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.io.InputStream;
import java.util.List;

import Modul.Product;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> products;
    private Context context;

    public ProductAdapter( Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvProductName.setText(p.getName());
        holder.itemView.setOnClickListener((v) -> {
            Toast.makeText(context, p.getName(), Toast.LENGTH_SHORT).show();
        });
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(p.getImageFileName());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.Image.setImageBitmap(bitmap);
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        holder.itemView.setOnClickListener((v) -> {
            Intent intent = new Intent(context, DetailProduct.class);
            intent.putExtra("PRODUCT_NAME", p.getName());
            intent.putExtra("PRODUCT_IMAGE", p.getImageFileName());
            intent.putExtra("origins", p.getOrigin());
            intent.putExtra("foodType", p.getFoodType());
            intent.putExtra("ingredients", p.getIngredients());
            intent.putExtra("hourToMake", p.getHourToMake());
            intent.putExtra("step", p.getStep());
//            intent.putExtra("likes", p.getLikes());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView tvProductName;
        ImageView Image;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.product_name);
            Image = itemView.findViewById(R.id.image);
        }
    }
}
