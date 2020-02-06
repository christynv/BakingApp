package com.example.bakingapp.model;

import java.util.List;

public class Recipe {

    private int id;
    private String recipeName;
    private int servings;
    private List<Ingredient> ingredients;
    private List<Steps> steps;

    public Recipe() {

    }

    public Recipe(int id, String recipeName, int servings, List<Ingredient> ingredients, List<Steps> steps) {
        this.id = id;
        this.recipeName = recipeName;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getRecipeName() {return recipeName;}

    public void setRecipeName(String recipeName) {this.recipeName = recipeName;}

    public int getServings() {return servings;}

    public void setServings(int servings) {this.servings = servings;}

    public List<Ingredient> getIngredients() {return ingredients;}

    public void  setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients;}

    public List<Steps> getSteps() {return steps;}

    public void setSteps(List<Steps> steps) {this.steps = steps;}
}
