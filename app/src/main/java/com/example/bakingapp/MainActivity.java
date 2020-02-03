package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.JsonUtils;
import com.example.bakingapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements RecipeAdapter.ListItemClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    public static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private RecyclerView recipeRecyclerView;
    private LinearLayoutManager layoutManager;
    private Recipe recipe;
    private List<Recipe> recipeList;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeRecyclerView = findViewById(R.id.recipe_card);
        layoutManager = new LinearLayoutManager(this);
        recipeRecyclerView.setLayoutManager(layoutManager);

        new getRecipes().execute();
    }

    @Override
    public void onListItemClick(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetail.class);
        intent.putExtra(RecipeDetail.RECIPE_QUANTITY, recipe.getServings());
        //Log.e(TAG, "Recipe Servings: " + recipe.getServings());
        startActivity(intent);
    }

    private class getRecipes extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                sock.close();

                String json = NetworkUtils.getRecipeURL(RECIPE_URL);

                if (json != null) {
                    recipeList = JsonUtils.parseRecipeJson(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (recipeAdapter == null) {
                recipeAdapter = new RecipeAdapter(recipeList, MainActivity.this);
                recipeRecyclerView.setAdapter(recipeAdapter);
            }
        }
    }
}
