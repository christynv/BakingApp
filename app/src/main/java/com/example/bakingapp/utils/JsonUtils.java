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

        JSONObject detailsJSON;
        List<Recipe> recipeList = new ArrayList<>();
        int recipeID;
        String recipeName;
        int servings;

        try {
            JSONArray recipeArray = new JSONArray(json);

            for (int a = 0; a < recipeArray.length(); a++) {
                detailsJSON = recipeArray.getJSONObject(a);

                recipeID = detailsJSON.getInt("id");
                recipeName = detailsJSON.getString("name");
                servings = detailsJSON.getInt("servings");

                Recipe recipe = new Recipe();
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

    public static List<Ingredient> parseIngredientJson(String json, int recipeID) {

        List<Ingredient> ingredientList = new ArrayList<>();
        JSONObject detailsJSON;
        Ingredient ingredientsModel;
        double quantity;
        String measure;
        String ingredient;

        try {
            JSONArray recipeArray = new JSONArray(json);
            detailsJSON = recipeArray.getJSONObject(recipeID - 1);

            JSONArray ingredientsArray = detailsJSON.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsArray.length(); i++) {
                JSONObject ingredients = ingredientsArray.getJSONObject(i);

                quantity = ingredients.getDouble("quantity");
                measure = ingredients.getString("measure");
                ingredient = ingredients.getString("ingredient");

                ingredientsModel = new Ingredient();
                ingredientsModel.setQuantity(quantity);
                ingredientsModel.setMeasure(measure);
                ingredientsModel.setIngredient(ingredient);

                ingredientList.add(i, ingredientsModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredientList;
    }

    public static List<Steps> parseStepsJson(String json, int recipeID) {

        List<Steps> stepsList = new ArrayList<>();
        JSONObject detailsJSON;
        String shortDescription;
        String description;
        String videoURL;

        try {
            JSONArray recipeArray = new JSONArray(json);
            detailsJSON = recipeArray.getJSONObject(recipeID - 1);

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
