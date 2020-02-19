package com.example.bakingapp.model;

import java.util.List;

public class Recipe {

    private int id;
    private String recipeName;
    private int servings;

    public Recipe() {

    }

    public Recipe(int id, String recipeName, int servings) {
        this.id = id;
        this.recipeName = recipeName;
        this.servings = servings;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getRecipeName() {return recipeName;}

    public void setRecipeName(String recipeName) {this.recipeName = recipeName;}

    public int getServings() {return servings;}

    public void setServings(int servings) {this.servings = servings;}

}
