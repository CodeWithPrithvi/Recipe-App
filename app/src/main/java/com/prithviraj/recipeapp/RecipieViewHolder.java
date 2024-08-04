package com.prithviraj.recipeapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipieViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    LinearLayout linearLayout;
    public RecipieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.foodimage);
        textView = itemView.findViewById(R.id.fooddescription);
        linearLayout = itemView.findViewById(R.id.item);
    }
}
