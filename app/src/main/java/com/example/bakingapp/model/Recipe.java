package com.example.bakingapp.model;

import java.util.List;

public class Recipe {

    private int id;
    private String recipeName;
    private List<Double> quantity;
    private List<String> measure;
    private List<String> ingredient;
    private List<String> description;
    private List<String> videoURL;
    private String thumbnailURL;
    private int servings;

    public Recipe() {

    }

    public Recipe(int id, String recipeName, List<Double> quantity, List<String> measure, List<String> ingredient,
                    List<String> description, List<String> videoURL, String thumbnailURL, int servings) {
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

    public List<Double> getQuantity() {return quantity;}

    public void setQuantity(List<Double> quantity) {this.quantity = quantity;}

    public List<String> getMeasure() {return measure;}

    public void setMeasure(List<String> measure) {this.measure = measure;}

    public List<String> getIngredient() {return ingredient;}

    public void setIngredient(List<String> ingredient) {this.ingredient = ingredient;}

    public List<String> getDescription() {return description;}

    public void setDescription(List<String> description) {this.description = description;}

    public List<String> getVideoURL() {return videoURL;}

    public void setVideoURL(List<String> videoURL) {this.videoURL = videoURL;}

    public String getThumbnailURL() {return thumbnailURL;}

    public void setThumbnailURL(String thumbnailURL) {this.thumbnailURL = thumbnailURL;}

    public int getServings() {return servings;}

    public void setServings(int servings) {this.servings = servings;}
}
