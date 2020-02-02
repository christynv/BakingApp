package com.example.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.JsonUtils;
import com.example.bakingapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RecipeDetail extends AppCompatActivity {

    private static String TAG = RecipeDetail.class.getSimpleName();
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public static final String RECIPE_QUANTITY = "extra_recipe_quantity";
    public static final String RECIPE_MEASURE = "extra_recipe_measure";
    public static final String RECIPE_INGREDIENT = "extra_recipe_ingredient";
    public static final String RECIPE_DESCRIPTION = "extra_recipe_description";

    private IngredientAdapter ingredientAdapter;
    private RecyclerView ingredientRV;
    private LinearLayoutManager layoutManager;


    List<Ingredient> ingredientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientRV = findViewById(R.id.rv_ingredient);
        layoutManager = new LinearLayoutManager(this);
        ingredientRV.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        if(intent == null) {
            finish();
        }

        new getIngredients().execute();
    }

    private class getIngredients extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                sock.close();

                String ingredientJson = NetworkUtils.getRecipeURL(MainActivity.RECIPE_URL);
                //Log.e(MovieDetailActivity.class.getSimpleName(), "Trailer json: " + trailerJson);

                if (ingredientJson != null) {
                    ingredientList = JsonUtils.parseIngredientJson(ingredientJson);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ingredientAdapter = new IngredientAdapter(ingredientList);
            ingredientRV.setAdapter(ingredientAdapter);
        }
    }
}
