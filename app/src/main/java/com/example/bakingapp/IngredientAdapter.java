package com.example.bakingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private static String TAG = IngredientAdapter.class.getSimpleName();
    private List<Ingredient> ingredientList;
    private Ingredient recipeIngredients;

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingredient_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        IngredientViewHolder viewHolder = new IngredientViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        recipeIngredients = this.ingredientList.get(position);
        holder.bind(recipeIngredients);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView recipeQuantityView;
        TextView recipeMeasureView;
        TextView recipeIngredientView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            recipeQuantityView = itemView.findViewById(R.id.tv_quantity);
            recipeMeasureView = itemView.findViewById(R.id.tv_measure);
            recipeIngredientView = itemView.findViewById(R.id.tv_ingredient);
        }

        void bind(final Ingredient recipeIngredients) {
            recipeQuantityView.setText(String.valueOf(recipeIngredients.getQuantity()));
            recipeMeasureView.setText(recipeIngredients.getMeasure());
            recipeIngredientView.setText(recipeIngredients.getIngredient());
        }
    }
}
