package com.prithviraj.recipeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Callback;

public class RecipeAdapter extends RecyclerView.Adapter<RecipieViewHolder> {
    private List<Category> categoryList;
    private OnItemClickListener listener;

    public RecipeAdapter(List<Category> categoryList, OnItemClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecipieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipieitems, parent, false);
        return new RecipieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipieViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Category category = categoryList.get(position);
        holder.textView.setText(category.getStrCategory());
        Glide.with(holder.itemView.getContext())
                .load(category.getStrCategoryThumb())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(category.getStrCategory()));


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String categoryName);
    }
}
