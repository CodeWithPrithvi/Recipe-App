package com.prithviraj.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity implements RecyclerViewInterface{
    private MaterialToolbar materialToolbar;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        setupInsets();
        initializeViews();
        setupToolbar();

        String category = getIntent().getStringExtra("category_name");
        if (category != null) {
            materialToolbar.setTitle(category);
            setupRecyclerView();
            fetchMealData(category);
        } else {
            Toast.makeText(this, "No category selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeViews() {
        materialToolbar = findViewById(R.id.toolbartile);
        recyclerView = findViewById(R.id.RCV2);
    }

    private void setupToolbar() {
        setSupportActionBar(materialToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        materialToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void fetchMealData(String category) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<MealResponse> call = apiService.getMeals(category);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    meals = response.body().getMeals();
                    if (meals != null && !meals.isEmpty()) {
                        categoryAdapter = new CategoryAdapter(meals, MainActivity2.this);
                        recyclerView.setAdapter(categoryAdapter);
                    } else {
                        Toast.makeText(MainActivity2.this, "No categories available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity2.this, meal_details_screen.class);
        intent.putExtra("mealID",meals.get(position).getIdMeal());
        startActivity(intent);

    }
}

