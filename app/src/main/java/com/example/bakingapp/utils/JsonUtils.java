package com.example.bakingapp.utils;

import android.util.Log;

import com.example.bakingapp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String TAG = JsonUtils.class.getSimpleName();

    public static List<Recipe> parseRecipeJson(String json) {

        Recipe recipe;
        List<Recipe> recipeList = new ArrayList<>();
        JSONObject detailsJSON;
        int recipeID = 0;
        String recipeName = null;
        List<Double> quantityList = new ArrayList<>();
        List<String> measureList = new ArrayList<>();
        List<String> ingredientList = new ArrayList<>();
        List<String> descriptionList = new ArrayList<>();
        List<String> videoURLList = new ArrayList<>();
        String thumbnailURL = null;
        int servings = 0;

        try {
            JSONArray recipeArray = new JSONArray(json);

            for (int a = 0; a < recipeArray.length(); a++) {
                detailsJSON = recipeArray.getJSONObject(a);

                recipeName = detailsJSON.getString("name");

                recipe = new Recipe();
                recipe.setRecipeName(recipeName);

                JSONArray ingredientsArray = detailsJSON.getJSONArray("ingredients");
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    JSONObject ingredients = ingredientsArray.getJSONObject(i);
                    quantityList.add(i, ingredients.getDouble("quantity"));
                    measureList.add(i, ingredients.getString("measure"));
                    ingredientList.add(i, ingredients.getString("ingredient"));
                }

                recipe.setQuantity(quantityList);
                Log.e(TAG, "Quantity: " + recipe.getQuantity().get(0));
                recipe.setMeasure(measureList);
                Log.e(TAG, "Measure: " + recipe.getMeasure().get(0));
                recipe.setIngredient(ingredientList);
                Log.e(TAG, "Ingredient: " + recipe.getIngredient().get(0));

                JSONArray stepsArray = detailsJSON.getJSONArray("steps");
                for (int j = 0; j < stepsArray.length(); j++) {
                    JSONObject steps = stepsArray.getJSONObject(j);
                    descriptionList.add(j, steps.getString("description"));
                    videoURLList.add(j, steps.getString("videoURL"));
                }

                recipe.setDescription(descriptionList);
                recipe.setVideoURL(videoURLList);

                servings = detailsJSON.getInt("servings");
                recipe.setServings(servings);

                Log.e(TAG, "Recipe ID: " + a);
                recipeList.add(a, recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }
}
