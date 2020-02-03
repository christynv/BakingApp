package com.example.bakingapp.utils;

import android.util.Log;

import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String TAG = JsonUtils.class.getSimpleName();

    public static List<Recipe> parseRecipeJson(String json) {

        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe;
        JSONObject detailsJSON;
        int recipeID = 0;
        String recipeName = null;
        int servings = 0;

        try {
            JSONArray recipeArray = new JSONArray(json);

            for (int a = 0; a < recipeArray.length(); a++) {
                detailsJSON = recipeArray.getJSONObject(a);

                recipeID = detailsJSON.getInt("id");
                recipeName = detailsJSON.getString("name");
                servings = detailsJSON.getInt("servings");

                recipe = new Recipe();
                recipe.setId(recipeID);
                recipe.setRecipeName(recipeName);
                recipe.setServings(servings);

                recipeList.add(a, recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public static List<Ingredient> parseIngredientJson(String json, int position ) {

        List<Recipe> recipeList = new ArrayList<>();
        List<Ingredient> ingredientList = new ArrayList<>();
        JSONObject detailsJSON;

        double quantity;
        String measure;
        String ingredient;

        try {
            JSONArray fullArray = new JSONArray(json);
            detailsJSON = fullArray.getJSONObject(position);

            JSONArray ingredientsArray = detailsJSON.getJSONArray("ingredients");

            Ingredient ingredientsModel = new Ingredient();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                JSONObject ingredients = ingredientsArray.getJSONObject(i);

                quantity = ingredients.getDouble("quantity");
                measure = ingredients.getString("measure");
                ingredient = ingredients.getString("ingredient");

                ingredientsModel.setQuantity(quantity);
                ingredientsModel.setMeasure(measure);
                ingredientsModel.setIngredient(ingredient);

                ingredientList.add(i, ingredientsModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "Ingredients Total: " + ingredientList.size());
        return ingredientList;
    }

    public static List<Steps> parseStepsJson(String json, int position) {

        List<Steps> stepsList = new ArrayList<>();
        JSONObject detailsJSON;

        String shortDescription;
        String description;
        String videoURL;

        try {
            JSONArray fullArray = new JSONArray(json);
            detailsJSON = fullArray.getJSONObject(position);

            JSONArray stepsArray = detailsJSON.getJSONArray("steps");

            for (int j = 0; j < stepsArray.length(); j++) {
                JSONObject steps = stepsArray.getJSONObject(j);
                shortDescription = steps.getString("shortDescription");
                description = steps.getString("description");
                videoURL = steps.getString("videoURL");

                Steps stepsModel = new Steps();
                stepsModel.setShortDescription(shortDescription);
                stepsModel.setDescription(description);
                stepsModel.setVideoURL(videoURL);

                stepsList.add(j, stepsModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stepsList;
    }
}
