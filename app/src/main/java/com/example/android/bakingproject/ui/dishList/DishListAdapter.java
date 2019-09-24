package com.example.android.bakingproject.ui.dishList;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.pojo.Dish;
import com.example.android.bakingproject.ui.hosting.HostingActivity;
import com.google.gson.Gson;

import java.util.List;


import timber.log.Timber;

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishListViewHolder> {
    private List<Dish> mDishes;
    private Context mContext;
    public DishListAdapter(List<Dish> dishes, Context context) {
        mDishes = dishes;
        mContext = context;

    }

    @NonNull
    @Override
    public DishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_list_item,parent,false);
        return new DishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishListViewHolder holder, int position) {
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

    protected class DishListViewHolder extends RecyclerView.ViewHolder  {
        private ImageView mDishImageView;
        private TextView mDishNameTextView;

        private DishListViewHolder(@NonNull View itemView) {
            super(itemView);
            mDishImageView = itemView.findViewById(R.id.food_view);
            mDishNameTextView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(this::startIngredientActivity);

        }

        private void startIngredientActivity(View view) {
            Intent intent = new Intent(mContext, HostingActivity.class);
            Dish dish = mDishes.get(getAdapterPosition());
            intent.putExtra(Intent.EXTRA_INTENT,new Gson().toJson(dish));

            mContext.startActivity(intent);
        }
    }


}
