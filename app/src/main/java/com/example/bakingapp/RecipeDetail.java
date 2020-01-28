package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetail extends AppCompatActivity {

    public static final String RECIPE_QUANTITY = "extra_recipe_quantity";
    public static final String RECIPE_MEASURE = "extra_recipe_measure";
    public static final String RECIPE_INGREDIENT = "extra_recipe_ingredient";
    public static final String RECIPE_DESCRIPTION = "extra_recipe_description";

    private TextView recipeQuantity;
    private TextView recipeMeasure;
    private TextView recipeIngredient;
    private TextView recipeDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recipeQuantity = findViewById(R.id.tv_quantity);
        recipeMeasure = findViewById(R.id.tv_measure);
        recipeIngredient = findViewById(R.id.tv_ingredient);
        recipeDescription = findViewById(R.id.tv_description);

        Intent intent = getIntent();
        if(intent == null) {
            finish();
        }

        Bundle recipe = intent.getExtras();
        if( recipe != null ) {
            recipeQuantity.setText(recipe.getString(RECIPE_QUANTITY, "quantity"));
            recipeMeasure.setText(recipe.getString(RECIPE_MEASURE, "measure"));
            recipeIngredient.setText(recipe.getString(RECIPE_INGREDIENT, "ingredient"));
            recipeDescription.setText(recipe.getString(RECIPE_DESCRIPTION, "description"));
        } else finish();
    }
}
