package com.example.bakingapp.model;

import java.util.List;

public class Ingredient {

    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredient() {

    }

    public Ingredient(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    /*public List<Double> getQuantity() {return quantity;}

    public void setQuantity(List<Double> quantity) {this.quantity = quantity;}

    public List<String> getMeasure() {return measure;}

    public void setMeasure(List<String> measure) {this.measure = measure;}

    public List<String> getIngredient() {return ingredient;}

    public void setIngredient(List<String> ingredient) {this.ingredient = ingredient;}*/

    public double getQuantity() {return quantity;}

    public void setQuantity(double quantity) {this.quantity = quantity;}

    public String getMeasure() {return measure;}

    public void setMeasure(String measure) {this.measure = measure;}

    public String getIngredient() {return ingredient;}

    public void setIngredient(String ingredient) {this.ingredient = ingredient;}
}
