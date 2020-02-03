package com.example.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Steps;
import com.example.bakingapp.utils.JsonUtils;
import com.example.bakingapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RecipeDetail extends AppCompatActivity {

    private static String TAG = RecipeDetail.class.getSimpleName();

    public static final String RECIPE_ID = "extra_recipe_id";
    public static final String RECIPE_SERVING = "extra_recipe_serving";
    public static final String RECIPE_QUANTITY = "extra_recipe_quantity";

    private int recipeID;
    private TextView servingsTv;

    private IngredientAdapter ingredientAdapter;
    private RecyclerView ingredientRV;
    private LinearLayoutManager layoutManager;
    private List<Ingredient> ingredientList;

    private StepsAdapter stepsAdapter;
    private RecyclerView stepsRV;
    private LinearLayoutManager stepsLayoutManager;
    private List<Steps> stepsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        servingsTv = (TextView) findViewById(R.id.tv_serving);
        ingredientRV = findViewById(R.id.rv_ingredient);
        layoutManager = new LinearLayoutManager(this);
        ingredientRV.setLayoutManager(layoutManager);

        stepsRV = findViewById(R.id.rv_description);
        stepsLayoutManager = new LinearLayoutManager(this);
        stepsRV.setLayoutManager(stepsLayoutManager);

        Intent intent = getIntent();
        if(intent != null) {
            final Bundle recipe = intent.getExtras();
            servingsTv.setText(String.valueOf(recipe.getInt(RECIPE_SERVING, 0)));
            recipeID = recipe.getInt(RECIPE_ID, 0);

            Log.d(TAG, "Servings: " + servingsTv.getText());

            new getIngredients().execute();
            new getSteps().execute();
         }
    }

    private class getIngredients extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                sock.close();

                String ingredientJson = NetworkUtils.getRecipeURL(MainActivity.RECIPE_URL);

                if (ingredientJson != null) {
                    ingredientList = JsonUtils.parseIngredientJson(ingredientJson, recipeID);
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
            if (ingredientAdapter == null) {
                ingredientAdapter = new IngredientAdapter(ingredientList);
                ingredientRV.setAdapter(ingredientAdapter);
            }
        }
    }

    private class getSteps extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                sock.close();

                String stepsJson = NetworkUtils.getRecipeURL(MainActivity.RECIPE_URL);

                if (stepsJson != null) {
                    stepsList = JsonUtils.parseStepsJson(stepsJson, recipeID);
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
            if (stepsAdapter == null) {
                stepsAdapter = new StepsAdapter(stepsList);
                stepsRV.setAdapter(stepsAdapter);
            }
        }
    }
}
