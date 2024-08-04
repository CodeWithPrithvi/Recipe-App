package com.prithviraj.recipeapp;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface ApiService {
    @GET("categories.php")
    Call<CategoriesResponse> getCategories();

    @GET("filter.php")
    Call<MealResponse> getMeals(@Query("c") String category);

    @GET("lookup.php")
    Call<MealDetails> getMealDetails(@Query("i") String mealId);
}

