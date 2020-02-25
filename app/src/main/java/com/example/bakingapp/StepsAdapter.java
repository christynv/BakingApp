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
    private final ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(Steps steps);
    }

    public StepsAdapter(List<Steps> stepsList, ListItemClickListener listener) {
        this.stepsList = stepsList;
        mOnClickListener = listener;
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

        public DescriptionHolder(View itemView) {
            super(itemView);
            shortDescriptionView = itemView.findViewById(R.id.tv_short_description);
        }
        void bind(final Steps recipeSteps) {
            shortDescriptionView.setText(recipeSteps.getShortDescription());
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    mOnClickListener.onListItemClick(recipeSteps);
                }
            });
        }
    }
}
