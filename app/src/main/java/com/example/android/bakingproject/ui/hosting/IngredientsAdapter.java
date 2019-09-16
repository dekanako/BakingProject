package com.example.android.bakingproject.ui.hosting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.POJOS.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<Ingredients> mIngredientsList;

    public IngredientsAdapter(List<Ingredients> ingredientsList) {
        mIngredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredint_list_item,parent,false);

        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {

        holder.mMeasuringUnitView.setText(mIngredientsList.get(position).getMeasure());
        holder.mItemTextView.setText(mIngredientsList.get(position).getIngredient());
        holder.mQuantityTextView.setText(mIngredientsList.get(position).getQuantity()+"");
    }



    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }

    protected class IngredientsViewHolder extends RecyclerView.ViewHolder{
        private TextView mItemTextView;
        private TextView mMeasuringUnitView;
        private TextView mQuantityTextView;
        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemTextView = itemView.findViewById(R.id.item_text_view);
            mMeasuringUnitView = itemView.findViewById(R.id.mesure_text_view);
            mQuantityTextView = itemView.findViewById(R.id.quantitiy);
        }
    }
}
