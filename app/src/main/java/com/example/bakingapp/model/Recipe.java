package com.example.bakingapp.model;

import java.util.List;

public class Recipe {

    private int id;
    private String recipeName;
    private int quantity;
    private String measure;
    private String ingredient;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    private int servings;

    public Recipe() {

    }

    public Recipe(int id, String recipeName, int quantity, String measure, String ingredient,
                    String description, String videoURL, String thumbnailURL, int servings) {
        this.id = id;
        this.recipeName = recipeName;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.servings = servings;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getRecipeName() {return recipeName;}

    public void setRecipeName(String recipeName) {this.recipeName = recipeName;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public String getMeasure() {return measure;}

    public void setMeasure(String measure) {this.measure = measure;}

    public String getIngredient() {return ingredient;}

    public void setIngredient(String ingredient) {this.ingredient = ingredient;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getVideoURL() {return videoURL;}

    public void setVideoURL(String videoURL) {this.videoURL = videoURL;}

    public String getThumbnailURL() {return thumbnailURL;}

    public void setThumbnailURL(String thumbnailURL) {this.thumbnailURL = thumbnailURL;}

    public int getServings() {return servings;}

    public void setServings(int servings) {this.servings = servings;}
}
