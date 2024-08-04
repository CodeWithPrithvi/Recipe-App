package com.prithviraj.recipeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class meal_details_screen extends AppCompatActivity {
    MaterialToolbar toolbartitle2;
    TextView foodname,instruction;
    ImageView foodimage3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_details_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        foodname = findViewById(R.id.foodname);
        foodimage3 = findViewById(R.id.foodimage3);
        instruction= findViewById(R.id.instructions);
        toolbartitle2 = findViewById(R.id.toolbartile2);
        Intent intent = getIntent();
        String mealID = intent.getStringExtra("mealID");
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<MealDetails> call = apiService.getMealDetails(mealID);
        call.enqueue(new Callback<MealDetails>() {
            @Override
            public void onResponse(Call<MealDetails> call, Response<MealDetails> response) {
                if (response.isSuccessful() && response.body()!= null)
                {
                    List<Meal2> meal2s = response.body().getMeals();
                    foodname.setText(meal2s.get(0).getStrMeal());
                    Glide.with(meal_details_screen.this)
                            .load(meal2s.get(0).getStrMealThumb())
                            .into(foodimage3);
                    instruction.setText(meal2s.get(0).getStrInstructions());
                    toolbartitle2.setTitle(meal2s.get(0).getStrMeal());


                }
            }

            @Override
            public void onFailure(Call<MealDetails> call, Throwable throwable) {

            }
        });


    }
}