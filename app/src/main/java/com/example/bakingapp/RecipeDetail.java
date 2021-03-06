package com.example.bakingapp;

import android.app.MediaRouteButton;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import java.util.List;

public class RecipeDetail extends AppCompatActivity
        implements StepsAdapter.ListItemClickListener{

    private static String TAG = RecipeDetail.class.getSimpleName();

    public static final String RECIPE_ID = "extra_recipe_id";
    public static final String RECIPE_NAME = "extra_recipe_name";
    public static final String RECIPE_SERVING = "extra_recipe_serving";

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

        servingsTv = findViewById(R.id.tv_serving);
        ingredientRV = findViewById(R.id.rv_ingredient);
        layoutManager = new LinearLayoutManager(this);
        ingredientRV.setLayoutManager(layoutManager);

        stepsRV = findViewById(R.id.rv_description);
        stepsLayoutManager = new LinearLayoutManager(this);
        stepsRV.setLayoutManager(stepsLayoutManager);

        Intent intent = getIntent();
        if(intent != null) {
            final Bundle recipe = intent.getExtras();
            recipeID = recipe.getInt(RECIPE_ID, 0);
            servingsTv.setText(String.valueOf(recipe.getInt(RECIPE_SERVING, 0)));
            setTitle(recipe.getString(RECIPE_NAME, "Baking App"));
            Log.d(TAG, "Recipe ID: " + recipeID);

            new getIngredients().execute();
            new getSteps().execute();
         }
    }

    @Override
    public void onListItemClick(Steps steps) {
        Intent intent = new Intent(this, DescriptionDetail.class);
        intent.putExtra(DescriptionDetail.DESCRIPTION_TEXT, steps.getDescription());
        intent.putExtra(DescriptionDetail.DESCRIPTION_VIDEO, steps.getVideoURL());
        startActivity(intent);
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
                stepsAdapter = new StepsAdapter(stepsList, RecipeDetail.this);
                stepsRV.setAdapter(stepsAdapter);
            }
        }
    }
}
