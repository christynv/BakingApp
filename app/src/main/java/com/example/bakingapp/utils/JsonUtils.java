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

        List<Recipe> recipeList = new ArrayList<>();
        JSONObject detailsJSON;
        int recipeID = 0;
        String recipeName = null;
        int quantity = 0;
        String measure = null;
        String ingredient = null;
        String description = null;
        String videoURL = null;
        String thumbnailURL = null;
        int servings = 0;

        try {
            JSONArray recipeArray = new JSONArray(json);

            for (int a = 0; a < recipeArray.length(); a++) {
                detailsJSON = recipeArray.getJSONObject(a);

                recipeName = detailsJSON.getString("name");

                Recipe recipe = new Recipe();
                recipe.setRecipeName(recipeName);

                JSONArray ingredientsArray = detailsJSON.getJSONArray("ingredients");
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    JSONObject ingredients = ingredientsArray.getJSONObject(i);
                    quantity = ingredients.getInt("quantity");
                    measure = ingredients.getString("measure");
                    ingredient = ingredients.getString("ingredient");

                    recipe.setQuantity(quantity);
                    recipe.setMeasure(measure);
                    recipe.setIngredient(ingredient);
                }

                JSONArray stepsArray = detailsJSON.getJSONArray("steps");
                for (int j = 0; j < stepsArray.length(); j++) {
                    JSONObject steps = stepsArray.getJSONObject(j);
                    description = steps.getString("description");
                    videoURL = steps.getString("videoURL");
                    thumbnailURL = steps.getString("thumbnailURL");

                    recipe.setDescription(description);
                    recipe.setVideoURL(videoURL);
                    recipe.setThumbnailURL(thumbnailURL);
                }

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
