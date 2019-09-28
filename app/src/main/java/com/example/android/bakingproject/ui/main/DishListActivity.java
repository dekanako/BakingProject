package com.example.android.bakingproject.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


import com.example.android.bakingproject.utilites.AppUtil;
import com.example.android.bakingproject.BuildConfig;
import com.example.android.bakingproject.R;

import com.example.android.bakingproject.ui.main.viewmodels.DishListViewModel;



import timber.log.Timber;

public class DishListActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    private ProgressBar mBar;
    private DishListViewModel mDishListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dish);

        mBar = findViewById(R.id.progress_bar);

        //planting Timber on the DEBUG variant of the app that means when we are deploying our app out Timber logs won't work
        initTimberLogging();

        mRecyclerView = findViewById(R.id.dish_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (AppUtil.isConnectedToNetwork(this)){
            mDishListViewModel = ViewModelProviders.of(this).get(DishListViewModel.class);
            getDishesListFromViewModel();
        }else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("there is no connection please connect and try again")
                    .setTitle("Problems while connecting")
                    .setPositiveButton("ok", (dialog, which) -> getDishesListFromViewModel());
            alert.show();
        }
    }

    private void getDishesListFromViewModel() {
        mDishListViewModel = ViewModelProviders.of(this).get(DishListViewModel.class);

        mDishListViewModel.getDishes()
            .observe(this,dishes -> {
                mRecyclerView.setVisibility(View.VISIBLE);
                //using the observer as an indicator to shut down the progress bar
                mBar.setVisibility(View.INVISIBLE);
                mRecyclerView.setAdapter(new DishListAdapter(dishes, this));
            });
    }

    private void initTimberLogging() {
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
