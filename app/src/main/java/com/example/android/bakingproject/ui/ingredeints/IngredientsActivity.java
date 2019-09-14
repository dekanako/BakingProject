package com.example.android.bakingproject.ui.ingredeints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.POJOS.Dish;
import com.google.gson.Gson;

import timber.log.Timber;

public class IngredientsActivity extends AppCompatActivity {
    private Dish mDish;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        mDish = returnDishFromJson(getIntent().getStringExtra(Intent.EXTRA_INTENT));
        mRecyclerView = findViewById(R.id.ingredents_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new IngredientsAdapter(mDish.getIngredients()));
    }

    private Dish returnDishFromJson(String stringExtra) {
        return new Gson().fromJson(stringExtra,Dish.class);
    }


}
