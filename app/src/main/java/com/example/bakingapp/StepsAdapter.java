package com.example.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bakingapp.model.Steps;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.DescriptionHolder> {

    private List<Steps> stepsList;
    private Steps recipeSteps;

    public StepsAdapter(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    @Override
    public DescriptionHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.step_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        DescriptionHolder viewHolder = new DescriptionHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( DescriptionHolder holder, int position) {
        recipeSteps = this.stepsList.get(position);
        holder.bind(recipeSteps);
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    public class DescriptionHolder extends RecyclerView.ViewHolder {

        TextView shortDescriptionView;
        TextView descriptionView;
        TextView videoURLView;

        public DescriptionHolder(View itemView) {
            super(itemView);
            descriptionView = itemView.findViewById(R.id.tv_description);
        }
        void bind(final Steps recipeSteps) {
            descriptionView.setText(recipeSteps.getDescription());
        }
    }
}
