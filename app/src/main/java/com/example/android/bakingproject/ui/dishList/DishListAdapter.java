package com.example.android.bakingproject.ui.dishList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.POJOS.Dish;

import java.util.List;


import timber.log.Timber;

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishListViewHolder> {
    private List<Dish> mDishes;

    public DishListAdapter(List<Dish> dishes) {
        mDishes = dishes;

    }

    @NonNull
    @Override
    public DishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_list_item,parent,false);
        return new DishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishListViewHolder holder, int position) {
        Timber.d(mDishes.get(2).getImage());
        holder.mDishNameTextView.setText(getModifiedText(mDishes.get(position).getName()));

        Glide.with(holder.itemView).load(mDishes.get(position).getImage())
                .into(holder.mDishImageView);



    }

    private String getModifiedText(String dishName) {

        StringBuilder builder = new StringBuilder(" ");
        builder.append(dishName);
        builder.append("  ");
        return builder.toString();
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    class DishListViewHolder extends RecyclerView.ViewHolder  {
        private ImageView mDishImageView;
        private TextView mDishNameTextView;

        public DishListViewHolder(@NonNull View itemView) {
            super(itemView);
            mDishImageView = itemView.findViewById(R.id.food_view);
            mDishNameTextView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener((view)-> Timber.d("SLAW"));

        }

    }


}
