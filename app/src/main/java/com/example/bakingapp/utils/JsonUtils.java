package com.example.bakingapp.utils;

import android.util.Log;

import com.example.bakingapp.model.Ingredient;
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
        String recipeName = null;
        List<String> descriptionList = null;
        List<String> videoURLList = null;
        String thumbnailURL = null;
        int servings = 0;

        try {
            JSONArray recipeArray = new JSONArray(json);

            for (int a = 0; a < recipeArray.length(); a++) {
                detailsJSON = recipeArray.getJSONObject(a);

                recipeName = detailsJSON.getString("name");

                recipe = new Recipe();
                recipe.setRecipeName(recipeName);

                JSONArray stepsArray = detailsJSON.getJSONArray("steps");
                descriptionList = new ArrayList<>();
                videoURLList = new ArrayList<>();
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

    public static List<Ingredient> parseIngredientJson(String json) {

        List<Ingredient> ingredientList = new ArrayList<>();
        JSONObject detailsJSON;
        /*List<Double> quantity = null;
        List<String> measure = null;
        List<String> ingredient = null*/

        double quantity;
        String measure;
        String ingredient;

        try {
            JSONArray fullArray = new JSONArray(json);

            for (int a = 0; a < fullArray.length(); a++) {
                detailsJSON = fullArray.getJSONObject(a);

                JSONArray ingredientsArray = detailsJSON.getJSONArray("ingredients");

                for (int i = 0; i < ingredientsArray.length(); i++) {
                    JSONObject ingredients = ingredientsArray.getJSONObject(i);
                    /*quantity.add(i, ingredients.getDouble("quantity"));
                    measure.add(ingredients.getString("measure"));
                    ingredient.add(ingredients.getString("ingredient"));*/

                    quantity = ingredients.getDouble("quantity");
                    measure = ingredients.getString("measure");
                    ingredient = ingredients.getString("ingredient");

                    Ingredient ingredientsModel = new Ingredient();
                    ingredientsModel.setQuantity(quantity);
                    ingredientsModel.setMeasure(measure);
                    ingredientsModel.setIngredient(ingredient);

                    ingredientList.add(i, ingredientsModel);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ingredientList;
    }
}
