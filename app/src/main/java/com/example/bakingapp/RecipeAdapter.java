package com.example.bakingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Recipe;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CardViewHolder> {

    private String TAG = RecipeAdapter.class.getSimpleName();
    private List<Recipe> recipeList;
    private Recipe recipe;
    private final ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(Recipe recipe);
    }

    public RecipeAdapter(List<Recipe> recipeList, ListItemClickListener listener) {
        this.recipeList = recipeList;
        mOnClickListener = listener;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        CardViewHolder viewHolder = new CardViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.CardViewHolder holder, int position) {
        recipe = this.recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView recipeNameView;

        public CardViewHolder(View itemView) {
            super(itemView);
            recipeNameView = itemView.findViewById(R.id.tv_recipe_name);
        }

        void bind(final Recipe recipe) {
            recipeNameView.setText(recipe.getRecipeName());
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    mOnClickListener.onListItemClick(recipe);
                }
            });
        }
    }
}
