package com.prithviraj.recipeapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class MealDetails {

    @SerializedName("meals")
    @Expose
    private List<Meal2> meals;

    public List<Meal2> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal2> meals) {
        this.meals = meals;
    }

}